package com.koron.web.workflow.mapper;


import com.koron.web.workflow.bean.AssignEmpDto;
import com.koron.web.workflow.bean.WorkflowResultBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface WorkflowMapper {


    @Update( "update ${procInstType} set assign_empid=#{assignEmpid},assign_name=#{assignName},handle_status=1 where id=#{id}" )
    void assignper(AssignEmpDto dto);


    List<String> quetlistByworkflowcode(String[] bean);


    //获取单据 审核状态
    @Select("select `status` from ${tableName} where id = #{id} ")
    int getworkflowStatus(@Param("tableName") String tableName, @Param("id") String id);
}
