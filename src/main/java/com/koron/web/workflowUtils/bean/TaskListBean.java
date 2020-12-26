package com.koron.web.workflowUtils.bean;


import com.koron.common.bean.IdentityBean;

public class TaskListBean implements IdentityBean {
	
	private Integer id;
	private String appid;
	private String name;
	
	/**
	 * 业务类型
	 */
	private String businessType;
	/**
	 * 业务分支
	 */
	private Integer businessBranch;
	/**
	 * 业务名称
	 */
	private String businessName;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 所属工作流
	 */
	private Integer workflowid;
	private String appname;
	private String workflowname;

	private String processcode;// 当前办理人
	private String currenttaskid;// 当前办理环节
	private String lastprocesstime;// 上一环节办理时间
	/**
	 * 工作流实例id
	 */
	private String proc_inst_id;
	
	private String loginid;
	/**
	 * 业务关联key
	 */
	private String businesskey;
	/**
	 * 当前节点名
	 */
	private String currenttaskname;
	/**
	 * 起草人
	 */
	private String draftsman;
	/**
	 * 实例状态 1正常运行中 2.正常结束或直接终止 4.异常结束
	 */
	private Integer status;
	/**
	 * 当前节点操作人
	 */
	private String currentOperator;
	/**
	 * 流程定义模板key
	 */
	private String templateKey;

	/**
	 * 模块类型
	 */
	private Integer modelType;


	/**
	 * 1it服务模块、2it资产模块、3it运维模块
	 * @return
	 */
	public Integer getModelType() {
		return modelType;
	}

	public void setModelType(Integer modelType) {
		this.modelType = modelType;
	}

	public String getTemplateKey() {
		return templateKey;
	}
	public void setTemplateKey(String templateKey) {
		this.templateKey = templateKey;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getWorkflowid() {
		return workflowid;
	}
	public void setWorkflowid(Integer workflowid) {
		this.workflowid = workflowid;
	}
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public String getWorkflowname() {
		return workflowname;
	}
	public void setWorkflowname(String workflowname) {
		this.workflowname = workflowname;
	}
	
	public String getProcesscode() {
		return processcode;
	}
	public void setProcesscode(String processcode) {
		this.processcode = processcode;
	}
	public String getCurrenttaskid() {
		return currenttaskid;
	}
	public void setCurrenttaskid(String currenttaskid) {
		this.currenttaskid = currenttaskid;
	}
	public String getLastprocesstime() {
		return lastprocesstime;
	}
	public void setLastprocesstime(String lastprocesstime) {
		this.lastprocesstime = lastprocesstime;
	}
	public String getProc_inst_id() {
		return proc_inst_id;
	}
	public void setProc_inst_id(String proc_inst_id) {
		this.proc_inst_id = proc_inst_id;
	}

	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getBusinesskey() {
		return businesskey;
	}
	public void setBusinesskey(String businesskey) {
		this.businesskey = businesskey;
	}
	public String getCurrenttaskname() {
		return currenttaskname;
	}
	public void setCurrenttaskname(String currenttaskname) {
		this.currenttaskname = currenttaskname;
	}
	public String getDraftsman() {
		return draftsman;
	}
	public void setDraftsman(String draftsman) {
		this.draftsman = draftsman;
	}
	public String getCurrentOperator() {
		return currentOperator;
	}
	public void setCurrentOperator(String currentOperator) {
		this.currentOperator = currentOperator;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Integer getBusinessBranch() {
		return businessBranch;
	}
	public void setBusinessBranch(Integer businessBranch) {
		this.businessBranch = businessBranch;
	}
}