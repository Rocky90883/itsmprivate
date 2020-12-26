package com.koron.web.maintain.release.bean;

import java.io.Serializable;

import com.koron.common.bean.BaseWorkflowBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 发布管理
 * @author 
 */
public class MtReleaseBean extends BaseWorkflowBean implements Serializable {
    private String id;

    private String orgid;

    /**
     * 发布单号
     */
    @ApiModelProperty(value = "发布单号")
    private String orderNo;

    /**
     * 申请日期
     */
    @ApiModelProperty(value = "申请日期")
    private String billDate;

    /**
     * 申请人id
     */
    @ApiModelProperty(value = "申请人id")
    private String employeeId;

    /**
     * 申请人名称
     */
    @ApiModelProperty(value = "申请人名称")
    private String staffunName;

    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    private String projectName;

    /**
     * 关联工单号
     */
    @ApiModelProperty(value = "关联工单号")
    private String serviceOrderNo;

    /**
     * 关联工单id
     */
    @ApiModelProperty(value = "关联工单id")
    private String serviceId;

    /**
     * 工单类型
     */
    @ApiModelProperty(value = "工单类型")
    private String serviceType;

    /**
     * 系统id
     */
    @ApiModelProperty(value = "系统id")
    private String appsysId;

    /**
     * 系统名称
     */
    @ApiModelProperty(value = "系统名称")
    private String appsysName;

    /**
     * 影响范围 1全集团、2单个公司、3个别用户
     */
    @ApiModelProperty(value = "影响范围 1全集团、2单个公司、3个别用户")
    private Integer affectType;

    /**
     * 影响概述
     */
    @ApiModelProperty(value = "影响概述")
    private String affectContent;

    /**
     * 变更描述
     */
    @ApiModelProperty(value = "变更描述")
    private String appContent;

    /**
     * 责任人id
     */
    @ApiModelProperty(value = "责任人id")
    private String assignEmpid;

    /**
     * 责任人名称
     */
    @ApiModelProperty(value = "责任人名称")
    private String assignName;

    /**
     * 分配状态 1已分配、0请求中
     */
    @ApiModelProperty(value = "发布单号")
    private Integer handleStatus;

    /**
     * 完成时间
     */
    private String overTime;

    /**
     * 流程id
     */
    private String workflowid;

    /**
     * 节点名称
     */
    private String stage;

    /**
     * 审核人
     */
    private String auditor;

    /**
     * 删除标记 (0正常、-1删除)
     */
    private Integer removeFlag;


    private static final long serialVersionUID = 1L;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getStaffunName() {
        return staffunName;
    }

    public void setStaffunName(String staffunName) {
        this.staffunName = staffunName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getServiceOrderNo() {
        return serviceOrderNo;
    }

    public void setServiceOrderNo(String serviceOrderNo) {
        this.serviceOrderNo = serviceOrderNo;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getAppsysId() {
        return appsysId;
    }

    public void setAppsysId(String appsysId) {
        this.appsysId = appsysId;
    }

    public String getAppsysName() {
        return appsysName;
    }

    public void setAppsysName(String appsysName) {
        this.appsysName = appsysName;
    }

    public Integer getAffectType() {
        return affectType;
    }

    public void setAffectType(Integer affectType) {
        this.affectType = affectType;
    }

    public String getAffectContent() {
        return affectContent;
    }

    public void setAffectContent(String affectContent) {
        this.affectContent = affectContent;
    }

    public String getAppContent() {
        return appContent;
    }

    public void setAppContent(String appContent) {
        this.appContent = appContent;
    }

    public String getAssignEmpid() {
        return assignEmpid;
    }

    public void setAssignEmpid(String assignEmpid) {
        this.assignEmpid = assignEmpid;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

    public Integer getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(Integer handleStatus) {
        this.handleStatus = handleStatus;
    }

    public String getOverTime() {
        return overTime;
    }

    public void setOverTime(String overTime) {
        this.overTime = overTime;
    }

    public String getWorkflowid() {
        return workflowid;
    }

    public void setWorkflowid(String workflowid) {
        this.workflowid = workflowid;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Integer getRemoveFlag() {
        return removeFlag;
    }

    public void setRemoveFlag(Integer removeFlag) {
        this.removeFlag = removeFlag;
    }
}