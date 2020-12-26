package com.koron.web.workorder.officeapp;

import com.koron.web.workorder.officeapp.bean.OfficeappBean;
import com.koron.web.workorder.officeapp.bean.OfficeappQueryBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OfficeappMapper {

    List<OfficeappBean> queryList(OfficeappQueryBean queryBean);

    int deleteByPrimaryKey(String id);

    int insertSelective(OfficeappBean record);

    OfficeappBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OfficeappBean record);

    @Select(" select case when max(RIGHT(order_no,4)) is null then '0000' else max(RIGHT(order_no,4)) end " +
            " from officeapp where left(CREATE_TIME,10)=left(now(),10) ")
    String maxOrderNo();


}