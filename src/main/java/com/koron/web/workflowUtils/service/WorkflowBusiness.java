package com.koron.web.workflowUtils.service;

import java.util.HashMap;
import java.util.List;

import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.workflow.bean.WorkflowResultBean;
import com.koron.web.workflow.service.WorkflowBusinessService;
import com.koron.web.workflowUtils.WorkflowUtil;
import com.koron.web.workflowUtils.bean.MessageEntity;
import com.koron.web.workflowUtils.bean.TaskListBean;
import com.koron.web.workflowUtils.bean.WorkflowBean;
import org.koron.ebs.mybatis.ADOConnection;
import org.swan.bean.MessageBean;

import com.google.gson.Gson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 处理业务库的审核流业务
 * @author 付浩强
 * @date 2019-01-23
 */
public class WorkflowBusiness {
	
	public  final Integer BUSINESS_DISABLE = 0; 
	public  final Integer BUSINESS_ENABLE = 1;
	
	/**
	 * 当前需要处理审核流的记录id
	 */
	private String billId= null ;
	
	/**
	 * 处理审核流的当前用户
	 */
	private StaffunBean user = null;
	
	/**
	 * 业务库的审核流标识
	 */
	private String businessCode = null;
	
	/**
	 * 取审核流平台的审核流标识
	 */
	private String workflowLogo = null;
	
	/**
	 *  工作流实例的状态（审核中，驳回，审核结束等）
	 */
	private Integer workflowStatus = 0;
	
//	/**
//	 * 实验室id
//	 */
//	private String laboratory = null;
	
	/**
	 * 审核流实例id
	 */
	private String workFlowId = null;
	
	private String nextWorkFlowName=null;
	
	
	/**
	 * 工作流业务有没有启用审核流程  0停用，1启用
	 */
    private Integer workflowBussionStatus = BUSINESS_ENABLE;
	
	
	/**
	 * 工作流业务初始化
	 * @param billId 业务单据的id
	 * @param user 审核流用户名称
	 * @param workflowLogo 审核流平台的审核流标识
	 */
	public WorkflowBusiness(String billId,StaffunBean user,String workflowLogo){
		this.billId = billId;
		this.user = user;
		this.workflowLogo = workflowLogo;
		Init();
	}
	
		
	/**
	 * 初始化审核流信息
	 */
	@SuppressWarnings("unchecked")
	private void Init(){

		//获取审核流的ID,在审核阶段，应可以取以审核流的id，在创建单据记录之后，启动审核流之前应该为空
		MessageBean<String> msg1 = ADOConnection.runTask(new WorkflowBusinessService(),"getWorkflowId", MessageBean.class, this.workflowLogo, this.billId);

		if(msg1.getCode() == 0){
			this.workFlowId = msg1.getData();
		}

				
	}
	
	/**
	 * 获取工作流列表
//	 * @param staffcode 人员code
//	 * @param type 1我发起的2我待审的（包含未办理和我可认领）3参与的(我发起、我办理、我沟通)4我沟通的5结束（包含我发起和我经办）
//	 * @param template  模板
//	 * @param page 当前页
//	 * @param pagecount 每页条数
	 * @return
	 * @throws Exception
	 */
	public MessageEntity<List<TaskListBean>> getWorkflowList(Integer type, Integer page, Integer pagecount) throws Exception{
		return WorkflowUtil.getWorkflowList(this.user.getWorkflowCode(),type,this.workflowLogo,page,pagecount,0);
	}
	
	/**
	 * 获取工作流图表
	 * @param businessKey 实例ID
	 * @return
	 * @throws Exception
	 */
	public MessageEntity<?> getWorkflowsvg(String businessKey) throws Exception{
		return WorkflowUtil.getWorkflowsvg(businessKey);
	}
	
