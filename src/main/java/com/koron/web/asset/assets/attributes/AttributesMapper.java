package com.koron.web.asset.assets.attributes;


import java.util.List;

public interface AttributesMapper {

    List<AttributesBean> queryList(AttributesBean queryBean);

    int deleteByPrimaryKey(String assetId);

    int insertSelective(AttributesBean record);

    AttributesBean selectByPrimaryKey(String assetId);

    int updateByPrimaryKeySelective(AttributesBean record);

}