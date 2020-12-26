package com.koron.web.workflowUtils.mapper;

import com.koron.web.workflow.bean.BaseWorkflowLogoBean;
import com.koron.web.workflow.bean.ViewEnableQueryBean;
import com.koron.web.workflowUtils.bean.TaskListBean;
import com.koron.web.workflowUtils.bean.TaskListVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BaseWorkflowMapper {


    /**
     * 获取原始单据数目
     * @param bean
     */
    @Select(" SELECT COUNT(*) FROM ${businessType} WHERE id = #{businesskey} AND remove_flag = 0 ")
    int getOriginalData(TaskListBean bean);

    /**
     * 获取原始单据信息(报废)
     * @param bean
     */
    @Select(" SELECT ${businessType}.id,bill_no as orderNo,app_content,employee_id,tblstaffun.name as staffunName,bill_date,auditor,auditor_name,${businessType}.status " +
            " FROM ${businessType} " +
            " left join tblstaffun on tblstaffun.code = ${businessType}.employee_id " +
            "WHERE ${businessType}.id = #{businesskey} AND remove_flag = 0 ")
    List<TaskListVo> getOriginalDataMsgtoAsset(TaskListBean bean);

    /**
     * 获取原始单据信息(转移)
     * @param bean
     */
    @Select(" SELECT ${businessType}.id,bill_no as orderNo,app_content,before_emp_id,tblstaffun.name as staffunName,bill_date,auditor,auditor_name,${businessType}.status " +
            " FROM ${businessType} " +
            " left join tblstaffun on tblstaffun.code = ${businessType}.before_emp_id " +
            " WHERE ${businessType}.id = #{businesskey} AND remove_flag = 0 ")
    List<TaskListVo> getOriginalDataMsgtoShift(TaskListBean bean);

    /**
     * 获取原始单据信息(服务类型)
     * @param bean
     */
    @Select(" SELECT ${businessType}.id,order_no,app_content,employee_id,tblstaffun.name as staffunName,bill_date,auditor,auditor_name,${businessType}.status " +
            " FROM ${businessType} " +
            " left join tblstaffun on tblstaffun.code = ${businessType}.employee_id " +
            " WHERE ${businessType}.id = #{businesskey} AND remove_flag = 0 ")
    List<TaskListVo> getOriginalDataMsgApp(TaskListBean bean);


    /**
     * 查所有基础流程
     * @return
     */
    @Select(" select * from base_workflow ")
    List<BaseWorkflowLogoBean> queryBaseWorkflow();


    /**
     * 根据用户名获取code
     * @param codes
     * @return
     */
    List<String> getUserCode(String[] codes);


    @Update( " update ${tableName} set score=#{score} where id=#{id} " )
    void playScore(@Param("tableName")String tableName, @Param("id")String id,@Param("score")double score);


}
