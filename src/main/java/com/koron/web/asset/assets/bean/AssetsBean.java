package com.koron.web.asset.assets.bean;

import java.io.Serializable;

import com.koron.common.bean.BaseBean;
import io.swagger.annotations.ApiModelProperty;

/**
 * 资产台账
 * @author 
 */
public class AssetsBean extends BaseBean implements Serializable {
    private String id;

    /**
     * 组织id
     */
    private String orgid;


    @ApiModelProperty(value = "sn号")
    private String snNumber;


    @ApiModelProperty(value = "资产分类id")
    private String assetTypeId;


    @ApiModelProperty(value = "资产分类名称(关联显示)")
    private String assetTypeName;


    @ApiModelProperty(value = "资产型号")
    private String goodsModel;


    @ApiModelProperty(value = "IT资产编号")
    private String assetsNumber;


    @ApiModelProperty(value = "固定资产编号")
    private String fixedNumber;


    @ApiModelProperty(value = "供应商id")
    private String companyId;


    @ApiModelProperty(value = "品牌code")
    private String brand;


    @ApiModelProperty(value = "归属部门")
    private String depatCode;


    @ApiModelProperty(value = "归属部门名称(关联显示)")
    private String depatName;


    @ApiModelProperty(value = "分配id")
    private String portionId;


    @ApiModelProperty(value = "分配编号（关联显示）")
    private String portionNo;


    @ApiModelProperty(value = "登记id")
    private String registerId;


    @ApiModelProperty(value = "登记单号（关联显示）")
    private String registerNo;


    @ApiModelProperty(value = "所有者")
    private String empId;


    @ApiModelProperty(value = "所有者名称(关联显示)")
    private String staffunName;


    @ApiModelProperty(value = "使用状态（待确认、待接受、使用中、已退出）")
    private Integer assetsStatus;


    @ApiModelProperty(value = "资产登记状态（已登记、已报废）")
    private Integer regisStatus;


    @ApiModelProperty(value = "资产转移Id")
    private String assShiftId;


    @ApiModelProperty(value = "转移单号（关联显示）")
    private String assShiftNo;


    @ApiModelProperty(value = "报废Id")
    private String scrappedId;


    @ApiModelProperty(value = "报废编号（关联显示）")
    private String scrappedNo;

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

    public String getSnNumber() {
        return snNumber;
    }

    public void setSnNumber(String snNumber) {
        this.snNumber = snNumber;
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

    public String getAssetsNumber() {
        return assetsNumber;
    }

    public void setAssetsNumber(String assetsNumber) {
        this.assetsNumber = assetsNumber;
    }

    public String getFixedNumber() {
        return fixedNumber;
    }

    public void setFixedNumber(String fixedNumber) {
        this.fixedNumber = fixedNumber;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getPortionId() {
        return portionId;
    }

    public void setPortionId(String portionId) {
        this.portionId = portionId;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getStaffunName() {
        return staffunName;
    }

    public void setStaffunName(String staffunName) {
        this.staffunName = staffunName;
    }

    public Integer getAssetsStatus() {
        return assetsStatus;
    }

    public void setAssetsStatus(Integer assetsStatus) {
        this.assetsStatus = assetsStatus;
    }

    public Integer getRegisStatus() {
        return regisStatus;
    }

    public void setRegisStatus(Integer regisStatus) {
        this.regisStatus = regisStatus;
    }

    public String getAssShiftId() {
        return assShiftId;
    }

    public void setAssShiftId(String assShiftId) {
        this.assShiftId = assShiftId;
    }

    public String getScrappedId() {
        return scrappedId;
    }

    public void setScrappedId(String scrappedId) {
        this.scrappedId = scrappedId;
    }

    public String getPortionNo() {
        return portionNo;
    }

    public void setPortionNo(String portionNo) {
        this.portionNo = portionNo;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public String getAssShiftNo() {
        return assShiftNo;
    }

    public void setAssShiftNo(String assShiftNo) {
        this.assShiftNo = assShiftNo;
    }

    public String getScrappedNo() {
        return scrappedNo;
    }

    public void setScrappedNo(String scrappedNo) {
        this.scrappedNo = scrappedNo;
    }
}