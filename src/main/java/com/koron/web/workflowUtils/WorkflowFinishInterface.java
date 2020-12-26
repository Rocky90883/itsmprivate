package com.koron.web.workflowUtils;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.workflowUtils.bean.WorkflowBean;



public interface  WorkflowFinishInterface {
	/**
	 * 审核流结束后，后面需要处理的业务
	 * @param user
	 * @param bean
	 * @return
	 */
	
	public Integer workflowFinishDo(StaffunBean user, WorkflowBean bean);
	
	/**
	 * 审核流结束之前，执行的动作
	 * @param user
	 * @param bean
	 * @return
	 */
	public default Integer workflowFinishBefore(StaffunBean user,WorkflowBean bean){
		return 0;
	};
	
	/**
	 * 启动审核流
	 * @param dataBean
	 * @param user
	 * @return
	 */
	public Integer workflowStart(Object dataBean,StaffunBean user);
	
}
