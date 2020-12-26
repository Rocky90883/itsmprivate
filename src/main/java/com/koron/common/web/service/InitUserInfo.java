package com.koron.common.web.service;

import com.koron.web.systemmanger.user.UserBean;
import com.koron.web.systemmanger.user.UserMapper;
import com.koron.web.systemmanger.user.UserQueryBean;
import org.koron.ebs.mybatis.ADOSessionImpl;
import org.koron.ebs.mybatis.SessionFactory;

import java.util.ArrayList;
import java.util.List;


public class InitUserInfo {


//    public UserBean getUserInfo(){
//        UserBean userBean = new UserBean();
//        String userName = "";
//        if(org.jasig.cas.client.util.AssertionHolder.getAssertion()!=null){
//            userName = org.jasig.cas.client.util.AssertionHolder.getAssertion().getPrincipal().getName();
//        }
//        List<UserBean> list = new ArrayList();
//        try(SessionFactory factory = new SessionFactory()){
//            UserQueryBean queryBean = new UserQueryBean();
//            queryBean.setUserName(userName);
//             list = factory.getMapper(UserMapper.class).list(queryBean);
//            System.out.println(list.get(0).getLoginId());
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally {
//
//        }
//        return list.get(0);
//    }

    public void abc(){
        SessionFactory factory = new ADOSessionImpl().getSessionFactory();


    }

}
