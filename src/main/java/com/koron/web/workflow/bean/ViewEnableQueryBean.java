package com.koron.web.workflow.bean;

import com.koron.common.bean.BaseQueryBean;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 所有服务单号
 * @author
 */
public class ViewEnableQueryBean extends BaseQueryBean implements Serializable {
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始申请日期")
    private String beginDate;

    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束申请日期")
    private String endDate;

    /**
     * 服务单号
     */
    @ApiModelProperty(value = "服务单号")
    private String orderNo;

    /**
     * 申请描述
     */
    @ApiModelProperty(value = "申请描述")
    private String appContent;

    /**
     * 申请人
     */
    @ApiModelProperty(hidden = true)
    private String employeeId;

    @ApiModelProperty(value = "申请人名称")
    private String staffunName;

    @ApiModelProperty(hidden = true)
    private String procInstName;

    @ApiModelProperty(value = "服务类型")
    private String procInstType;

    /**
     * 下一个审核节点
     */
    @ApiModelProperty(hidden = true)
    private String stage;

    private static final long serialVersionUID = 1L;



    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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