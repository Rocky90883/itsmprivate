package com.koron.web.workorder.msgapp;

import com.koron.web.workorder.msgapp.bean.MsgappBean;
import com.koron.web.workorder.msgapp.bean.MsgappQueryBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MsgappMapper {

    List<MsgappBean> queryList(MsgappQueryBean queryBean);

    int deleteByPrimaryKey(String id);

    int insertSelective(MsgappBean record);

    MsgappBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MsgappBean record);

    @Select(" select case when max(RIGHT(order_no,4)) is null then '0000' else max(RIGHT(order_no,4)) end " +
            " from msgapp where left(CREATE_TIME,10)=left(now(),10) ")
    String maxOrderNo();

}