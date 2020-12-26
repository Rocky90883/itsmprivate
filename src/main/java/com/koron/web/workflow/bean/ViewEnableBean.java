package com.koron.web.workflow.bean;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * 所有服务单号
 * @author
 */
public class ViewEnableBean implements Serializable {
    private String id;

    /**
     * 服务单号
     */
    private String orderNo;

    /**
     * 申请描述
     */
    private String appContent;

    /**
     * 申请日期
     */
    private String billDate;

    /**
     * 申请人
     */
    private String employeeId;

    /**
     * 所有者名称(关联显示)
     */
    @ApiModelProperty(value = "申请人(关联显示)")
    private String staffunName;

    /**
     * 申请类型 （1账户新增、2账户注销、3权限变更）
     */
    private Integer apptype;

    private String procInstName;

    private String procInstType;

    /**
     * 下一个审核节点
     */
    private String stage;

    private static final long serialVersionUID = 1L;

    public String getStaffunName() {
        return staffunName;
    }

    public void setStaffunName(String staffunName) {
        this.staffunName = staffunName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAppContent() {
        return appContent;
    }

    public void setAppContent(String appContent) {
        this.appContent = appContent;
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

    public Integer getApptype() {
        return apptype;
    }

    public void setApptype(Integer apptype) {
        this.apptype = apptype;
    }

    public String getProcInstName() {
        return procInstName;
    }

    public void setProcInstName(String procInstName) {
        this.procInstName = procInstName;
    }

    public String getProcInstType() {
        return procInstType;
    }

    public void setProcInstType(String procInstType) {
        this.procInstType = procInstType;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}