	/**
	 * 工作流办理推送数据
//	 * @param staffcode 操作人
//	 * @param form 表单数据
//	 * @param LabWorkflowBean
	 * @return
	 * @throws Exception
	 */
public MessageEntity<?> workflowsubmit(HashMap<String,Object> form,WorkflowBean bean) throws Exception{
		//没有启用审核流的情况下，调此接口无效。
		if(workflowBussionStatus != 1){
			MessageEntity<String> msg = new MessageEntity<>(); 
		    msg.setCode(WorkflowUtil.WORKFLOW_STATUS_DISABEL );
		    msg.setDescription("未启用审核");
		    return msg;
		}
		
		try {
			WorkflowResultBean workflowResult = getWorkflowNextNodeInfo();
			if(bean.getTaskid() == null || bean.getTaskid().equals("")){
				//判断节点是否结束，获取下一步节点id
				if(workflowResult!=null && !workflowResult.getNextactivitiName().equals("结束")){
					String taskId = getWorkflowNextNodeInfo().getNexttaskId();
					bean.setTaskid(taskId);
				}
				else{
					MessageEntity<String> msg = new MessageEntity<>(); 
				    msg.setCode(WorkflowUtil.WORKFLOW_STATUS_FINISH);
				    msg.setDescription("审核流已结束");
				    return msg;
				}
			}
			
			MessageEntity<?> retMsg = WorkflowUtil.workflowsubmit(this.user.getWorkflowCode(),form, bean);
			MessageEntity<WorkflowResultBean> result = new MessageEntity<>();
			if(retMsg.getCode() == 0){
				//workflowResult = getWorkflowNextNodeInfo();
				if(workflowResult != null){
					workflowResult = writeWorkflowInfo();//拿到获取的流程平台的id，重新调接口获取流程信息
					result.setCode(0);
					result.setData(workflowResult);
					result.setDescription("提交审核成功");
					return result;
				}
			}
			return retMsg;
		} catch (Exception e) {
		    e.printStackTrace();
			throw new Exception("获取审核流任务id失败.");
		}
	}
	
	/**
	 * 工作流提交前所需要表单
//	 * @param staffcode 用户code
//	 * @param template 工作流模板
//	 * @param taskid 任务ID
	 * @return
	 * @throws Exception
	 */
	public String workflowsubmitpre() throws Exception{
		String nexttaskId = "" ;
		MessageEntity<String> ret = new MessageEntity<>();
		try {
			WorkflowResultBean workflowResult = getWorkflowNextNodeInfo();
			if(workflowResult!=null){
				nexttaskId = workflowResult.getNexttaskId();
				String from = WorkflowUtil.workflowsubmitpre( this.user.getWorkflowCode(), this.workflowLogo, nexttaskId,this.workFlowId);
				if(from !=null &&  !from.equals("")){
					ret.setCode(0);
					ret.setDescription("获取审核流表单成功");
				    ret.setData(from);
				    return from ;
				}
			}
			ret.setCode(-1);
			ret.setDescription("获取审核流表单信息失败");
//			return ret ;
			return "获取审核流表单信息失败,检查是否登陆" ;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("获取下一结点的任务id失败");
		}
	}
//    public MessageEntity<?> workflowsubmitpre() throws Exception{
	
	/**
	 * 启动工作流实例并完成起草节点
//	 * @param businessKey 业务Key
//	 * @param processKey 流程模板key  工作流标识
//	 * @param staffcode 人员
//	 * @param variables 参数
//	 * @param LabWorkflowBean bean
	 * @return
	 * @throws Exception 
	 */
	public  Integer StartWorkflow(WorkflowBean bean) throws Exception{
		
		//没有启用审核流的情况下，调此接口无效。
		HashMap<String,Object> variables = new HashMap<>();
		if(workflowBussionStatus != WorkflowUtil.WORKFLOW_STATUS_ENABEL ){
			return WorkflowUtil.WORKFLOW_STATUS_DISABEL;
		}
		
		variables.put("processInstance_Name", bean.getProcInstName());
		MessageEntity<?> msg  = WorkflowUtil.createProcessInstance(this.billId, this.workflowLogo,this.user.getWorkflowCode(), variables, bean);
		System.out.println(msg);
		if(msg.getCode() == 0){
			this.workFlowId = msg.getData().toString();
			writeWorkflowInfo();
			//工作流启动后，状态为审核中
			return WorkflowUtil.WORKFLOW_STATUS_CHECKING;
		}
		else{
			//启动审核流失败
			return WorkflowUtil.WorkFlow_STATUS_FAILS;
		}
		
	}
	
