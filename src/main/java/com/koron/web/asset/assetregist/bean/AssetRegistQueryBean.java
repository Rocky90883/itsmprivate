package com.koron.web.asset.assetregist.bean;

import com.koron.common.bean.BaseQueryBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * asset_regist
 * @author 
 */
@Data
public class AssetRegistQueryBean extends BaseQueryBean {

    private String id;

    @ApiModelProperty(value = "组织ID")
    private String orgid;


    @ApiModelProperty(value = "单据编号")
    private String billNo;


    @ApiModelProperty(value = "资产分类Id")
    private String assetTypeId;


    @ApiModelProperty(value = "采购合同编号")
    private String contractNo;


    @ApiModelProperty(value = "采购日期")
    private String buyDate;


    @ApiModelProperty(value = "采购人")
    private String empId;


    @ApiModelProperty(value = "供应商id")
    private String companyId;


    @ApiModelProperty(value = "型号")
    private String goodsModel;


    @ApiModelProperty(value = "采购数量")
    private Double qty;


    @ApiModelProperty(value = "分配数量")
    private Double overQty;


    @ApiModelProperty(value = "品牌id")
    private String brand;


    @ApiModelProperty(value = "备注")
    private String remark;


    /**
     * 开始时间
     */
    private String beginDate;

    /**
     * 结束日期
     */
    private String endDate;


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

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getAssetTypeId() {
        return assetTypeId;
    }

    public void setAssetTypeId(String assetTypeId) {
        this.assetTypeId = assetTypeId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public Double getOverQty() {
        return overQty;
    }

    public void setOverQty(Double overQty) {
        this.overQty = overQty;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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