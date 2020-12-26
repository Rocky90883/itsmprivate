package com.koron.web.workflowUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import com.koron.web.workflowUtils.bean.MessageEntity;
import com.koron.web.workflowUtils.bean.StaffInfoBean;
import com.koron.web.workflowUtils.bean.TaskListBean;
import com.koron.web.workflowUtils.bean.WorkflowBean;
import com.koron.web.workflowUtils.mapper.DepartmentMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.koron.ebs.mybatis.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.stereotype.Component;



/**
 * 工作流接口
 * @author Administrator
 *
 */
@Configuration
@Component  
public class WorkflowUtil {
	
	@Value("${workflow.config.secret}")
    public String WORKFLOW_CONFIG_SECRET ;
	@Value("${workflow.config.url}")
    public String WORKFLOW_CONFIG_URL;

	private static Logger log = Logger.getLogger(WorkflowUtil.class);

	public WorkflowUtil(){};
	
	/**
	 * 通过
	 */
	public static final Integer OPERATION_PASS =1;
	
	public static final Integer WORKFLOW_STATUS_DISABEL  = 0; //未启动审核流
	public static final Integer WORKFLOW_STATUS_ENABEL   = 1; //启用审核流
	public static final Integer WorkFlow_STATUS_SUCESS   = 2; //审核流启动成功
	public static final Integer WORKFLOW_STATUS_CHECKING = 3; //审核中       
	public static final Integer WorkFlow_STATUS_FAILS    = 4; //工作流启动失败 
	public static final Integer WORKFLOW_STATUS_FINISH   = 5; //审核结束

	public static final String WORKFLOW_TYPE_SCRAPPED = "scrapped";//资产报废
	public static final String WORKFLOW_TYPE_SHIFT = "asset_shift";//资产转移

	public static final String WORKFLOW_TYPE_ACCOUNTAPP = "accountapp";//账号权限申请
	public static final String WORKFLOW_TYPE_MSGAPP = "msgapp";//信息资源申请
	public static final String WORKFLOW_TYPE_DATAACCAPP = "dataccessapp";//数据接入申请
	public static final String WORKFLOW_TYPE_DEMANDAPP = "demandapp";//信息系统需求申请
	public static final String WORKFLOW_TYPE_REPAIRAPP = "repairapp";//报障报修申请
	public static final String WORKFLOW_TYPE_OFFICEAPP = "officeapp";//正版软件申请

	public static final String WORKFLOW_TYPE_MTMONDIFY = "mt_modify";//变更管理申请
	public static final String WORKFLOW_TYPE_MTRELEASE = "mt_release";//发布管理申请
	public static final String WORKFLOW_TYPE_MTPROBLEM = "mt_problem";//问题管理申请

	public static final String WORKFLOW_TYPE_SPAREINSTOCK = "spare_instock";//备件入库
	public static final String WORKFLOW_TYPE_SPAREOUTSTOCK = "spare_outstock";//备件出库


	private static ObjectMapper objectMapper = new ObjectMapper();
	/**
	 * b)获取工作流模板列表
	 */
	private static final String TEMPLATE_URL ="/port/template.htm";
	/**
	 * c)获取工作流列表
	 */
	private static final String WORKFLOW_URL="/port/workflow.htm";
	/**
	 * e)工作流办理推送数据
	 */
	private static final String SUBMIT_URL= "/port/workflowsubmit.htm";
	/*
	 * f)工作流图表
	 */
	private static final String SVG_URL= "/port/workflowsvg.htm";
	/**
	 * g)工作流提交前所需要表单
	 */
	private static final String SUBMITPRE_URL= "/port/workflowsubmitpre.htm";
	
	
	/**
	 * 工作流历史结点列表
	 */
	private static final String HISTORY_URL= "/port/workflowhistory.htm";
	
	
	/**
	 * h)启动工作流实例
	 */
	private static final String CREATE_PROCESS_URL= "/port/startProcessInstance.htm";
	/**
	 * i)节点信息
	 */
	private static final String NODEINFO_URL=  "/port/workflowNodeInfo.htm";
	
