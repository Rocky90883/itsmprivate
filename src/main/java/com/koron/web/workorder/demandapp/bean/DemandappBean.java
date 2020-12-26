package com.koron.web.workorder.demandapp.bean;

import com.koron.common.bean.BaseWorkflowBean;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 信息系统需求申请
 * @author 
 */
public class DemandappBean extends BaseWorkflowBean implements Serializable {
    private String id;

    @ApiModelProperty(hidden = true)
    private String orgid;

    /**
     * 工单号
     */
    @ApiModelProperty(value = "服务单号")
    private String orderNo;

    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期 必填")
    private String billDate;

    /**
     * 申请人
     */
    @ApiModelProperty(value = "申请人 必填")
    private String employeeId;

    /**
     * 部门code
     */
    @ApiModelProperty(value = "部门code 必填")
    private String depatCode;


    @ApiModelProperty(value = "联系号码")
    private String phone;

    /**
     * 应用系统id
     */
    @ApiModelProperty(value = "应用系统id （非数据字典，可选择） 必填 暂时没有先手给一个")
    private String appsysId;

    /**
     * 应用系统名称
     */
    @ApiModelProperty(value = "应用系统名称（非数据字典，可选择） 必填 暂时没有先手给一个")
    private String appsysName;

    /**
     * 申请描述
     */
    @ApiModelProperty(value = "申请描述 必填")
    private String appContent;

    /**
     * 完成时间
     */
    @ApiModelProperty(value = "完成时间")
    private String overTime;

    /**
     * 评分
     */
    @ApiModelProperty(value = "评分")
    private Double score;

    /**
     * 附件
     */
    @ApiModelProperty(hidden = true)
    private String annex;

    /**
     * 工作流id
     */
    @ApiModelProperty(value = "评分")
    private String workflowid;


    /**
     * 审核节点
     */
    @ApiModelProperty(value = "审核节点名称")
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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
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