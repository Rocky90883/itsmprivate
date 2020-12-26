package com.koron.web.workorder.officeapp.officeappdet.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 正版软件申请
 * @author 
 */
public class OfficeappdetBean implements Serializable {
    private String id;

    /**
     * 主表id
     */
    @ApiModelProperty(hidden = true)
    private String fRef;

    /**
     * 登记单号
     */
    @ApiModelProperty(value = "登记单号")
    private String billNo;

    /**
     * 登记单id
     */
    @ApiModelProperty(value = "登记单id")
    private String sourceId;

    /**
     * 资产类型
     */
    @ApiModelProperty(hidden = true)
    private String assetTypeId;


    /**
     * 资产类型
     */
    @ApiModelProperty(hidden = true)
    private String typeName;

    /**
     * 资产型号
     */
    @ApiModelProperty(hidden = true)
    private String goodsModel;

    /**
     * 申请数量
     */
    @ApiModelProperty(value = "申请数量")
    private Double qty;

    /**
     * 回填主表申请人
     */
    @ApiModelProperty(hidden = true)
    private String appEmp;

    /**
     * 用户清单(字符串录入)
     */
    @ApiModelProperty(value = "用户清单(字符串录入)")
    private String empids;

    /**
     * 用途
     */
    @ApiModelProperty(value = "用途")
    private String usemsg;

    /**
     * 申请时库存
     */
    @ApiModelProperty(value = "申请时库存")
    private Double instock;

    /**
     * 0未启用、1启用
     */
    @ApiModelProperty(hidden = true)
    private Integer isstart;

    private String assetTypeName;

    private static final long serialVersionUID = 1L;


    public String getAssetTypeName() {
        return assetTypeName;
    }

    public void setAssetTypeName(String assetTypeName) {
        this.assetTypeName = assetTypeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfRef() {
        return fRef;
    }

    public void setfRef(String fRef) {
        this.fRef = fRef;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getAssetTypeId() {
        return assetTypeId;
    }

    public void setAssetTypeId(String assetTypeId) {
        this.assetTypeId = assetTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getAppEmp() {
        return appEmp;
    }

    public void setAppEmp(String appEmp) {
        this.appEmp = appEmp;
    }

    public String getEmpids() {
        return empids;
    }

    public void setEmpids(String empids) {
        this.empids = empids;
    }

    public String getUsemsg() {
        return usemsg;
    }

    public void setUsemsg(String usemsg) {
        this.usemsg = usemsg;
    }

    public Double getInstock() {
        return instock;
    }

    public void setInstock(Double instock) {
        this.instock = instock;
    }

    public Integer getIsstart() {
        return isstart;
    }

    public void setIsstart(Integer isstart) {
        this.isstart = isstart;
    }
}