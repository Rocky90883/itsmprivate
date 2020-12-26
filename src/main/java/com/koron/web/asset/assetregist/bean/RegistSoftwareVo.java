package com.koron.web.asset.assetregist.bean;

import io.swagger.annotations.ApiModelProperty;

public class RegistSoftwareVo {


    @ApiModelProperty("登记id")
    private String id;

    @ApiModelProperty("库存")
    private String instock;

    @ApiModelProperty("资产型号")
    private String assetTypeName;

    @ApiModelProperty("登记单耗")
    private String billNo;

    @ApiModelProperty("资产类型")
    private String goodsModel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstock() {
        return instock;
    }

    public void setInstock(String instock) {
        this.instock = instock;
    }

    public String getAssetTypeName() {
        return assetTypeName;
    }

    public void setAssetTypeName(String assetTypeName) {
        this.assetTypeName = assetTypeName;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }
}
