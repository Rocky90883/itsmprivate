package com.koron.web.asset.assetportiondet.bean;

import com.koron.common.bean.BaseQueryBean;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * 资产分配明细
 * @author 
 */
public class AssetPortionDetQueryBean extends BaseQueryBean implements Serializable {
    private String id;


    private String orgid;


    @ApiModelProperty(value = "分配主表id")
    private String fRef;


    @ApiModelProperty(value = "资产台账id")
    private String sourceId;


    @ApiModelProperty(value = "台账资产编号")
    private String assetsNumber;


    @ApiModelProperty(value = "归属人")
    private String empId;


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



}