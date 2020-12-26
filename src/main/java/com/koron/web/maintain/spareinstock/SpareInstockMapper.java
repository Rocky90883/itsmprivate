package com.koron.web.maintain.spareinstock;

import com.koron.web.maintain.spareinstock.bean.SpareInstockBean;
import com.koron.web.maintain.spareinstock.bean.SpareInstockQueryBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SpareInstockMapper {

    int deleteByPrimaryKey(String id);

    List<SpareInstockBean> queryList(SpareInstockQueryBean querybean);

    int insertSelective(SpareInstockBean record);

    SpareInstockBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SpareInstockBean record);


    @Select(" select case when max(RIGHT(order_no,4)) is null then '0000' else max(RIGHT(order_no,4)) end " +
            " from spare_instock where left(CREATE_TIME,10)=left(now(),10) ")
    String maxOrderNo();




}
