package com.koron.web.asset.assetregist;

import com.koron.web.asset.assetregist.bean.AssetRegistBean;
import com.koron.web.asset.assetregist.bean.AssetRegistQueryBean;
import com.koron.web.asset.assetregist.bean.RegistSoftwareVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AssetRegistMapper {

    int deleteByPrimaryKey(String id);

    int insertSelective(AssetRegistBean record);

    AssetRegistBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AssetRegistBean record);

    List<AssetRegistBean> queryList(AssetRegistQueryBean bean);


    @Select(" select case when max(RIGHT(bill_no,4)) is null then '0000' else max(RIGHT(bill_no,4)) end " +
            " from asset_regist where left(CREATE_TIME,10)=left(now(),10) ")
    String maxBillNo();


    @Update( "update asset_regist set over_qty = over_qty + #{qty} where id=#{id} " )
    void updateOverQty(@Param("id") String id,@Param("qty") double qty);

    List<RegistSoftwareVo> queryListByOffice();
}