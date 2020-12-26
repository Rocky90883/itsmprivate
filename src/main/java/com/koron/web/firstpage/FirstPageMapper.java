package com.koron.web.firstpage;

import com.koron.web.firstpage.bean.ServiceAndMtBean;
import com.koron.web.firstpage.bean.SpareFristBean;
import com.koron.web.workorder.accountapp.bean.AccountappBean;
import com.koron.web.workorder.accountapp.bean.AccountappQueryBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FirstPageMapper {

    //查所有服务、运维 信息
    List<ServiceAndMtBean> queryservicesList(String[] procInstTypelist);

    //查最新备品备件信息
    List<SpareFristBean> querySpareFristList();


}