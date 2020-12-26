package com.koron.main;

import com.koron.common.web.service.DataSourceService;
import com.koron.web.workflowUtils.WorkFlowActionMap;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.swan.bean.MessageBean;

import java.util.HashMap;

public class SwanSpringApplicationRunListener implements SpringApplicationRunListener {

	private final Logger log = Logger.getLogger(this.getClass());

	public SwanSpringApplicationRunListener(SpringApplication app, String[] args) {
	}

	@Override
	public void starting() {
		System.out.println("now start!!!");
	}

	@Override
	public void environmentPrepared(ConfigurableEnvironment environment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextPrepared(ConfigurableApplicationContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextLoaded(ConfigurableApplicationContext context) {

	}

	@Override
	public void started(ConfigurableApplicationContext context) {
		// 服务启动完成后，应用结构的根节点需要创建（若已创建则跳过）
//		boolean tf = ADOConnection.runTask(new ApplicationService(), "isExitAppRoot", Boolean.class);
//		if (!tf) {
//			LongTreeBean child = new LongTreeBean();
//			child.setType(AppConstant.TYPE_APPLICATIONTREE).setForeignkey(AppConstant.APP_ROOT_NAME);
//			ADOConnection.runTask(TreeService.class, "addNode", LongTreeBean.class, null,child);

		//初始化审核流关联业务 Action
		WorkFlowActionMap.init();

		//默认启动动态数据库
//		MessageBean<HashMap> msg = ADOConnection.runTask(new DataSourceService(), "initDataSources", MessageBean.class,1);
//		String info =(String)msg.getData().get("info");
//		log.info(info);

//		}
	}

	@Override
	public void running(ConfigurableApplicationContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void failed(ConfigurableApplicationContext context, Throwable exception) {
		// TODO Auto-generated method stub
	}

}
