package com.koron.web.firstpage.bean;

import com.koron.common.bean.BaseQueryBean;
import io.swagger.annotations.ApiModelProperty;

public class ServiceAndMtBean {

    @ApiModelProperty(value = "单据id")
    private String id;

    @ApiModelProperty(value = "摘要")
    private String appContent;

    @ApiModelProperty(value = "报告人")
    private String staffunName;

    @ApiModelProperty(value = "报告时间")
    private String billDate;

    @ApiModelProperty(value = "服务名称")
    private String procInstName;

    @ApiModelProperty(value = "服务类型")
    private String procInstType;

    @ApiModelProperty(value = "审核状态")
    private Integer status;

    @ApiModelProperty(value = "完成时间")
    private String finishTime;

    @ApiModelProperty(value = "创建时间")
    private String createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppContent() {
        return appContent;
    }

    public void setAppContent(String appContent) {
        this.appContent = appContent;
    }

    public String getStaffunName() {
        return staffunName;
    }

    public void setStaffunName(String staffunName) {
        this.staffunName = staffunName;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
