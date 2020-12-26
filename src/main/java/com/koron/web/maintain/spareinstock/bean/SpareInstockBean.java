package com.koron.web.maintain.spareinstock.bean;

import com.koron.common.bean.BaseWorkflowBean;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 备件入库
 * @author 
 */
public class SpareInstockBean extends BaseWorkflowBean implements Serializable {
    private String id;

    private String orgid;

    /**
     * 服务单号
     */
    @ApiModelProperty(value = "入库单号")
    private String orderNo;

    /**
     * 申请日期
     */
    @ApiModelProperty(value = "申请日期")
    private String billDate;

    /**
     * 申请描述
     */
    @ApiModelProperty(value = "申请描述")
    private String appContent;

    /**
     * 备件分类id
     */
    @ApiModelProperty(value = "备件分类id")
    private String assetTypeId;

    /**
     * 备件分类名称
     */
    @ApiModelProperty(value = "备件分类名称")
    private String assetTypeName;

    /**
     * 备件型号
     */
    @ApiModelProperty(value = "备件型号")
    private String goodsModel;

    /**
     * 入库数量
     */
    @ApiModelProperty(value = "入库数量")
    private Double inQty;

    /**
     * 出库数量
     */
    @ApiModelProperty(value = "出库数量")
    private Double outQty;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private Double price;

    /**
     * 采购合同号
     */
    @ApiModelProperty(value = "采购合同号")
    private String contractNo;

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
     * 完成时间
     */
    @ApiModelProperty(hidden = true)
    private String overTime;

    /**
     * 流程id
     */
    @ApiModelProperty(hidden = true)
    private String workflowid;

    /**
     * 节点名称
     */
    @ApiModelProperty(value = "节点名称")
    private String stage;

    /**
     * 删除标记 (0正常、-1删除)
     */
    @ApiModelProperty(hidden = true)
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

    public String getAppContent() {
        return appContent;
    }

    public void setAppContent(String appContent) {
        this.appContent = appContent;
    }

    public String getAssetTypeId() {
        return assetTypeId;
    }

    public void setAssetTypeId(String assetTypeId) {
        this.assetTypeId = assetTypeId;
    }

    public String getAssetTypeName() {
        return assetTypeName;
    }

    public void setAssetTypeName(String assetTypeName) {
        this.assetTypeName = assetTypeName;
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public Double getInQty() {
        return inQty;
    }

    public void setInQty(Double inQty) {
        this.inQty = inQty;
    }

    public Double getOutQty() {
        return outQty;
    }

    public void setOutQty(Double outQty) {
        this.outQty = outQty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
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