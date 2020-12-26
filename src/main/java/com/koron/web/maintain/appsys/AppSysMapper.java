package com.koron.web.maintain.appsys;

import com.koron.web.maintain.appsys.bean.AppSysBean;
import com.koron.web.maintain.appsys.bean.AppSysQueryBean;

import java.util.List;

public interface AppSysMapper {

    List<AppSysBean> queryList(AppSysQueryBean queryBean);

    int deleteByPrimaryKey(String id);

    int insertSelective(AppSysBean record);

    AppSysBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AppSysBean record);

}