	/**
	 * 获取单据流转数据(获取当前工作流所有节点列表，以及下一个节点信息)
//	 * @param procInsId
	 * @return
	 * @throws Exception
	 */
	public  String getWorkflowhistorys() throws Exception {
		return WorkflowUtil.getWorkflowhistorys(this.workFlowId);
	}
	
	/**
	 * 获取节点信息
	 * taskId 为空默认返回第一个用户节点信息
//	 * @param processKey 流程实例ID  流程模板key  工作流标识
	 * @return
	 * @throws Exception 
	 */
	public  MessageEntity<HashMap<String,Object>> workflowNodeInfo() throws Exception{
		return workflowNodeInfo(null);
	}
	
	/**
	 * 获取节点信息
//	 * @param template 流程实例ID   流程模板key  工作流标识
	 * @param taskId 任务id     工作流id
	 * @return
	 * @throws Exception 
	 */
	public  MessageEntity<HashMap<String,Object>> workflowNodeInfo(String taskId) throws Exception{
		return  WorkflowUtil.workflowNodeInfo(this.workflowLogo,taskId);
	}

    /**
     * 根据this.workFlowId 调接口获取流程信息，写库
     * @return
     * @throws Exception
     */
	private WorkflowResultBean writeWorkflowInfo() throws Exception{
		
		String ret = getWorkflowhistorys();//拿到获取的流程平台的id，重新调接口获取流程信息
	    JSONObject jsonObject = JSONObject.fromObject(ret);
	    String data = jsonObject.get("data").toString();
	    JSONArray jsonArray = JSONArray.fromObject(data);
	    if(jsonArray.size()>0){
	    	WorkflowResultBean result = new Gson().fromJson(jsonArray.getString(0), WorkflowResultBean.class);
	    	result.setId(this.billId);
	    	result.setProcessinsId(this.workFlowId);
	    	result.setTableName(this.workflowLogo);
	    	this.nextWorkFlowName = result.getNextactivitiName();
	    	if(result.getNextactivitiName().equals("结束")) {
	    		this.workflowStatus = 1 ;
	    	}
	    	else{
	    		this.workflowStatus = 0 ; //审核中
	    	}

	    	//以下是保存流程信息到本地库
//	    	@SuppressWarnings("unchecked")
			MessageBean<Integer> msg = ADOConnection.runTask(new WorkflowBusinessService(),"updateWorkflowInfo", MessageBean.class, result);
	    	
	    	return result;
	    }
	    else {
	    	return null ;
		}
		
	}

	private WorkflowResultBean getWorkflowNextNodeInfo() throws Exception{
		String ret = getWorkflowhistorys();
	    JSONObject jsonObject = JSONObject.fromObject(ret);
	    String data = jsonObject.get("data").toString();
	    JSONArray jsonArray = JSONArray.fromObject(data);
	    if(jsonArray.size()>0){
	    	WorkflowResultBean result = new Gson().fromJson(jsonArray.getString(0), WorkflowResultBean.class);
	    	result.setId(this.billId);
	    	result.setProcessinsId(this.workFlowId);
	    	result.setTableName(this.businessCode);
	    	if(result.getNextactivitiName().equals("结束")) {
	    		this.workflowStatus = 1 ;
	    	}
	    	else{
	    		this.workflowStatus = 0 ;
	    	}
	    	return result ;
	    }
	    else{
	    	return null;
	    }
	}
	
	/**
	 * 工作流实例的状态
	 * @return
	 */
	public Integer getWorkflowStatus() {
		return workflowStatus;
	}

	/**
	 * 工作流配置的状态
	 * @return
	 */
	public Integer getWorkflowBussionStatus() {
		return workflowBussionStatus;
	}

	public String getNextWorkFlowName() {
		return nextWorkFlowName;
	}

	public void setNextWorkFlowName(String nextWorkFlowName) {
		this.nextWorkFlowName = nextWorkFlowName;
	}


	public String getWorkFlowId() {
		return workFlowId;
	}


	
}
