package com.koron.web.workflowUtils.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkflowBean {

	
//	/**
//	 * 当前taskid
//	 */
//	@ApiModelProperty(hidden = true)
//	private String currenttaskid;

	/**
	 * taskid
	 */
	@ApiModelProperty(hidden = true)
	private String taskid;

	
	/**
	 * 审核人code
	 */
	@ApiModelProperty(value = "审核人code")
	private String auditor;

	/**
	 * 审核人名称
	 */
//	@ApiModelProperty( hidden = true)
	@ApiModelProperty(value = "审核人名称，不需要传")
	private String auditorName;

	/**
	 * 审核节点
	 */
	@ApiModelProperty(value = "选择节点")
	private String subinfo;

	/**
	 * 操作
	 * 1 驳回2.转办4.沟通8.催办 16.废弃 32.通过,64.回复沟通,128.取消沟通
	 */
	@ApiModelProperty(value = "操作")
	private Integer operation;

//	/**
//	 * 模板
//	 */
//	@ApiModelProperty(hidden = true)
//	private String template;

	/**
	 * 任务ID
	 */
	@ApiModelProperty(hidden = true)
	private String businessKey;

	/**
	 * 当前节点
	 */
	@ApiModelProperty(hidden = true)
	private String node;

	/**
	 * 评论
	 */
	@ApiModelProperty(value = "处理意见")
	private String comment;
	
	/**
	 * 工作流类型
	 * 1.我发起的
	 * 2.我待审的（包含我待办的和我可认领的）
	 * 3.参与的
	 * 4.我沟通的
	 * 5.结束的（包含我发起和我经办的）
	 */
	@ApiModelProperty(value = "opt为2时 选择 转办人")
	private String subuser;
	

	@ApiModelProperty(hidden = true)
	private String assignercode;

	/**
	 * 审核人code。 单据状态为起草节点的时候传
	 */
	@ApiModelProperty(value = "审核人code。 单据状态为驳回,节点为起草 的时候传")
	private List<String> assignercodes;


	/**
	 * 附件
	 */
	@ApiModelProperty(hidden = true)
	private String attachmentId;
	/**
	 * 可修改的节点
	 */
	@ApiModelProperty(hidden = true)
	private List<Map<String,String>> nodeOperator;
	/**
	 * 必须修改的节点
	 */
	@ApiModelProperty(hidden = true)
	private List<Map<String,String>> mnodeOperator;
	/**
	 * 实例ID
	 */
	@ApiModelProperty(value = "单据id")
	private String procInstId;
	
	/**
	 * 实例类型
	 */
	@ApiModelProperty(value = "固定标识")
	private String procInstType;
	
	/**
	 * 实例名称
	 */
	@ApiModelProperty(hidden = true)
	private String procInstName;

	
//	/**
//	 * 模块id
//	 */
//	@ApiModelProperty(hidden = true)
//	private String moduleId;
	
	/**
	 * 操作项code
	 */
	@ApiModelProperty(hidden = true)
	private String operationCode;

	/**
	 * 标识赋值
	 */
	@ApiModelProperty(hidden = true)
	private HashMap<String,Object> form ;


	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

//	public String getCurrenttaskid() {
//		return currenttaskid;
//	}
//
//	public void setCurrenttaskid(String currenttaskid) {
//		this.currenttaskid = currenttaskid;
//	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	
//	public String getTemplate() {
//		return template;
//	}
//
//	public void setTemplate(String template) {
//		this.template = template;
//	}

	public String getSubinfo() {
		return subinfo;
	}

	public void setSubinfo(String subinfo) {
		this.subinfo = subinfo;
	}

	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getSubuser() {
		return subuser;
	}

	public void setSubuser(String subuser) {
		this.subuser = subuser;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public List<Map<String, String>> getNodeOperator() {
		return nodeOperator;
	}

	public void setNodeOperator(List<Map<String, String>> nodeOperator) {
		this.nodeOperator = nodeOperator;
	}

	public List<Map<String, String>> getMnodeOperator() {
		return mnodeOperator;
	}

	public void setMnodeOperator(List<Map<String, String>> mnodeOperator) {
		this.mnodeOperator = mnodeOperator;
	}

	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	public String getProcInstType() {
		return procInstType;
	}

	public void setProcInstType(String procInstType) {
		this.procInstType = procInstType;
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

	public String getProcInstName() {
		return procInstName;
	}

	public void setProcInstName(String procInstName) {
		this.procInstName = procInstName;
	}

	public String getAssignercode() {
		return assignercode;
	}

	public void setAssignercode(String assignercode) {
		this.assignercode = assignercode;
	}

	public List<String> getAssignercodes() {
		return assignercodes;
	}

	public void setAssignercodes(List<String> assignercodes) {
		this.assignercodes = assignercodes;
	}

	public HashMap<String, Object> getForm() {
		return form;
	}

	public void setForm(HashMap<String, Object> form) {
		this.form = form;
	}


//	public String getModuleId() {
//		return moduleId;
//	}
//
//	public void setModuleId(String moduleId) {
//		this.moduleId = moduleId;
//	}

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
 
}
