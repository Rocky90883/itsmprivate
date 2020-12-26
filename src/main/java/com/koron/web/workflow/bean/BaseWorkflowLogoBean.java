package com.koron.web.workflow.bean;

import com.koron.common.bean.BaseBean;

import javax.validation.constraints.NotNull;

public class BaseWorkflowLogoBean extends BaseBean {

    /**
     * ID
     */
    private String id;

    /**
     * 业务ID
     */
    private String businessId;

    /**
     * 业务编码
     */
    private String businessCode;

    /**
     * 业务名称
     */
    private String businessName;

    /**
     * 平台工作流标识
     */
    @NotNull(message="平台工作流标识不能为空")
    private String workflowlogo;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 删除标志
     */
    private Boolean deleteFlag;
    /**
     * 状态    0：停用    1：启用
     */
    private Integer status;

    /**
     * 0 保存草稿   1提交
     */
    private Integer submitType;


    /**
     * 1服务、2资产、3运维
     */
    private Integer modelType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getWorkflowlogo() {
        return workflowlogo;
    }

    public void setWorkflowlogo(String workflowlogo) {
        this.workflowlogo = workflowlogo;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
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

    public Integer getModelType() {
        return modelType;
    }

    public void setModelType(Integer modelType) {
        this.modelType = modelType;
    }
}
