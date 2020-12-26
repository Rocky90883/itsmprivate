package com.koron.web.maintain.spareoutstock.bean;

import java.io.Serializable;
import java.util.Date;

import com.koron.common.bean.BaseWorkflowBean;
import io.swagger.annotations.ApiModelProperty;

/**
 * 备件出库
 * @author 
 */
public class SpareOutstockBean extends BaseWorkflowBean implements Serializable {
    private String id;

    private String orgid;

    /**
     * 出库单号
     */
    @ApiModelProperty(value = "出库单号")
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
     * 入库id
     */
    @ApiModelProperty(value = "入库id")
    private String instockId;

    /**
     * 入库单号
     */
    @ApiModelProperty(value = "入库单号")
    private String instockNo;

    /**
     * 备件类型id
     */
    @ApiModelProperty(value = "备件类型id")
    private String assetTypeId;

    /**
     * 备件类型名称
     */
    @ApiModelProperty(value = "备件类型名称")
    private String assetTypeName;

    /**
     * 备件型号
     */
    @ApiModelProperty(value = "备件型号")
    private String goodsModel;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Double qty;

    /**
     * 领用人名称
     */
    @ApiModelProperty(value = "领用人名称")
    private String staffunName;

    /**
     * 领用人
     */
    @ApiModelProperty(value = "领用人")
    private String employeeId;

    /**
     * 领用人部门code
     */
    @ApiModelProperty(value = "领用人部门code")
    private String depatCode;

    /**
     * 领用人部门名称
     */
    @ApiModelProperty(value = "领用人部门名称")
    private String depatName;

    /**
     * 关联服务号
     */
    @ApiModelProperty(value = "关联服务号")
    private String serviceOrderNo;

    /**
     * 关联服务单据id
     */
    @ApiModelProperty(value = "关联服务单据id")
    private String serviceId;

    /**
     * 服务类型
     */
    @ApiModelProperty(value = "服务类型")
    private String serviceType;

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

    public String getInstockId() {
        return instockId;
    }

    public void setInstockId(String instockId) {
        this.instockId = instockId;
    }

    public String getInstockNo() {
        return instockNo;
    }

    public void setInstockNo(String instockNo) {
        this.instockNo = instockNo;
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

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public String getStaffunName() {
        return staffunName;
    }

    public void setStaffunName(String staffunName) {
        this.staffunName = staffunName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDepatCode() {
        return depatCode;
    }

    public void setDepatCode(String depatCode) {
        this.depatCode = depatCode;
    }

    public String getDepatName() {
        return depatName;
    }

    public void setDepatName(String depatName) {
        this.depatName = depatName;
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