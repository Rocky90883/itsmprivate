package com.koron.web.asset.assetportiondet.bean;

import java.io.Serializable;

import com.koron.common.bean.BaseBean;
import io.swagger.annotations.ApiModelProperty;

/**
 * 资产分配明细
 * @author 
 */
public class AssetPortionDetBean extends BaseBean implements Serializable {
    private String id;


    private String orgid;


    @ApiModelProperty(value = "分配主表id")
    private String fRef;


    @ApiModelProperty(value = "资产台账id，自动生成")
    private String sourceId;


    @ApiModelProperty(value = "资产分类名称(关联显示)")
    private String assetTypeName;


    @ApiModelProperty(value = "归属者名称")
    private String staffunName;


    @ApiModelProperty(value = "台账资产编号")
    private String assetsNumber;


    @ApiModelProperty(value = "归属人")
    private String empId;


    @ApiModelProperty(value = "分配单号(来自关联显示)")
    private String portionBillNo;


    @ApiModelProperty(value = "资产型号(来自关联显示)")
    private String goodsModel;


    @ApiModelProperty(value = "资产状态(来自关联显示)")
    private int assetsStatus;


    @ApiModelProperty(value = "分配日期")
    private String billDate;

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

    public String getfRef() {
        return fRef;
    }

    public void setfRef(String fRef) {
        this.fRef = fRef;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getAssetsNumber() {
        return assetsNumber;
    }

    public void setAssetsNumber(String assetsNumber) {
        this.assetsNumber = assetsNumber;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getAssetTypeName() {
        return assetTypeName;
    }

    public void setAssetTypeName(String assetTypeName) {
        this.assetTypeName = assetTypeName;
    }

    public String getStaffunName() {
        return staffunName;
    }

    public void setStaffunName(String staffunName) {
        this.staffunName = staffunName;
    }

    public String getPortionBillNo() {
        return portionBillNo;
    }

    public void setPortionBillNo(String portionBillNo) {
        this.portionBillNo = portionBillNo;
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public int getAssetsStatus() {
        return assetsStatus;
    }

    public void setAssetsStatus(int assetsStatus) {
        this.assetsStatus = assetsStatus;
    }
}