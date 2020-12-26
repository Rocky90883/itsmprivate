package com.koron.common.web.servlet;

import com.koron.common.bean.StaffBean;
import com.koron.common.type.CacheKey;
import com.koron.common.web.mapper.StaffMapper;
import com.koron.util.Constant;
import com.koron.web.systemmanger.model.SysModelService;
import com.koron.web.systemmanger.model.bean.SysModelVo;
import com.koron.web.systemmanger.roles.bean.FpModelVo;
import com.koron.web.systemmanger.roles.bean.SysRoleBean;
import com.koron.web.systemmanger.user.UserMapper;
import com.koron.web.systemmanger.user.bean.StaffAccount;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import org.koron.ebs.mybatis.ADOConnection;
import org.koron.ebs.mybatis.ADOSessionImpl;
import org.koron.ebs.mybatis.SessionFactory;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;



@Controller
public class DevData {


    public static void devdatainit(HttpServletRequest request){

        SessionFactory factory = new ADOSessionImpl().getSessionFactory();
        StaffunBean staffun = factory.getMapper(UserMapper.class).getStaffByLoginId("lidan");

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

    }



}
