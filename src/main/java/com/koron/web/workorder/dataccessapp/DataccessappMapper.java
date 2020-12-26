package com.koron.web.workorder.dataccessapp;

import com.koron.web.workorder.dataccessapp.bean.DataccessappBean;
import com.koron.web.workorder.dataccessapp.bean.DataccessappQueryBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DataccessappMapper {

    List<DataccessappBean> queryList(DataccessappQueryBean queryBean);

    int deleteByPrimaryKey(String id);

    int insertSelective(DataccessappBean record);

    DataccessappBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DataccessappBean record);

    @Select(" select case when max(RIGHT(order_no,4)) is null then '0000' else max(RIGHT(order_no,4)) end " +
            " from dataccessapp where left(CREATE_TIME,10)=left(now(),10) ")
    String maxOrderNo();

}