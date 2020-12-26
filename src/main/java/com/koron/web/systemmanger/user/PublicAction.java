package com.koron.web.systemmanger.user;

import com.koron.common.authentication.NoAuth;
import com.koron.common.bean.StaffBean;
import com.koron.common.type.CacheKey;
import com.koron.common.web.bean.DepartmentBean;
import com.koron.common.web.mapper.DepartmentMapper;
import com.koron.common.web.servlet.LoginController;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.util.Tools;
import com.koron.web.systemmanger.model.SysModelService;
import com.koron.web.systemmanger.model.bean.SysModelVo;
import com.koron.web.systemmanger.roles.bean.FpModelVo;
import com.koron.web.systemmanger.roles.bean.SysRoleBean;
import com.koron.web.systemmanger.user.bean.StaffAccount;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.koron.ebs.mybatis.ADOSessionImpl;
import org.koron.ebs.mybatis.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.swan.bean.MessageBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Api(tags = "公用请求")
@RestController
public class PublicAction {

    Logger log = Logger.getLogger(PublicAction.class);

//    @Autowired
//    private DeptService deptService;

    /**
     * 获取登陆用户信息
     * @return
     */
    @NoAuth
    @ApiOperation("---获取登陆用户信息")
    @ResponseBody
    @PostMapping(value = "/getUserInfo")
    public MessageBean<?> getUserInfo(){

        @SuppressWarnings("rawtypes")
        MessageBean<StaffunBean> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", StaffunBean.class);
        try {
            StaffunBean userInfo = SessionUtil.getLoginUser();
            success.setData(userInfo);
        } catch (Exception e) {
            log.error("获取登陆用户信息查询失败", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("fail " + e.getMessage());
            return success;
        }
        return success;
    }


    @ApiOperation("---切换用户 dev")
    @ResponseBody
    @PostMapping(value = "/devuserdatainit")
    public MessageBean devuserdatainit(@RequestParam("mobile") String mobile,HttpServletRequest request){

        SessionFactory factory = new ADOSessionImpl().getSessionFactory();
        StaffunBean staffun = factory.getMapper(UserMapper.class).getStaffByMobile(mobile);
        if(StringUtils.isEmpty(staffun.getCode())){
            return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "找不到此手机号的用户", StaffunBean.class);
        }

        FpModelVo interfaces = ADOConnection.runTask(new LoginController(), "queryModelByStaffuncode", FpModelVo.class, staffun.getCode());
        List<SysModelVo> sysModelVo = ADOConnection.runTask(new SysModelService(), "getSysModelVo", List.class, staffun.getCode());
        List<SysRoleBean> roles = ADOConnection.runTask(new LoginController(), "getRoleBystaffuncode", List.class, staffun.getCode());
        staffun.setInterfaces(sysModelVo);  //返回人员权限

        StaffAccount staffAccount = new StaffAccount(staffun, roles, "my", interfaces);
        //it运维人员、信息中心、it经理可以看到全部部门
        if (roles.stream().anyMatch(r->r.getBroper().equals("all") || staffun.getCode().equals("admin"))) {
            staffAccount.setBroper("all");
        }
        HttpSession se = request.getSession(true);
        se.setMaxInactiveInterval(8*60*60);//8个小时
        se.setAttribute(CacheKey.LOGIN_USER, staffAccount);

        factory.close();

        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", StaffunBean.class);
    }

}
