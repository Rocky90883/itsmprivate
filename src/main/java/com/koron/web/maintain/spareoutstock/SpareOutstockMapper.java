package com.koron.web.maintain.spareoutstock;

import com.koron.web.maintain.spareoutstock.bean.SpareAvailableInVo;
import com.koron.web.maintain.spareoutstock.bean.SpareOutstockBean;
import com.koron.web.maintain.spareoutstock.bean.SpareOutstockQueryBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SpareOutstockMapper {

    int deleteByPrimaryKey(String id);

    List<SpareOutstockBean> queryList(SpareOutstockQueryBean querybean);

    int insertSelective(SpareOutstockBean record);

    SpareOutstockBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SpareOutstockBean record);

    @Select(" select case when max(RIGHT(order_no,4)) is null then '0000' else max(RIGHT(order_no,4)) end " +
            " from spare_outstock where left(CREATE_TIME,10)=left(now(),10) ")
    String maxOrderNo();

    @Update(" update spare_instock set out_qty=out_qty + #{outQty} where id=#{id} ")
    int updateOutQty(@Param("outQty") double qty, @Param("id") String id);


    List<SpareAvailableInVo> availableSpare();

    @Select( "select sum(qty) as qty from spare_outstock where status=3 and instock_id=#{instockId} " )
    double getshenheQty(String instockId);
}