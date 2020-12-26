package com.koron.web.workorder.msgapp.bean;

import java.io.Serializable;
import java.util.Date;

import com.koron.common.bean.BaseWorkflowBean;
import io.swagger.annotations.ApiModelProperty;

/**
 * 信息资源申请
 * @author
 */
public class MsgappBean extends BaseWorkflowBean implements Serializable {
    private String id;

    private String orgId;

    /**
     * 工单号
     */
    @ApiModelProperty(value = "工单号")
    private String orderNo;

    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期")
    private String billDate;

    /**
     * 申请人
     */
    @ApiModelProperty(value = "申请人id")
    private String employeeId;

    /**
     * 部门
     */
    @ApiModelProperty(value = "部门code")
    private String depatCode;


    @ApiModelProperty(value = "联系号码")
    private String phone;

    /**
     * 台账id
     */
    @ApiModelProperty(value = "台账id")
    private String sourceId;

    /**
     * 1开通外网 2固定ip 3SSL 4VPN 5dswifi
     */
    @ApiModelProperty(value = "1开通外网 2固定ip 3SSL 4VPN 5dswifi 来源数据字典")
    private Integer apptype;

    /**
     * 申请描述
     */
    @ApiModelProperty(value = "申请描述")
    private String appContent;

    /**
     * 完成日期
     */
    @ApiModelProperty(value = "完成日期")
    private Date overTime;

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
     * 确认人
     */
    @ApiModelProperty(value = "指点审核人code")
    private String confirmMan;

    /**
     * 工作流id
     */
    private String workflowid;

    /**
     * 状态:【0:草稿/1:已提交/2:已启用/3:已停用/4:已驳回】
     */
    private Integer status;

    /**
     * 审核节点
     */
    @ApiModelProperty(value = "审核节点")
    private String stage;

    /**
     * 审批人
     */
    @ApiModelProperty(value = "审批人")
    private String auditor;

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


    @ApiModelProperty(value = "资产类型")
    private String assetTypeName;


    @ApiModelProperty(value = "资产型号")
    private String goodsModel;


    @ApiModelProperty(value = "资产编号")
    private String assetsNumber;

    private static final long serialVersionUID = 1L;

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

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
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

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
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

    public String getConfirmMan() {
        return confirmMan;
    }

    public void setConfirmMan(String confirmMan) {
        this.confirmMan = confirmMan;
    }

    public String getWorkflowid() {
        return workflowid;
    }

    public void setWorkflowid(String workflowid) {
        this.workflowid = workflowid;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    @Override
    public String getAuditor() {
        return auditor;
    }

    @Override
    public void setAuditor(String auditor) {
        this.auditor = auditor;
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