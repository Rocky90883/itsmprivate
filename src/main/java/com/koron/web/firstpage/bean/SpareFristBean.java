package com.koron.web.firstpage.bean;

import io.swagger.annotations.ApiModelProperty;

public class SpareFristBean {

    @ApiModelProperty(value = "单据id")
    private String id;

    @ApiModelProperty(value = "备件类型")
    private String assetTypeName;

    @ApiModelProperty(value = "数量")
    private Integer qty;

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

    public String getAssetTypeName() {
        return assetTypeName;
    }

    public void setAssetTypeName(String assetTypeName) {
        this.assetTypeName = assetTypeName;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
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
