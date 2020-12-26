package com.koron.web.workorder.recordapp;

import com.koron.web.workorder.recordapp.bean.RecordQueryapp;
import com.koron.web.workorder.recordapp.bean.Recordapp;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RecordappMapper {

    int deleteByPrimaryKey(String id);

    List<Recordapp> queryList(RecordQueryapp queryBean);

    int insertSelective(Recordapp record);

    Recordapp selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Recordapp record);

    @Select(" select case when max(RIGHT(order_no,4)) is null then '0000' else max(RIGHT(order_no,4)) end " +
            " from recordapp where left(CREATE_TIME,10)=left(now(),10) ")
    String maxOrderNo();
}