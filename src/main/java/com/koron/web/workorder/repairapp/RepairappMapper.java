package com.koron.web.workorder.repairapp;

import com.koron.web.workorder.repairapp.bean.RepairappBean;
import com.koron.web.workorder.repairapp.bean.RepairappQueryBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RepairappMapper {

    List<RepairappBean> queryList(RepairappQueryBean queryBean);

    int deleteByPrimaryKey(String id);

    int insertSelective(RepairappBean record);

    RepairappBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RepairappBean record);


    @Select(" select case when max(RIGHT(order_no,4)) is null then '0000' else max(RIGHT(order_no,4)) end " +
            " from repairapp where left(CREATE_TIME,10)=left(now(),10) ")
    String maxOrderNo();
}