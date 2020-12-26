package com.koron.web.workorder.scoremark;

import com.koron.web.workorder.scoremark.bean.ScoremarkBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ScoremarkMapper {

    int deleteByPrimaryKey(String id);

    int insertSelective(ScoremarkBean record);

    ScoremarkBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScoremarkBean record);

    @Select( "select * from scoremark where source_id=#{sourceId}" )
    List<ScoremarkBean> selectBysourceId(String sourceId);
}