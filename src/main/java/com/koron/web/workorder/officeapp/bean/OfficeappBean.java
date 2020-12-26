package com.koron.web.workorder.officeapp.bean;

import com.koron.common.bean.BaseWorkflowBean;
import com.koron.web.workorder.officeapp.officeappdet.bean.OfficeappdetBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 正版软件申请
 * @author 
 */
public class OfficeappBean extends BaseWorkflowBean implements Serializable {
    private String id;

    @ApiModelProperty(hidden=true)
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
    private String assetsId;


    @ApiModelProperty(value = "资产编号")
    private String assetsNumber;


    @ApiModelProperty(value = "资产类型（关联显示）")
    private String assetTypeName;


    @ApiModelProperty(value = "资产型号（关联显示）")
    private String gModel;

    /**
     * 软件操作类型1安装、2卸载
     */
    @ApiModelProperty(value = "软件操作类型1安装、2卸载 来源数据字典")
    private Integer apptype;

    /**
     * 申请原因
     */
    @ApiModelProperty(value = "申请原因")
    private String appContent;

    /**
     * 完成时间
     */
    @ApiModelProperty(hidden = true)
    private String overTime;

    /**
     * 流程id
     */
    @ApiModelProperty(hidden = true)
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


    @ApiModelProperty(value = "起草节点指点审核人code")
    private String confirmMan;

    @ApiModelProperty(value = "软件明细")
    private List<OfficeappdetBean> det;

    private static final long serialVersionUID = 1L;

    public List<OfficeappdetBean> getDet() {
        return det;
    }

    public void setDet(List<OfficeappdetBean> det) {
        this.det = det;
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

    public String getAssetsId() {
        return assetsId;
    }

    public void setAssetsId(String assetsId) {
        this.assetsId = assetsId;
    }

    public String getAssetsNumber() {
        return assetsNumber;
    }

    public void setAssetsNumber(String assetsNumber) {
        this.assetsNumber = assetsNumber;
    }

    public String getAssetTypeName() {
        return assetTypeName;
    }

    public void setAssetTypeName(String assetTypeName) {
        this.assetTypeName = assetTypeName;
    }

    public String getgModel() {
        return gModel;
    }

    public void setgModel(String gModel) {
        this.gModel = gModel;
    }

    public Integer getApptype() {
        return apptype;
    }

    public void setApptype(Integer apptype) {
        this.apptype = apptype;
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
}