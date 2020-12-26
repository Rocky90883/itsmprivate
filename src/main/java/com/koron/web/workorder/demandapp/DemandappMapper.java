package com.koron.web.workorder.demandapp;

import com.koron.web.workorder.demandapp.bean.DemandappBean;
import com.koron.web.workorder.demandapp.bean.DemandappQueryBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DemandappMapper {

    List<DemandappBean> queryList(DemandappQueryBean queryBean);

    int deleteByPrimaryKey(String id);

    int insertSelective(DemandappBean record);

    DemandappBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DemandappBean record);

    @Select(" select case when max(RIGHT(order_no,4)) is null then '0000' else max(RIGHT(order_no,4)) end " +
            " from demandapp where left(CREATE_TIME,10)=left(now(),10) ")
    String maxOrderNo();
}