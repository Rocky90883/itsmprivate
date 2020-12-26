package com.koron.common.web.servlet;

import com.google.gson.Gson;
import com.koron.common.authentication.AuthInterceptor;
import com.koron.common.bean.StaffBean;
import com.koron.common.type.CacheKey;
import com.koron.common.web.mapper.StaffMapper;
import com.koron.util.Constant;
import com.koron.util.JsonUtils;
import com.koron.util.Tools;
import com.koron.web.systemmanger.model.SysModelMapper;
import com.koron.web.systemmanger.model.SysModelService;
import com.koron.web.systemmanger.model.bean.ModelTreeBean;
import com.koron.web.systemmanger.model.bean.SysModelBean;
import com.koron.web.systemmanger.model.bean.SysModelVo;
import com.koron.web.systemmanger.roles.SysRoleMapper;
import com.koron.web.systemmanger.roles.bean.FpModelVo;
import com.koron.web.systemmanger.roles.bean.RoleModelVo;
import com.koron.web.systemmanger.roles.bean.SysRoleBean;
import com.koron.web.systemmanger.roles.bean.SysRoleQueryBean;
import com.koron.web.systemmanger.user.LoginAction;
import com.koron.web.systemmanger.user.UserBean;
import com.koron.web.systemmanger.user.UserMapper;
import com.koron.web.systemmanger.user.bean.StaffAccount;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.koron.ebs.mybatis.EnvSource;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.swan.bean.MessageBean;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class LoginController {

    Logger log = Logger.getLogger(LoginController.class);

//    @Value("${app.appid}")
//    private String appid;
//    @Value("${app.secret}")
//    private String secret;

    @Resource
    public JedisPool jedisPool;

    @RequestMapping("/login.htm")
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @RequestMapping("/login/login.htm")
    @ResponseBody
    public String login(@RequestParam("name") String name, @RequestParam("password") String password,
                        HttpServletRequest request) {
        try (SessionFactory factory = new SessionFactory()) {
            return new Gson().toJson(factory.runTask(this, "login", MessageBean.class, name, Tools.MD5(password), request));
        }
    }

    /**
     * 注销登录
     *
     * @return
     */
    @RequestMapping("/logout.htm")
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().removeAttribute(Constant.USER);
        //ModelAndView mv = Tools.getView("login.btl");
        ModelAndView mv = new ModelAndView("redirect:/cas.htm");
        return mv;
    }

    /**
     * 注销登录
     *
     * @return
     */
    @RequestMapping("/canteenLogout.htm")
    @ResponseBody
    public String canteenLogout(HttpServletRequest request) {
        //MessageBean<Integer> ret = new MessageBean<>();
        request.getSession().removeAttribute(CacheKey.LOGIN_USER);
        //ModelAndView mv = Tools.getView("login.btl");
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", Void.class).toJson();
    }
    /**
     * 账号登陆查询
     */
    @TaskAnnotation("login")
    private MessageBean<Integer> login(SessionFactory factory, String name, String password,HttpServletRequest request) {
        MessageBean<Integer> ret = new MessageBean<>();
        UserMapper mapper = factory.getMapper(UserMapper.class);
        UserBean user = mapper.loginPwd(name, password);
        if (user == null) {
            ret.setCode(Constant.MESSAGE_INT_PWDERROR);
            ret.setDescription("账号或密码不正确");
        } else {
            StaffunBean staffun = factory.getMapper(UserMapper.class).getStaffByLoginId(name);
            ret.setCode(Constant.MESSAGE_INT_SUCCESS);
            ret.setDescription("登录成功");
            if(staffun == null) {
                ret.setCode(Constant.MESSAGE_INT_STAFF_NO_EXIST);
                ret.setDescription("用户信息不存在.");
            }else if(staffun.getStatus() != 1){
                ret.setCode(Constant.MESSAGE_INT_STAFF_DISABLE);
                ret.setDescription("用户已停用.");
            }
            FpModelVo interfaces = ADOConnection.runTask(this, "queryModelByStaffuncode", FpModelVo.class, staffun.getCode());
            List<SysModelVo> sysModelVo = ADOConnection.runTask(new SysModelService(), "getSysModelVo", List.class, staffun.getCode());
            List<SysRoleBean> roles = ADOConnection.runTask(this, "getRoleBystaffuncode", List.class, staffun.getCode());
            staffun.setInterfaces(sysModelVo);        //返回人员权限

            StaffAccount staffAccount = new StaffAccount(staffun, roles, "my", interfaces);
            //it运维人员、信息中心、it经理可以看到全部部门
            if (roles.stream().anyMatch(r->r.getBroper().equals("all") || staffun.getCode().equals("admin"))) {
                staffAccount.setBroper("all");
            }
//            roles.stream().forEach(r->{
//                if (r.getBroper().equals("all") || staffun.getCode().equals("admin")) {
//                }
//            });

            HttpSession se = request.getSession(true);
            se.setMaxInactiveInterval(8*60*60);//8个小时
            se.setAttribute(CacheKey.LOGIN_USER, staffAccount);
            se.getServletContext().setAttribute(name,se.getId());
//            String uuid = UUID.randomUUID().toString();
            MessageBean<String> sret = setRedis(se.getId(), staffAccount,"粤海供水itsm系统");
            ret.setToken(se.getId());
//            AuthInterceptor.token=uuid;     //tem    后端独立开发启动，拦截器默认給每个请求写上登陆的token    部署注释
//            YzjSSO.setUser(uuid,staff);
        }
        return ret;
    }

    /**
     * 记录缓存服务器
     * @param token
     * @param userInfo
     * @return
     */
    private MessageBean<String> setRedis(String token,StaffAccount userInfo,String moduleName) {
        // 连接redis
        Jedis resource = null;
        try {
            //写redis,因为要设置过期时间，所以这里直接存为顶级变量
            resource = jedisPool.getResource();
            if (resource != null) {
                resource.set(token, JsonUtils.objectToJson(userInfo));
                resource.expire(token, 30 * 60);
            }
        } catch (Exception e) {
            log.error("",e);
            return MessageBean.create(Constant.MESSAGE_DBFAIL, "缓存服务器异常，不能登陆", String.class);
        } finally {
            if (resource != null) {
                resource.close();
            }
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "", String.class);
    }



    /**
     * 根据角色查人员
     * @param factory
     * @param staffuncode
     * @return
     */
    @TaskAnnotation("queryModelByStaffuncode")
    public FpModelVo queryModelByStaffcode(SessionFactory factory, String staffuncode) {
        FpModelVo vo = new FpModelVo();
        try {
            SysModelMapper mapper = factory.getMapper(SysModelMapper.class);
            SysModelService modelService = new SysModelService();

//            List<ModelTreeBean> modelTree = modelService.findModelTree(factory);    //模块树
            List<SysModelBean> list = mapper.getMoldelByStaffuncode(staffuncode);
//            List<String> collect = list.stream().map(SysModelBean::getId).collect(Collectors.toList()); //人员权限

            vo.setModel(list);
//            vo.setModelId(collect);
//            vo.setModelTree(modelTree);

        } catch (Exception e) {
            log.error("查人员权限异常");
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return vo;
    }

//    /**
//     * 根据角色查人员
//     * @param factory
//     * @param staffuncode
//     * @return
//     */
//    @TaskAnnotation("queryModelByStaffuncode")
//    public SysModelVo queryModelByStaffcode(SessionFactory factory, String staffuncode) {
//        SysModelVo vo = new SysModelVo();
//        try {
//            SysModelMapper mapper = factory.getMapper(SysModelMapper.class);
//            SysModelService modelService = new SysModelService();
//            modelService.getSysModelVo(sta)
//
//        } catch (Exception e) {
//            log.error("查人员权限异常");
//            e.printStackTrace();
//            throw new RuntimeException(e.getMessage());
//        }
//        return vo;
//    }

    /**
     * 登陆-根据人员查角色
     * @param factory
     * @param staffuncode
     * @return
     */
    @TaskAnnotation("getRoleBystaffuncode")
    public List<SysRoleBean> getRoleBystaffuncode(SessionFactory factory, String staffuncode) {
        List<SysRoleBean> roles = new ArrayList();
        try {
            SysRoleMapper mapper = factory.getMapper(SysRoleMapper.class);
            roles = mapper.getRoleBystaffuncode(staffuncode);

        } catch (Exception e) {
            log.error("登陆-根据人员查角色");
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return roles;
    }



}
