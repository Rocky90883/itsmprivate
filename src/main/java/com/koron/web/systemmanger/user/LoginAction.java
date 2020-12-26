package com.koron.web.systemmanger.user;


import com.koron.common.authentication.NoAuth;
import com.koron.common.type.CacheKey;
import com.koron.util.Constant;
import com.koron.util.JsonUtils;

import com.koron.web.systemmanger.user.bean.StaffunBean;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.swan.bean.MessageBean;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

@RestController
public class LoginAction {

    Logger log = Logger.getLogger(LoginAction.class);

//    @Value("${app.appid}")
//    private String appid;
//    @Value("${app.secret}")
//    private String secret;

    @Resource
    public JedisPool jedisPool;

    @NoAuth
    @RequestMapping("/cas.htm")
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1，验证单点登陆
//        if (true) {
//            String idsAccount = "shanluoq";
//            System.out.println(idsAccount);
//            ADOConnection.runTask(this, "casVerify",MessageBean.class,request,response,idsAccount);
//        }
//        if (org.jasig.cas.client.util.AssertionHolder.getAssertion() != null) {
//            String idsAccount = org.jasig.cas.client.util.AssertionHolder.getAssertion().getPrincipal().getName();
//            System.out.println(idsAccount);
//            ADOConnection.runTask(this, "casVerify",MessageBean.class,request,response,idsAccount);
//        }
        return;
    }

    /**
     * @desc 单点登陆
     * @author dulingjiang
     * @date 2019年10月10日
     * @param factory
     * @param request
     * @param response
     * @param idsAccount
     * @return
     */
    @SuppressWarnings("rawtypes")
    @TaskAnnotation("casVerify")
    private void casVerify(SessionFactory factory, HttpServletRequest request, HttpServletResponse response, String idsAccount){

        StaffunBean staffun = factory.getMapper(UserMapper.class).getStaffByLoginId(idsAccount);

        if (staffun == null)
            try {
                request.getRequestDispatcher("/error.htm").forward(request, response);
            } catch (Exception e) {

            }
        else{
//            userInfo.setDepartmentname(Tools.getDeptName(factory,userInfo.getDepartmentid()));//部门名称赋值
            HttpSession se = request.getSession();
            se.setAttribute(CacheKey.LOGIN_USER,staffun);
            se.getServletContext().setAttribute(staffun.getName(), se.getId());//登录的时候保存会话id到application

//            String uuid = UUID.randomUUID().toString();
            //登陆会话信息存入redis缓存服务器
            MessageBean<String> sret = setRedis(se.getId(), staffun,"粤海供水itsm系统");
            if(sret.getCode() != Constant.MESSAGE_INT_SUCCESS) {
                try {
                    response.sendRedirect(request.getContextPath()+"/err.htm?msg="+ URLEncoder.encode("缓存服务器异常，暂时无法登陆","UTF-8"));
//                    request.setAttribute("msg","缓存服务器异常，暂时无法登陆");
//                    request.getRequestDispatcher("err.htm").forward(request,response);
//                    response.sendRedirect(request.getContextPath()+"/err.htm?msg="+ URLEncoder.encode("缓存服务器异常，暂时无法登陆","UTF-8"));
                } catch (Exception e) {
                    log.error("",e);
                }
                return;
            }
            try {
//                AuthInterceptor.token=uuid;     //tem    后端独立开发启动，拦截器默认給每个请求写上登陆的token    部署注释
//                request.getRequestDispatcher("index.html").forward(request, response);
                response.sendRedirect(request.getContextPath() + "/vue/index.html");
            } catch (Exception e) {

            }
        }
//        MessageBean<String> msg = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "单点登录成功", String.class);
//        return msg;
    }

    /**
     * 记录缓存服务器
     * @param token
     * @param userInfo
     * @return
     */
    private MessageBean<String> setRedis(String token,StaffunBean userInfo,String moduleName) {
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
//        ADOConnection.runTask("_default", serviceUserService, "loginLog",
//                userInfo.getUserInfo().getAcount(), 1, "用户登录",moduleName);
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "", String.class);
    }
}