	/**
	 * 获取工作流模板列表
	 * @param page 开始页
	 * @param pagecount 每页记录数
	 * @throws Exception
	 */
	public static MessageEntity<?> getWorkflowTemplate(Integer page, Integer pagecount) throws Exception{
		String param ="secret=" + WorkflowConfig.WORKFLOW_SECRET;
		param += "&page=" + page ;
		param += "&pagecount="+pagecount;
		String ret = HttpUtils.sendGet(WorkflowConfig.WORKFLOW_URL+ TEMPLATE_URL, param);
		return objectMapper.readValue(ret, MessageEntity.class);
	};
	

	/**
	 * 获取工作流列表
	 * @param staffcode 人员code
	 * @param type 1我发起的2我待审的（包含未办理和我可认领）3参与的(我发起、我办理、我沟通)4我沟通的5结束（包含我发起和我经办）
	 * @param template  模板
	 * @param page 当前页
	 * @param pagecount 每页条数
	 * @return
	 * @throws Exception
	 */
	public static MessageEntity<List<TaskListBean>> getWorkflowList(String staffcode, Integer type, String template, Integer page, Integer pagecount,Integer status) throws Exception{
		
		String param ="secret=" + WorkflowConfig.WORKFLOW_SECRET;
		if(StringUtils.isNotEmpty(template)){
			param += "&templatekey=" + template ;
		}
		if(type == null ){
			throw new Exception("type 参数错误");
		}
		//type 1我发起的 2待审 3参与的 4沟通 5结束
		param += "&type="+type;
		if(StringUtils.isEmpty(staffcode)){
			throw new Exception("staffcode 参数错误");
		}
		param += "&staffcode=" + staffcode ;
		param += "&page="+page;
		param += "&pagecount=" + pagecount ;
		param += "&status=" + status ;
		String ret = HttpUtils.sendGet(WorkflowConfig.WORKFLOW_URL + WORKFLOW_URL, param);
		return objectMapper.readValue(ret,objectMapper.getTypeFactory().constructParametricType(MessageEntity.class
				,objectMapper.getTypeFactory().constructParametricType(List.class,TaskListBean.class)));
	}
	
