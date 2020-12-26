package com.koron.web.asset.assetportion.bean;

import java.io.Serializable;
import java.util.List;

import com.koron.common.bean.BaseBean;
import com.koron.web.asset.assetportiondet.bean.AssetPortionDetBean;
import io.swagger.annotations.ApiModelProperty;

/**
 * asset_portion
 * @author 
 */
public class AssetPortionBean extends BaseBean implements Serializable{
    private String id;

    private String orgid;


    @ApiModelProperty(value = "分配单号")
    private String portionBillNo;


    @ApiModelProperty(value = "部门id")
    private String depatCode;


    @ApiModelProperty(value = "归属部门名称")
    private String depatName;


    @ApiModelProperty(value = "关联资产登记ID")
    private String registerId;


    @ApiModelProperty(value = "登记编号")
    private String registBillNo;


    @ApiModelProperty(value = "分类id")
    private String assetTypeId;


    @ApiModelProperty(value = "资产型号")
    private String goodsModel;


    @ApiModelProperty(value = "资产分类名称")
    private String assetTypeName;


    @ApiModelProperty(value = "分配数量")
    private Double portionQty;


    @ApiModelProperty(value = "已分配数量")
    private Integer overQty;


    @ApiModelProperty(value = "分配日期")
    private String billDate;


    private List<AssetPortionDetBean> portionDet;

    private static final long serialVersionUID = 1L;

    public List<AssetPortionDetBean> getPortionDet() {
        return portionDet;
    }

    public void setPortionDet(List<AssetPortionDetBean> portionDet) {
        this.portionDet = portionDet;
    }

    public Integer getOverQty() {
        return overQty;
    }

    public void setOverQty(Integer overQty) {
        this.overQty = overQty;
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

    public String getDepatName() {
        return depatName;
    }

    public void setDepatName(String depatName) {
        this.depatName = depatName;
    }

    public String getRegistBillNo() {
        return registBillNo;
    }

    public void setRegistBillNo(String registBillNo) {
        this.registBillNo = registBillNo;
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

}