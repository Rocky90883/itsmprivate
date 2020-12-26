package com.koron.common.thread;

import com.koron.web.systemmanger.dept.DeptService;
import com.koron.web.systemmanger.user.UserService;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.swan.bean.MessageBean;

public class SynOrgTimeThread implements Runnable{

    Logger log = Logger.getLogger(SynOrgTimeThread.class);

    @SuppressWarnings("unchecked")
    @Override
    public void run() {

        log.info("开始线程 同步组织 每天20点0分进行）");
        //同步统一用户平台-部门
        ADOConnection.runTask(new DeptService(), "syncDep", MessageBean.class);

        //同步统一用户平台-人员
        ADOConnection.runTask(new UserService(), "syncUser", MessageBean.class);
    }

}
