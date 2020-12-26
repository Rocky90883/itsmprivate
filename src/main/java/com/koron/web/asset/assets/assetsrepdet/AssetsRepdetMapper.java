package com.koron.web.asset.assets.assetsrepdet;

import com.koron.web.asset.assets.assetsrepdet.bean.AssetsRepdetBean;
import com.koron.web.asset.assets.assetsrepdet.bean.AssetsRepdetDto;
import com.koron.web.asset.assets.bean.AssetsQueryBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AssetsRepdetMapper {

    int deleteByPrimaryKey(String id);

    @Select( "select * from assets_repdet where f_ref=#{id}" )
    List<AssetsRepdetBean> queryList(AssetsRepdetDto dto);

    void insertSelective(AssetsRepdetBean record);

    AssetsRepdetBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AssetsRepdetBean record);

}