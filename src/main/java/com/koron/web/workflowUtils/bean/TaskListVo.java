package com.koron.web.workflowUtils.bean;


import io.swagger.annotations.ApiModelProperty;

public class TaskListVo  {
	
	private String id;

	private String orderNo;


	private String appContent;

	@ApiModelProperty(value = "业务标识")
	private String procInstType;


	@ApiModelProperty(value = "业务名称")
	private String procInstName;


	private String employeeId;
	private String staffunName;

	/**
	 * 起草人
	 */
	private String draftsman;


	private String billDate;

	/**
	 * 当前节点名
	 */
	private String currenttaskname;

	/**
	 * 上一环节办理时间
	 */
	private String lastprocesstime;

	private String auditor;
	private String auditorName;

	private Integer status;
	private String flowStatusName;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAppContent() {
		return appContent;
	}

	public void setAppContent(String appContent) {
		this.appContent = appContent;
	}

	public String getProcInstType() {
		return procInstType;
	}

	public void setProcInstType(String procInstType) {
		this.procInstType = procInstType;
	}

	public String getProcInstName() {
		return procInstName;
	}

	public void setProcInstName(String procInstName) {
		this.procInstName = procInstName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getStaffunName() {
		return staffunName;
	}

	public void setStaffunName(String staffunName) {
		this.staffunName = staffunName;
	}

	public String getDraftsman() {
		return draftsman;
	}

	public void setDraftsman(String draftsman) {
		this.draftsman = draftsman;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getLastprocesstime() {
		return lastprocesstime;
	}

	public void setLastprocesstime(String lastprocesstime) {
		this.lastprocesstime = lastprocesstime;
	}

	public String getCurrenttaskname() {
		return currenttaskname;
	}

	public void setCurrenttaskname(String currenttaskname) {
		this.currenttaskname = currenttaskname;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFlowStatusName() {
		return flowStatusName;
	}

	public void setFlowStatusName(String flowStatusName) {
		this.flowStatusName = flowStatusName;
	}
}