package com.koron.web.asset.assettype;

import com.koron.web.asset.assettype.bean.AssetTypeBean;
import com.koron.web.asset.assettype.bean.AssetTypeQueryBean;
import com.koron.web.asset.assettype.bean.SelectBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AssetTypeMapper {
    int deleteByPrimaryKey(String id);

    int addassettype(AssetTypeBean record);

    AssetTypeBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AssetTypeBean record);


    //找当前classcode最大值
    String findMaxChild(String classcode);

    //根据code查District数据
    public SelectBean findAssetTypeByCode(@Param("classcode") String classcode);

    //分类树
    List<AssetTypeBean> findAssetType(AssetTypeBean bean);

    //查分类上级
    List<AssetTypeBean> assetTypeSuperList(AssetTypeQueryBean bean);

    //查分类列表
    List<AssetTypeBean> queryList(AssetTypeQueryBean bean);

    //是否存在下级
    @Select("select count(0) from asset_type where parentId=#{parentid}")
    int countByParentid(String parentid);

    int updateAssetType(AssetTypeBean bean);

    //选择分类 下拉框  过滤了备品备件
    List<SelectBean> selectAssetTypeList();


    //选择分类 下拉框  只有备品备件
    List<SelectBean> selectAssetTypeListSpare();


    @Select( "select * from asset_type a where a.isCatalog = 1 " )
    List<AssetTypeBean> queryAllmjList();

}