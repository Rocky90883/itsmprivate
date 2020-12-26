package com.koron.web.workflowUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class WorkflowConfig {
	/**
	 * 工作流秘钥
	 */
	
	public static String WORKFLOW_SECRET;
	/**
	 * 工作流引擎服务地址
	 */
	
	public static String WORKFLOW_URL;
	
	
	public static String getWORKFLOW_URL() {
		return WORKFLOW_URL;
	}
	
	
	@Value("${workflow.config.url}")
	public  void setWORKFLOW_URL(String wORKFLOW_URL) {
		WORKFLOW_URL = wORKFLOW_URL;
	}

	public static String getWORKFLOW_SECRET() {
		return WORKFLOW_SECRET;
	}

	@Value("${workflow.config.secret}")
	public void setWORKFLOW_SECRET(String wORKFLOW_SECRET) {
		WORKFLOW_SECRET = wORKFLOW_SECRET;
	}
	

}
