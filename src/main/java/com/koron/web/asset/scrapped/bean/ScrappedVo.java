package com.koron.web.asset.scrapped.bean;

import com.koron.common.bean.BaseWorkflowBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * scrapped
 * @author 
 */
@Data
public class ScrappedVo extends BaseWorkflowBean {
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
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期")
    private String billDate;

    /**
     * 台账id
     */
    @ApiModelProperty(value = "台账id")
    private String sourceId;

    /**
     * 报废人
     */
    @ApiModelProperty(value = "报废人")
    private String employeeId;

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

    /**
     * 归属部门名称(关联显示)
     */
    @ApiModelProperty(value = "归属部门名称(关联显示)")
    private String depatName;

    /**
     * 所有者名称(关联显示)
     */
    @ApiModelProperty(value = "所有者名称(关联显示)")
    private String staffunName;

    /**
     * 确认人
     */
    @ApiModelProperty(value = "转办人")
    private String confirmMan;

    /**
     * 确认人名称
     */
    @ApiModelProperty(value = "转办人名称")
    private String confirmManName;


    @ApiModelProperty(value = "节点名称")
    private String stage;


    @ApiModelProperty(value = "流程状态名称")
    private String flowStatusName;


    @ApiModelProperty(value = "备注")
    private String remark;

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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }


}