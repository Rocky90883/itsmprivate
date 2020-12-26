package com.koron.web.workflow.mapper;

import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.workflow.bean.WorkflowResultBean;
import com.koron.web.workflow.bean.WorkflowTypeBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface WorkflowBusinessMapper {
	
   @Update("update ${tableName} set auditor=#{nextcandidateUsers},auditor_name=#{auditorName},stage=#{nextactivitiName},workflowId=#{processinsId}," +
           " `status` = case " +
           " when #{nowOpt}='1' then 4 " +
           " when #{nowOpt}='32' then 3 " +
           " when #{nowOpt}='2' then 6 " +
           " when #{nowOpt}='16' then 5 else `status` end " +
		   " where id = #{id}")
   int updateWorkflowInfo(WorkflowResultBean bean);
   
   @Select("select workflowid from ${tableName} where id = #{id} ")
   String getWorkflowId(@Param("tableName") String tableName, @Param("id") String id);

    @Update("update ${tableName} set auditor=#{nextcandidateUsers},stage=#{nextactivitiName},workflowId=#{processinsId},`status` = case when #{nowOpt}='1' then 4 else `status` end "
            + " where id = #{id}")
    int updateWorkflowStaus(@Param("id") String id,@Param("sta") String staus);


    /**
     * 设置单据启动
     * @param bean
     * @return
     */
    @Update("update ${tableName} set  `status` = 1 where id = #{id}")
    int updateend(WorkflowResultBean bean);


//    @Select(" select name from tblstaffun where workflow_code in (#{nextcandidateUsers}); ")
//    List<String> quetlistByworkflowcode(WorkflowResultBean bean);
}
