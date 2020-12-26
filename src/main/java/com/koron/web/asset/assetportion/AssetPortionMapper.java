package com.koron.web.asset.assetportion;

import com.koron.web.asset.assetportion.bean.AssetPortionBean;
import com.koron.web.asset.assetportion.bean.AssetPortionQueryBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AssetPortionMapper {

    List<AssetPortionBean> queryList(AssetPortionQueryBean queryBean);

    int deleteByPrimaryKey(String id);

    int insertSelective(AssetPortionBean record);

    AssetPortionBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AssetPortionBean record);

    @Update(" update asset_regist set over_qty=over_qty + #{qty} where id=#{id} ")
    int updateRegistQty(@Param("qty") double qty,@Param("id") String id);

    @Update(" update asset_regist set over_qty=over_qty - #{qty} where id=#{id} ")
    int updateRegistQtyjian(@Param("qty") double qty,@Param("id") String id);

    @Update(" update asset_regist set over_qty=#{overQty} where id=#{id}" )
    int updateOverQty(@Param("overQty") double overQty,@Param("id") String id);

    @Select(" select case when max(RIGHT(portion_bill_no,4)) is null then '0000' else max(RIGHT(portion_bill_no,4)) end " +
            " from asset_portion where left(CREATE_TIME,10)=left(now(),10) ")
    String maxBillNo();

    @Select(" select count(0) from asset_portion_ ")
    int countByDet();

    //查询资产是否为软件资产
    @Select( " select count(0) from asset_type where classCode like '00002%' and id=#{assetTypeId} " )
    int countBysoff(String assetTypeId);
}