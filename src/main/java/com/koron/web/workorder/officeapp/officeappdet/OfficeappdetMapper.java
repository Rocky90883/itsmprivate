package com.koron.web.workorder.officeapp.officeappdet;

import com.koron.web.workorder.officeapp.officeappdet.bean.OfficeappdetBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OfficeappdetMapper {

    @Select( "select * from officeappdet where f_ref = #{fRef}" )
    List<OfficeappdetBean> queryList(String fRef);

    int deleteByPrimaryKey(String id);

    int insertSelective(OfficeappdetBean record);

    OfficeappdetBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OfficeappdetBean record);


    @Delete( "delete from officeappdet where f_ref = #{fRef}" )
    int deleteByfReg(String fRef);
}