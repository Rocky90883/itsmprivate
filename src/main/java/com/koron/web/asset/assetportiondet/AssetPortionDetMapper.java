package com.koron.web.asset.assetportiondet;

import com.koron.web.asset.assetportiondet.bean.AssetPortionDetBean;
import com.koron.web.asset.assetportiondet.bean.AssetPortionDetQueryBean;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.systemmanger.user.bean.StaffunQueryBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AssetPortionDetMapper {

    List<AssetPortionDetBean> queryList(AssetPortionDetQueryBean queryBean);

    int deleteByPrimaryKey(String id);

    int insertSelective(AssetPortionDetBean record);

    AssetPortionDetBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AssetPortionDetBean record);


    @Select(" select count(0) from asset_portion_det where f_ref=#{fRef}")
    int countByPortionId(String fRef);

    //根据部门查人员
    @Select( "select * from tblstaffun where org_node_code=#{orgNodeCode} " )
    List<StaffunBean> queryStaffunbydeptCode(StaffunQueryBean orgNodeCode);
}