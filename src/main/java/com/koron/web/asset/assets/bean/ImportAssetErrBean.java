package com.koron.web.asset.assets.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.koron.web.asset.assets.attributes.AttributesBean;

public class ImportAssetErrBean extends AttributesBean {

    @Excel(name = "错误信息*")
    private String errMsg;


    private String orgid;


    @Excel(name = "sn号*")
    private String snNumber;


    @Excel(name = "资产分类名称")
    private String assetTypeName;


    @Excel(name = "资产型号")
    private String goodsModel;


    @Excel(name = "固定资产编号")
    private String fixedNumber;


    @Excel(name = "品牌名称")
    private String brandName;


    @Excel(name = "归属部门名称")
    private String depatName;


    @Excel(name = "所有者名称")
    private String staffunName;


    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

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
