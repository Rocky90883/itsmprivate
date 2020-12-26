package com.koron.web.asset.assets.bean;

import com.koron.common.bean.BaseQueryBean;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 资产台账
 * @author 
 */
public class AssetsQueryBean extends BaseQueryBean {
    private String id;

    /**
     * 组织id
     */
    private String orgid;

    /**
     * sn号
     */
    @ApiModelProperty(value = "sn号")
    private String snNumber;

    /**
     * 资产分类id
     */
    @ApiModelProperty(value = "资产分类id")
    private String assetTypeId;

    /**
     * 资产型号
     */
    @ApiModelProperty(value = "资产型号")
    private String goodsModel;

    /**
     * IT资产编号
     */
    @ApiModelProperty(value = "IT资产编号")
    private String assetsNumber;

    /**
     * 固定资产编号
     */
    @ApiModelProperty(value = "固定资产编号")
    private String fixedNumber;

    /**
     * 供应商id
     */
    @ApiModelProperty(value = "供应商id")
    private String companyId;

    /**
     * 品牌id
     */
    @ApiModelProperty(value = "品牌id")
    private String brand;

    /**
     * 归属部门
     */
    @ApiModelProperty(value = "归属部门")
    private String depatCode;

    /**
     * 分配id
     */
    @ApiModelProperty(value = "分配id")
    private String portionId;


    /**
     * 登记id
     */
    @ApiModelProperty(value = "登记id")
    private String registerId;


    /**
     * 所有者
     */
    @ApiModelProperty(value = "所有者")
    private String empId;

    /**
     * 使用状态（待确认、待接受、使用中、已退出）
     */
    @ApiModelProperty(value = "资产状态（0待确认、1待接受、2使用中、-1已退出）")
    private Integer assetsStatus;

    /**
     * 资产登记状态（已登记、已报废）
     */
    @ApiModelProperty(value = "资产登记状态（1已登记、-1已报废）")
    private Integer regisStatus;

    /**
     * 资产转移Id
     */
    @ApiModelProperty(value = "资产转移Id")
    private String assShiftId;



    /**
     * 报废Id
     */
    @ApiModelProperty(value = "报废Id")
    private String scrappedId;


    @ApiModelProperty(value = "所有者名称(关联显示)")
    private String staffunName;


    @ApiModelProperty(value = "归属部门名称(关联显示)")
    private String depatName;



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

    public void setAssetTypeId(String assetTypeid) {
        this.assetTypeId = assetTypeid;
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

    public String getStaffunName() {
        return staffunName;
    }

    public void setStaffunName(String staffunName) {
        this.staffunName = staffunName;
    }

    public String getDepatName() {
        return depatName;
    }

    public void setDepatName(String depatName) {
        this.depatName = depatName;
    }
}