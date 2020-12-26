package com.koron.web.asset.shift.bean;

import java.io.Serializable;
import java.util.Date;

import com.koron.common.bean.BaseWorkflowBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 资产转移
 * @author 
 */
public class AssetShiftBean extends BaseWorkflowBean implements Serializable {

    private String id;

    /**
     * 组织标识
     */
    private String orgid;

    /**
     * 单据编号
     */
    @ApiModelProperty(value = "单据编号")
    private String billNo;

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    private String billDate;

    /**
     * 台账id
     */
    @ApiModelProperty(value = "台账id")
    private String sourceId;

    /**
     * 资产原归属人
     */
    @ApiModelProperty(value = "原归属人code")
    private String beforeEmpId;

    /**
     * 转移人
     */
    @ApiModelProperty(value = "接收人code")
    private String empId;

    /**
     * 转移部门
     */
    @ApiModelProperty(value = "接收人部门code")
    private String depatcode;


    @ApiModelProperty(value = "指点审核人code")
    private String confirmMan;


    @ApiModelProperty(value = "审核人名称")
    private String confirmManName;

    /**
     * 流程id
     */
    private String workflowid;


    /**
     *资产类型(关联显示)
     */
    @ApiModelProperty(value = "资产类型(关联显示)")
    private String assetTypeName;

    /**
     * 资产型号(关联显示)
     */
    @ApiModelProperty(value = "资产型号(关联显示)")
    private String goodsModel;

    /**
     * IT资产编号(关联显示)
     */
    @ApiModelProperty(value = "IT资产编号(关联显示)")
    private String assetsNumber;

    /**
     * 固定资产编号(关联显示)
     */
    @ApiModelProperty(value = "固定资产编号(关联显示)")
    private String fixedNumber;


    @ApiModelProperty(value = "节点名称")
    private String stage;

    /**
     * 所有者名称(关联显示)
     */
    @ApiModelProperty(value = "原归属人名称(关联显示)")
    private String staffunName;

    /**
     * 归属部门名称(关联显示)
     */
    @ApiModelProperty(value = "原归属人部门名称(关联显示)")
    private String depatName;


    @ApiModelProperty(value = "接收人名称(关联显示)")
    private String zystaffunName;


    @ApiModelProperty(value = "接收人部门名称(关联显示)")
    private String tydepatName;


//    @ApiModelProperty(value = "表单是否可编辑 0不编辑 1可编辑")
//    private int ifedit = 0;

    /**
     * 删除标记 (0正常、-1删除)
     */
    private int removeFlag;


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

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getBeforeEmpId() {
        return beforeEmpId;
    }

    public void setBeforeEmpId(String beforeEmpId) {
        this.beforeEmpId = beforeEmpId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getDepatcode() {
        return depatcode;
    }

    public void setDepatcode(String depatcode) {
        this.depatcode = depatcode;
    }

    public String getConfirmMan() {
        return confirmMan;
    }

    public void setConfirmMan(String confirmMan) {
        this.confirmMan = confirmMan;
    }

    public String getConfirmManName() {
        return confirmManName;
    }

    public void setConfirmManName(String confirmManName) {
        this.confirmManName = confirmManName;
    }

    public String getWorkflowid() {
        return workflowid;
    }

    public void setWorkflowid(String workflowid) {
        this.workflowid = workflowid;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public int getRemoveFlag() {
        return removeFlag;
    }

    public void setRemoveFlag(int removeFlag) {
        this.removeFlag = removeFlag;
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

    public String getZystaffunName() {
        return zystaffunName;
    }

    public void setZystaffunName(String zystaffunName) {
        this.zystaffunName = zystaffunName;
    }

    public String getTydepatName() {
        return tydepatName;
    }

    public void setTydepatName(String tydepatName) {
        this.tydepatName = tydepatName;
    }


}