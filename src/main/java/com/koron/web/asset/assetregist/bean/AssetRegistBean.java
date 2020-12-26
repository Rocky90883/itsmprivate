package com.koron.web.asset.assetregist.bean;

import java.io.Serializable;
import java.util.Date;

import com.koron.common.bean.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * asset_regist
 * @author 
 */
@Data
public class AssetRegistBean extends BaseBean {
    private String id;


    @ApiModelProperty(value = "组织ID")
    private String orgid;


    @ApiModelProperty(value = "单据编号")
    private String billNo;


    @ApiModelProperty(value = "资产分类Id")
    private String assetTypeId;


    @ApiModelProperty(value = "资产分类名称")
    private String assetTypeName;


    @ApiModelProperty(value = "采购合同编号")
    private String contractNo;


    @ApiModelProperty(value = "采购日期")
    private String buyDate;


    @ApiModelProperty(value = "采购人")
    private String empId;


    @ApiModelProperty(value = "登记人名字")
    private String staffunName;


    @ApiModelProperty(value = "供应商id")
    private String companyId;


    @ApiModelProperty(value = "供应商名称")
    private String comFullName;


    @ApiModelProperty(value = "型号")
    private String goodsModel;


    @ApiModelProperty(value = "采购数量")
    private Double qty;


    @ApiModelProperty(value = "分配数量")
    private Double overQty;


    @ApiModelProperty(value = "品牌id")
    private String brand;


    @ApiModelProperty(value = "cpu")
    private String cpu;


    @ApiModelProperty(value = "内存")
    private String ram;


    @ApiModelProperty(value = "硬盘")
    private String disk;


    @ApiModelProperty(value = "瓦数")
    private String wattage;


    @ApiModelProperty(value = "接口数")
    private String portqty;


    @ApiModelProperty(value = "带宽")
    private String bandwidth;


    @ApiModelProperty(value = "规格")
    private String spec;


    @ApiModelProperty(value = "版本")
    private String edition;


    @ApiModelProperty(value = "保修年限")
    private String warranty;


    @ApiModelProperty(value = "附件")
    private String annex;

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

    public String getStaffunName() {
        return staffunName;
    }

    public void setStaffunName(String staffunName) {
        this.staffunName = staffunName;
    }

    public String getAssetTypeName() {
        return assetTypeName;
    }

    public void setAssetTypeName(String assetTypeName) {
        this.assetTypeName = assetTypeName;
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

    public String getComFullName() {
        return comFullName;
    }

    public void setComFullName(String comFullName) {
        this.comFullName = comFullName;
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

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String getWattage() {
        return wattage;
    }

    public void setWattage(String wattage) {
        this.wattage = wattage;
    }

    public String getPortqty() {
        return portqty;
    }

    public void setPortqty(String portqty) {
        this.portqty = portqty;
    }

    public String getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(String bandwidth) {
        this.bandwidth = bandwidth;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getAnnex() {
        return annex;
    }

    public void setAnnex(String annex) {
        this.annex = annex;
    }

}