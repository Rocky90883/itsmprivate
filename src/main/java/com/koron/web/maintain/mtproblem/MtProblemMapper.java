package com.koron.web.maintain.mtproblem;

import com.koron.web.maintain.mtproblem.bean.MtProblemBean;
import com.koron.web.maintain.mtproblem.bean.MtProblemQueryBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MtProblemMapper {

    int deleteByPrimaryKey(String id);

    List<MtProblemBean> queryList(MtProblemQueryBean queryBean);

    int insertSelective(MtProblemBean record);

    MtProblemBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MtProblemBean record);

    @Select(" select case when max(RIGHT(order_no,4)) is null then '0000' else max(RIGHT(order_no,4)) end " +
            " from mt_problem where left(CREATE_TIME,10)=left(now(),10) ")
    String maxOrderNo();

}