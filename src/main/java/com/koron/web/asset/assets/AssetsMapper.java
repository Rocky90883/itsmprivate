package com.koron.web.asset.assets;

import com.koron.web.asset.assets.bean.AssetsBean;
import com.koron.web.asset.assets.bean.AssetsQueryBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AssetsMapper {

    List<AssetsBean> queryList(AssetsQueryBean queryBean);

    int deleteByPrimaryKey(String id);

    int insertSelective(AssetsBean record);

    AssetsBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AssetsBean record);

    @Select(" select case when max(RIGHT(assets_number,4)) is null then '0000' else max(RIGHT(assets_number,4)) end " +
            " from assets where left(CREATE_TIME,10)=left(now(),10) ")
    String maxAssetsNumber();

    @Update( " update assets set assets_status=2 where id=#{id} " )
    int confirmAsset(String id);

    /**
     *驳回分配 驳回确认
     * @param sourceId
     */
    @Delete( " delete from asset_portion_det where source_id=#{sourceId} " )
    void deletBySourceiId(String sourceId);

    @Select( "select count(0) from assets where asset_type_id=#{assetTypeId}" )
    int countByType(String assetTypeId);
}