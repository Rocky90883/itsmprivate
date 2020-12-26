package com.koron.web.workorder.repairapp.bean;

import com.koron.common.bean.BaseWorkflowBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 报障报修
 * @author 
 */
@Data
public class RepairappBean extends BaseWorkflowBean implements Serializable {
    private String id;

    private String orgid;

    /**
     * 服务编号
     */
    @ApiModelProperty(value = "服务编号")
    private String orderNo;

    /**
     * 申请日期
     */
    @ApiModelProperty(value = "申请日期")
    private String billDate;

    /**
     * 申请人
     */
    @ApiModelProperty(value = "申请人")
    private String employeeId;

    /**
     * 所属部门
     */
    @ApiModelProperty(value = "所属部门")
    private String depatCode;

    /**
     * 联系号码
     */
    @ApiModelProperty(value = "联系号码")
    private String phone;

    /**
     * 台账id
     */
    @ApiModelProperty(value = "台账id")
    private String sourceId;

    /**
     * 硬件类 来源数据字典
     */
    @ApiModelProperty(value = "硬件类 来源数据字典")
    private Integer fixedtype;

    /**
     * 办公软件类 来源数据字典
     */
    @ApiModelProperty(value = "办公软件类 来源数据字典")
    private Integer softtype;

    /**
     * 业务系统类 来源数据字典
     */
    @ApiModelProperty(value = "业务系统类 来源数据字典")
    private Integer apptype;

    /**
     * 应用系统 id
     */
    @ApiModelProperty(value = "应用系统 id")
    private String appsysId;

    /**
     * 应用系统 名称
     */
    @ApiModelProperty(value = "应用系统 名称")
    private String appsysName;

    /**
     * 申请原因
     */
    @ApiModelProperty(value = "申请原因")
    private String appContent;

    /**
     * 完成时间
     */
    @ApiModelProperty(value = "完成时间")
    private String overTime;

    /**
     * 流程id
     */
    @ApiModelProperty(value = "流程id")
    private String workflowid;

    /**
     * 节点名称
     */
    @ApiModelProperty(value = "节点名称")
    private String stage;

    /**
     * 删除标记 (0正常、-1删除)
     */
    @ApiModelProperty(hidden = true)
    private Integer removeFlag;

    /**
     * 归属部门名称(关联显示)
     */
    @ApiModelProperty(value = "归属部门名称(关联显示)")
    private String depatName;

    /**
     * 所有者名称(关联显示)
     */
    @ApiModelProperty(value = "申请人(关联显示)")
    private String staffunName;


    @ApiModelProperty(value = "台账id")
    private String assetsId;

    @ApiModelProperty(value = "资产型号")
    private String goodsModel;

    @ApiModelProperty(value = "资产分类")
    private String assetTypeName;

    @ApiModelProperty(value = "资产编号")
    private String assetsNumber;

    private static final long serialVersionUID = 1L;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDepatCode() {
        return depatCode;
    }

    public void setDepatCode(String depatCode) {
        this.depatCode = depatCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getFixedtype() {
        return fixedtype;
    }

    public void setFixedtype(Integer fixedtype) {
        this.fixedtype = fixedtype;
    }

    public Integer getSofttype() {
        return softtype;
    }

    public void setSofttype(Integer softtype) {
        this.softtype = softtype;
    }

    public Integer getApptype() {
        return apptype;
    }

    public void setApptype(Integer apptype) {
        this.apptype = apptype;
    }

    public String getAppsysId() {
        return appsysId;
    }

    public void setAppsysId(String appsysId) {
        this.appsysId = appsysId;
    }

    public String getAppsysName() {
        return appsysName;
    }

    public void setAppsysName(String appsysName) {
        this.appsysName = appsysName;
    }

    public String getAppContent() {
        return appContent;
    }

    public void setAppContent(String appContent) {
        this.appContent = appContent;
    }

    public String getOverTime() {
        return overTime;
    }

    public void setOverTime(String overTime) {
        this.overTime = overTime;
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

    public Integer getRemoveFlag() {
        return removeFlag;
    }

    public void setRemoveFlag(Integer removeFlag) {
        this.removeFlag = removeFlag;
    }

    public String getAssetsId() {
        return assetsId;
    }

    public void setAssetsId(String assetsId) {
        this.assetsId = assetsId;
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public String getAssetTypeName() {
        return assetTypeName;
    }

    public void setAssetTypeName(String assetTypeName) {
        this.assetTypeName = assetTypeName;
    }

    public String getAssetsNumber() {
        return assetsNumber;
    }

    public void setAssetsNumber(String assetsNumber) {
        this.assetsNumber = assetsNumber;
    }
}