	/**
	 * 获取工作流图表
	 * @param businessKey 实例ID
	 * @return
	 * @throws Exception
	 */
	public static MessageEntity<?> getWorkflowsvg(String businessKey) throws Exception{
		
		String param ="secret=" + WorkflowConfig.WORKFLOW_SECRET;
		param +="&businessKey=" + businessKey;
		String ret = HttpUtils.sendGet(WorkflowConfig.WORKFLOW_URL + SVG_URL, param);
		return objectMapper.readValue(ret, MessageEntity.class);
		
	}
	/**
	 * 工作流办理推送数据
	 * @param staffcode 操作人
	 * @param form 表单数据
	 * @param LabWorkflowBean  
	 * @return
	 * @throws Exception
	 */
	public static MessageEntity<?> workflowsubmit(String staffcode,HashMap<String,Object> form,WorkflowBean bean) throws Exception{
		String param ="secret=" + WorkflowConfig.WORKFLOW_SECRET;
		param +="&staffcode=" + staffcode;
		if(bean.getOperation() == null || bean.getOperation() <= 0){
			throw new Exception("operation 参数错误");
		}
		// operation 1 驳回2.转办4.沟通8.催办 16.废弃 32.通过,64.回复沟通,128.取消沟通
		param +="&operation=" + bean.getOperation();
		if(StringUtils.isEmpty(bean.getTaskid())){
			throw new Exception("taskid 参数错误");
		}
		//taskid 任务id
		param +="&taskid=" + bean.getTaskid();
		if(form!=null && form.size() > 0){
			param +="&formstr=" +  objectMapper.writeValueAsString(form);
		}
		//comment 评语 (驳回，沟通，回复沟通必须填)
		if(StringUtils.isEmpty(bean.getComment()) && (bean.getOperation() ==1||
				bean.getOperation() ==4 || bean.getOperation() == 64)){
			throw new Exception("comment 参数错误");
		}
		param +="&comment=" + bean.getComment();
		//operation为1时 subinfo为nodekey,
		if(bean.getOperation() == 1 && StringUtils.isEmpty(bean.getSubinfo())){
			throw new Exception("subinfo 参数错误");
		}
		param +="&subinfo=" + bean.getSubinfo();
		//operation为2,4,128 时该参数必须有,该参数为user数组，
		if((bean.getOperation() == 2||bean.getOperation() == 4||bean.getOperation() == 128)
				&& StringUtils.isEmpty(bean.getSubuser())){
			throw new Exception("subuser 参数错误");
		}
		param +="&subuser=" +  StringUtils.join(split(bean.getSubuser()),",");
		//可修改节点或者必须修改节点参数
		String nextCandidateUsers = operator(bean.getNodeOperator(),bean.getMnodeOperator());
		if(StringUtils.isNotEmpty(nextCandidateUsers)){
			param +="&nextCandidateUsers=" + nextCandidateUsers;
		}
		//上传附件
		if(StringUtils.isNotEmpty(bean.getAttachmentId())){
			param += "&attachmentId=" + bean.getAttachmentId();
		}
		log.debug(WorkflowConfig.WORKFLOW_URL + SUBMIT_URL);
		log.debug(param);

		String ret = HttpUtils.sendPost(WorkflowConfig.WORKFLOW_URL + SUBMIT_URL, param);
		log.debug(ret);
		return objectMapper.readValue(ret, MessageEntity.class);
	}
	/**
	 * 工作流提交前所需要表单
	 * @param staffcode 用户code
	 * @param template 工作流模板
	 * @param taskid 任务ID
	 * @return
	 * @throws Exception
	 */
	public static String workflowsubmitpre(String staffcode,String template,String taskid,String processinst) throws Exception{
		String param ="secret=" + WorkflowConfig.WORKFLOW_SECRET;
		if(StringUtils.isNotEmpty(taskid)){
			param +="&taskid=" + taskid;
		}
		if(StringUtils.isNotEmpty(processinst)){
			param +="&processinst=" + processinst;
		}
		
		if(StringUtils.isEmpty(template)){
			throw new Exception("templateKey 参数错误");
		}
		param +="&staffcode="+staffcode;
		param +="&template="+template;
		return HttpUtils.sendPost(WorkflowConfig.WORKFLOW_URL + SUBMITPRE_URL, param);
	}                                                                     
	/**
	 * 启动工作流实例并完成起草节点
	 * @param businessKey 业务Key
	 * @param processKey 流程模板key  工作流标识
	 * @param staffcode 人员
	 * @param variables 参数
	 * @param LabWorkflowBean bean 
	 * @return
	 * @throws Exception 
	 */
	public static MessageEntity<?> createProcessInstance(String businessKey,String processKey,String staffcode,HashMap<String,Object> variables,WorkflowBean bean) throws Exception{
		String param ="secret=" + WorkflowConfig.WORKFLOW_SECRET;
		param +="&businessKey=" + businessKey;
		param +="&processKey=" + processKey;
		param +="&staffcode=" + staffcode;
		String nextCandidateUsers = operator(bean.getNodeOperator(),bean.getMnodeOperator());
		if(StringUtils.isNotEmpty(nextCandidateUsers)){
			param +="&nextCandidateUsers=" + nextCandidateUsers;
		}
		variables.putAll(convertBean(bean));
		param +="&variables=" + objectMapper.writeValueAsString(variables);
		//上传附件
		if(StringUtils.isNotEmpty(bean.getAttachmentId())){
			param += "&attachmentId=" + bean.getAttachmentId();
		}
		
		String ret = HttpUtils.sendPost(WorkflowConfig.WORKFLOW_URL + CREATE_PROCESS_URL, param);
		return objectMapper.readValue(ret, MessageEntity.class);
	}
	
	/**
	 * 获取单据流转数据(获取当前工作流所有节点列表，以及下一个节点信息)
	 * @param procInsId
	 * @return
	 * @throws Exception
	 */
	public static String getWorkflowhistorys(String procInsId) throws Exception {
		String param = "secret=" + WorkflowConfig.WORKFLOW_SECRET;
		param += "&procInstId=" + procInsId;
		String ret = HttpUtils.sendPost(WorkflowConfig.WORKFLOW_URL + HISTORY_URL, param);
		return ret;
	}
	
