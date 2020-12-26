package com.koron.web.asset.myasset.bean;

import io.swagger.annotations.ApiModelProperty;

public class MyAssetDto {

    @ApiModelProperty(value = "资产id")
    private String assetId;

    @ApiModelProperty(value = "操作 1确认、2接收、3驳回")
    private int opt;

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public int getOpt() {
        return opt;
    }

    public void setOpt(int opt) {
        this.opt = opt;
    }
}
