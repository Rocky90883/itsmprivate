package com.koron.web.asset.assets.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.koron.web.asset.assets.attributes.AttributesBean;
import io.swagger.annotations.ApiModelProperty;

public class ImportAssetBean extends AttributesBean {


    /**
     * 组织id
     */
    private String orgid;


    @ApiModelProperty(value = "sn号")
    @Excel(name = "sn号*")
    private String snNumber;


    @ApiModelProperty(value = "资产分类名称(关联显示)")
    @Excel(name = "资产分类名称")
    private String assetTypeName;


    @ApiModelProperty(value = "资产型号")
    @Excel(name = "资产型号")
    private String goodsModel;


    @ApiModelProperty(value = "固定资产编号")
    @Excel(name = "固定资产编号")
    private String fixedNumber;


    @ApiModelProperty(value = "品牌名称")
    @Excel(name = "品牌名称")
    private String brandName;


    @ApiModelProperty(value = "归属部门名称")
    @Excel(name = "归属部门名称")
    private String depatName;


    @ApiModelProperty(value = "所有者名称")
    @Excel(name = "所有者名称")
    private String staffunName;


    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getSnNumber() {
        return snNumber;
    }

    public void setSnNumber(String snNumber) {
        this.snNumber = snNumber;
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

    public String getFixedNumber() {
        return fixedNumber;
    }

    public void setFixedNumber(String fixedNumber) {
        this.fixedNumber = fixedNumber;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDepatName() {
        return depatName;
    }

    public void setDepatName(String depatName) {
        this.depatName = depatName;
    }

    public String getStaffunName() {
        return staffunName;
    }

    public void setStaffunName(String staffunName) {
        this.staffunName = staffunName;
    }
}
