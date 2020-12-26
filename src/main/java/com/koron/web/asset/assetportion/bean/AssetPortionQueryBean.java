package com.koron.web.asset.assetportion.bean;

import com.koron.common.bean.BaseQueryBean;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * asset_portion
 * @author 
 */
public class AssetPortionQueryBean extends BaseQueryBean {
    private String id;

    private String orgid;


    @ApiModelProperty(value = "分配单号")
    private String portionBillNo;


    @ApiModelProperty(value = "部门code")
    private String depatCode;


    @ApiModelProperty(value = "关联资产登记ID")
    private String registerId;


    @ApiModelProperty(value = "分类id")
    private String assetTypeId;


    @ApiModelProperty(value = "资产型号")
    private String goodsModel;


    @ApiModelProperty(value = "分配数量")
    private Double portionQty;


    @ApiModelProperty(value = "分配日期")
    private String billDate;


    @ApiModelProperty(value = "开始时间")
    private String beginDate;


    @ApiModelProperty(value = "结束日期")
    private String endDate;


    @ApiModelProperty(value = "登记单号")
    private String registBillNo;

    public String getRegistBillNo() {
        return registBillNo;
    }

    public void setRegistBillNo(String registBillNo) {
        this.registBillNo = registBillNo;
    }

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

    public String getPortionBillNo() {
        return portionBillNo;
    }

    public void setPortionBillNo(String portionBillNo) {
        this.portionBillNo = portionBillNo;
    }

    public String getDepatCode() {
        return depatCode;
    }

    public void setDepatCode(String depatCode) {
        this.depatCode = depatCode;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getAssetTypeId() {
        return assetTypeId;
    }

    public void setAssetTypeId(String assetTypeId) {
        this.assetTypeId = assetTypeId;
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public Double getPortionQty() {
        return portionQty;
    }

    public void setPortionQty(Double portionQty) {
        this.portionQty = portionQty;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

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
}