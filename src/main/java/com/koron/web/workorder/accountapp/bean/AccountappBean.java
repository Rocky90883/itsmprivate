package com.koron.web.workorder.accountapp.bean;

import java.io.Serializable;

import com.koron.common.bean.BaseWorkflowBean;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户账号/权限变更
 * @author 
 */
public class AccountappBean extends BaseWorkflowBean implements Serializable {
    private String id;

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
     * 申请人
     */
    @ApiModelProperty(value = "申请人")
    private String employeeId;

    /**
     * 申请人部门
     */
    @ApiModelProperty(value = "申请人部门")
    private String depatCode;

    /**
     * 联系号码
     */
    @ApiModelProperty(value = "联系号码")
    private String phone;

    /**
     * 申请类型 （1账户新增、2账户注销、3权限变更）
     */
    @ApiModelProperty(value = "申请类型 （1账户新增、2账户注销、3权限变更）")
    private String apptype;

    /**
     * 业务系统名称 跟appsys_id 保持同步
     */
    @ApiModelProperty(value = "业务系统名称")
    private String appsysName;

    /**
     * (关联应用系统清单)
     */
    @ApiModelProperty(value = "业务系统id")
    private String appsysId;

    /**
     * 申请描述
     */
    @ApiModelProperty(value = "申请描述")
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
    private String annex;

    /**
     * 流程id
     */
    private String workflowid;

    /**
     * 确认人
     */
    @ApiModelProperty(value = "指点审核人code")
    private String confirmMan;

    /**
     * 下一个审核节点
     */
    @ApiModelProperty(value = "审核节点")
    private String stage;

    /**
     * 删除标记 (0正常、-1删除)
     */
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

    public String getApptype() {
        return apptype;
    }

    public void setApptype(String apptype) {
        this.apptype = apptype;
    }

    public String getAppsysName() {
        return appsysName;
    }

    public void setAppsysName(String appsysName) {
        this.appsysName = appsysName;
    }

    public String getAppsysId() {
        return appsysId;
    }

    public void setAppsysId(String appsysId) {
        this.appsysId = appsysId;
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

    public String getConfirmMan() {
        return confirmMan;
    }

    public void setConfirmMan(String confirmMan) {
        this.confirmMan = confirmMan;
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
}