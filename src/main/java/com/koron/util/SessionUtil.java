package com.koron.util;

import com.koron.common.bean.StaffBean;
import com.koron.common.type.CacheKey;
import com.koron.web.systemmanger.user.UserBean;
import com.koron.web.systemmanger.user.bean.StaffAccount;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * HttpSession工具，简单存取HttpRequest缓存
 * @author Gaoyuan
 * @date 2018年8月16日
 *
 */
public class SessionUtil {
    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpSession getSession(){
        HttpServletRequest request = getRequest();
        if(null != request){
            return request.getSession();
        }
        return null;
    }

    public static Object getAttribute(String key){
        HttpSession session = getSession();
        if(null != session){
            return session.getAttribute(key);
        }
        return null;
    }

    public static void setAttribute(String key, Object value){
        HttpSession session = getSession();
        if(null != session){
            session.setAttribute(key, value);
        }
    }

//    @Deprecated
//    private static String getGroupCode(){
//        if(null != getAttribute(CacheKey.GROUP_CODE)){
//            return getAttribute(CacheKey.GROUP_CODE).toString();
//        }
//
//        //TODO 调试完删除
//        return "C021008";
//    }
//
//    @Deprecated
//    private static void setGroupCode(String groupCode){
//        getSession().setAttribute(CacheKey.GROUP_CODE, groupCode);
//    }
//

    /**
     * 获取登陆用户信息
     * @return
     */
    public static StaffunBean getLoginUser() {
        if (null != getSession().getAttribute(CacheKey.LOGIN_USER)) {
            StaffAccount staffAccount = (StaffAccount) getSession().getAttribute(CacheKey.LOGIN_USER);
            return staffAccount.getStaffunBean();
        }
        return null;
    }

    /**
     * 获取登陆人id
     * @return
     */
    public static Integer getUseerInfoId() {
        if (null != getSession().getAttribute(CacheKey.LOGIN_USER)) {
            StaffAccount staffAccount = (StaffAccount) getSession().getAttribute(CacheKey.LOGIN_USER);
            return staffAccount.getStaffunBean().getId();
        }
        return null;
    }

    /**
     * 获取登陆人code
     * @return
     */
    public static String getUseerInfoCode() {
        if (null != getSession().getAttribute(CacheKey.LOGIN_USER)) {
            StaffAccount staffAccount = (StaffAccount) getSession().getAttribute(CacheKey.LOGIN_USER);
            return staffAccount.getStaffunBean().getCode();
        }
        return null;
    }

    /**
     * 获取登陆人流程code
     * @return
     */
    public static String getUseerWorkFlowCode() {
        if (null != getSession().getAttribute(CacheKey.LOGIN_USER)) {
            StaffAccount staffAccount = (StaffAccount) getSession().getAttribute(CacheKey.LOGIN_USER);
            return staffAccount.getStaffunBean().getWorkflowCode();
        }
        return null;
    }

    /**
     * 获取登陆人name
     * @return
     */
    public static String getUseerInfoName() {
        if (null != getSession().getAttribute(CacheKey.LOGIN_USER)) {
            StaffAccount staffAccount = (StaffAccount) getSession().getAttribute(CacheKey.LOGIN_USER);
            return staffAccount.getStaffunBean().getName();
        }
        return null;
    }

    /**
     * 获取登陆人阅览权限
     * @return
     */
    public static String getUseerBroper() {
        if (null != getSession().getAttribute(CacheKey.LOGIN_USER)) {
            StaffAccount staffAccount = (StaffAccount) getSession().getAttribute(CacheKey.LOGIN_USER);
            return staffAccount.getBroper();
        }
        return null;
    }

    public static List<String> getUserModel() {
        if (null != getSession().getAttribute(CacheKey.USER_MODEL)) {
            return (List<String>) getSession().getAttribute(CacheKey.USER_MODEL);
        }
        return null;
    }
//
//        return null;
//    }
//
//    public static Integer getLoginUserId(){
//        UserBean loginUser = getLoginUser();
//        if(null != loginUser){
//            return loginUser.getId();
//        }
//
//        //TODO 调试完删除
//        return 1;
//    }
}