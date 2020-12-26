package com.koron.common.bean;

import com.koron.web.workflowUtils.bean.WorkflowBean;
import io.swagger.annotations.ApiModelProperty;


public class BaseWorkflowBean extends WorkflowBean {

    @ApiModelProperty(hidden = true)
    private String createAccount;

    @ApiModelProperty(hidden = true)
    private String createTime;

    @ApiModelProperty(hidden = true)
    private String createName;

    @ApiModelProperty(hidden = true)
    private String updateAccount;

    @ApiModelProperty(hidden = true)
    private String updateTime;

    @ApiModelProperty(hidden = true)
    private String updateName;
    //    private String orgId;

    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 状态:【/1:启用/2:草稿/3审核中/4:已驳回/5:废弃/6:转办中】
     */
    private Integer status;


    @ApiModelProperty(value = "流程状态名称")
    private String flowStatusName;


    @ApiModelProperty(value = "流程状态名称 1提交 2保存草稿")
    private Integer submitType;


    @ApiModelProperty(value = "是否可以审核 0不可以审核 1可审核")
    private int workflowIsApprover = 0;

    public BaseWorkflowBean() {

    }

//    public String getOrgId() {
//        return orgId;
//    }
//
//    public void setOrgId(String orgId) {
//        this.orgId = orgId;
//    }

    public String getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(String createAccount) {
        this.createAccount = createAccount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateAccount() {
        return updateAccount;
    }

    public void setUpdateAccount(String updateAccount) {
        this.updateAccount = updateAccount;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFlowStatusName() {
        return flowStatusName;
    }

    public void setFlowStatusName(String flowStatusName) {
        this.flowStatusName = flowStatusName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSubmitType() {
        return submitType;
    }

    public void setSubmitType(Integer submitType) {
        this.submitType = submitType;
    }

    public int getWorkflowIsApprover() {
        return workflowIsApprover;
    }

    public void setWorkflowIsApprover(int workflowIsApprover) {
        this.workflowIsApprover = workflowIsApprover;
    }
}
