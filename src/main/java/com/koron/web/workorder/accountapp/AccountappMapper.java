package com.koron.web.workorder.accountapp;

import com.koron.web.workorder.accountapp.bean.AccountappBean;
import com.koron.web.workorder.accountapp.bean.AccountappQueryBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AccountappMapper {

    List<AccountappBean> queryList(AccountappQueryBean queryBean);

    int deleteByPrimaryKey(String id);

    int insertSelective(AccountappBean record);

    AccountappBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AccountappBean record);

    @Select(" select case when max(RIGHT(order_no,4)) is null then '0000' else max(RIGHT(order_no,4)) end " +
            " from accountapp where left(CREATE_TIME,10)=left(now(),10) ")
    String maxOrderNo();


}