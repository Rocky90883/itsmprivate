package com.koron.web.annex;

import com.koron.web.annex.bean.AnnexBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AnnexMapper {

    @Select( " select * from annex where source_id=#{sourceId}" )
    List<AnnexBean> queryList(AnnexBean dto);

    int deleteByPrimaryKey(String id);

    int insertSelective(AnnexBean record);

    AnnexBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AnnexBean record);


//    @Select("select workflowid from ${tableName} where id = #{id} ")
//    List<AnnexBean> getAnnexByid(@Param("tableName") String tableName, @Param("id") String id);
}