	/**
	 * 获取节点信息
	 * taskId 为空默认返回第一个用户节点信息
	 * @param processKey 流程实例ID  流程模板key  工作流标识
	 * @return
	 * @throws Exception 
	 */
	public static MessageEntity<HashMap<String,Object>> workflowNodeInfo(String processKey) throws Exception{
		return workflowNodeInfo(processKey,null);
	}
	
	/**
	 * 获取节点信息
	 * @param template 流程实例ID   流程模板key  工作流标识
	 * @param taskId 任务id     工作流id
	 * @return
	 * @throws Exception 
	 */
	public static MessageEntity<HashMap<String,Object>> workflowNodeInfo(String template,String taskId) throws Exception{
		String param ="secret=" + WorkflowConfig.WORKFLOW_SECRET;
		param +="&template=" + template;
		if(StringUtils.isNotEmpty(taskId))
			param +="&taskid=" + taskId;
		String ret = HttpUtils.sendPost(NODEINFO_URL, param);
		return objectMapper.readValue(ret,objectMapper.getTypeFactory().constructParametricType(MessageEntity.class
				,objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, Object.class)));
	}
	
	
	private static String operator(List<Map<String,String>> nodeOperator,List<Map<String,String>> mnodeOperator ){
		Map<String,String> njo = new HashMap<>();
		if((mnodeOperator!=null && mnodeOperator.size() > 0)
				||(nodeOperator!=null && nodeOperator.size() > 0)){
			mnodeOperator.removeAll(nodeOperator);
			mnodeOperator.addAll(nodeOperator);
			for (Map<String,String> el : mnodeOperator) {
				for (Map.Entry<String, String> entity : el.entrySet()) {
					if(StringUtils.isEmpty(entity.getValue())){
						continue;
					}
					njo.put(entity.getKey(), StringUtils.join(split(entity.getValue()), "-"));
				}
			}
		}
		if(njo == null || njo.size() == 0)
			return null;
		try {
			return objectMapper.writeValueAsString(njo);
		} catch (IOException e) {
		}
		return null;
	}
	private static List<String> split(String users) {
		List<String> list = new ArrayList<>();
		if (users == null)
			return list;
		try (SessionFactory factory = new SessionFactory()) {
			DepartmentMapper staff = factory.getMapper(DepartmentMapper.class);
			String[] userArray = users.split(",");
			for (String user : userArray) {
				if (user.startsWith("1-"))
					list.add(user.substring(2));
				else if(user.startsWith("2-")) {
					List<StaffInfoBean> info = staff.getStaffOfDemartment(user.substring(2));
					List<String> depts = info.stream().map((item) -> item.getLoginid()).collect(Collectors.toList());
					list.addAll(depts);
				}
			}
		}
		return new ArrayList<String>(new HashSet<>(list));
	}
	   /** 
     * 将一个 JavaBean 对象转化为一个  Map 
     * @param bean 要转化的JavaBean 对象 
     * @return 转化出来的  Map 对象 
     * @throws IntrospectionException 如果分析类属性失败 
     * @throws IllegalAccessException 如果实例化 JavaBean 失败 
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败 
     */ 
	private static Map<String,Object> convertBean(Object bean) 
            throws IntrospectionException, IllegalAccessException, InvocationTargetException { 
        Class<?> type = bean.getClass(); 
        Map<String,Object> returnMap = new HashMap<>(); 
        BeanInfo beanInfo = Introspector.getBeanInfo(type); 

        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
        for (int i = 0; i< propertyDescriptors.length; i++) { 
            PropertyDescriptor descriptor = propertyDescriptors[i]; 
            String propertyName = descriptor.getName(); 
            if (!propertyName.equals("class")) { 
                Method readMethod = descriptor.getReadMethod(); 
                Object result = readMethod.invoke(bean, new Object[0]); 
                if (result != null) { 
                    returnMap.put(propertyName, result); 
                }
            } 
        } 
        return returnMap; 
    } 
    

}
