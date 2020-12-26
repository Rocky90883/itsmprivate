package com.koron.web.workflowUtils;

import java.util.HashMap;


public class WorkFlowActionMap {
	public static HashMap <String,String> workflowFinishActionMap = new HashMap<>();
	public static void init(){


		workflowFinishActionMap.put("scrapped", "com.koron.web.asset.scrapped.ScrappedAction");  //资产报废
		workflowFinishActionMap.put("asset_shift", "com.koron.web.asset.shift.AssetShiftAction");//资产变更

		workflowFinishActionMap.put("accountapp", "com.koron.web.workorder.accountapp.AccountappAction");//账号权限申请
		workflowFinishActionMap.put("dataccessapp", "com.koron.web.workorder.dataccessapp.DataccessappAction");//数据接入申请
		workflowFinishActionMap.put("demandapp", "com.koron.web.workorder.demandapp.DemandappAction");//信息系统需求申请
		workflowFinishActionMap.put("msgapp", "com.koron.web.workorder.msgapp.MsgappAction");//信息资源申请
		workflowFinishActionMap.put("officeapp", "com.koron.web.workorder.officeapp.OfficeappAction");//正版软件申请
		workflowFinishActionMap.put("repairapp", "com.koron.web.workorder.repairapp.RepairappAction");//报障报修申请

		workflowFinishActionMap.put("mt_modify", "com.koron.web.maintain.mtmodify.MtModifyAction");//变更管理申请
		workflowFinishActionMap.put("mt_release", "com.koron.web.maintain.release.MtReleaseAction");//发布管理申请
		workflowFinishActionMap.put("mt_problem", "com.koron.web.maintain.mtproblem.MtProblemAction");//问题管理申请
		workflowFinishActionMap.put("spare_instock", "com.koron.web.maintain.spareinstock.SpareInstockAction");//备件入库申请
		workflowFinishActionMap.put("spare_outstock", "com.koron.web.maintain.spareoutstock.SpareOutstockAction");//备件出库申请

	}
}

