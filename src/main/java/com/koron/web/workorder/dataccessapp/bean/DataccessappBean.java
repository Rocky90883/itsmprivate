package com.koron.web.workorder.dataccessapp.bean;

import java.io.Serializable;
import java.util.Date;

import com.koron.common.bean.BaseWorkflowBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


public class DataccessappBean extends BaseWorkflowBean implements Serializable {

    private String id;

    @ApiModelProperty(hidden = true)
    private String orgid;

    /**
     * 服务单号
     */
    @ApiModelProperty(value = "服务单号")
    private String orderNo;

    /**
     * 申请日期
     */
    @ApiModelProperty(value = "申请日期")
    private String billDate;

    /**
     * 人员code
     */
    @ApiModelProperty(value = "人员code")
    private String employeeId;

    /**
     * 部门code
     */
    @ApiModelProperty(value = "部门code")
    private String depatCode;

    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式")
    private String phone;

    /**
     * 业务类型 1物联数据接入 2分支机构VPN接入 3网络扩容
     */
    @ApiModelProperty(value = "业务类型 1物联数据接入 2分支机构VPN接入 3网络扩容 来源数据字典")
    private Integer apptype;

    /**
     * 申请内容
     */
    @ApiModelProperty(value = "申请内容")
    private String appContent;


    @ApiModelProperty(value = "完成时间")
    private String overTime;

    /**
     * 评分
     */
    @ApiModelProperty(value = "评分")
    private Integer score;

    /**
     * 附件
     */
    @ApiModelProperty(hidden = true)
    private String annex;

    /**
     * 流程id
     */
    @ApiModelProperty(hidden = true)
    private String workflowid;

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

    /**
     * 审核节点
     */
    @ApiModelProperty(value = "当前审核节点")
    private String stage;


    @ApiModelProperty(value = "起草节点指点审核人code")
    private String confirmMan;

    /**
     * 删除标记 (0正常、-1删除)删除标记
     */
    @ApiModelProperty(hidden = true)
    private Integer removeFlag;


    private static final long serialVersionUID = 1L;




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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getAnnex() {
        return annex;
    }

    public void setAnnex(String annex) {
        this.annex = annex;
    }

    public String getWorkflowid() {
        return workflowid;
    }

    public void setWorkflowid(String workflowid) {
        this.workflowid = workflowid;
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


    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getOverTime() {
        return overTime;
    }

    public void setOverTime(String overTime) {
        this.overTime = overTime;
    }

    public Integer getRemoveFlag() {
        return removeFlag;
    }

    public void setRemoveFlag(Integer removeFlag) {
        this.removeFlag = removeFlag;
    }
}