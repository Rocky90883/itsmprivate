package com.koron.web.workorder.msgapp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.workflowUtils.StatusMapper;
import com.koron.web.workflowUtils.WorkflowFinishInterface;
import com.koron.web.workflowUtils.WorkflowUtil;
import com.koron.web.workflowUtils.bean.MessageEntity;
import com.koron.web.workflowUtils.bean.WorkflowBean;
import com.koron.web.workflowUtils.service.WorkflowBusiness;
import com.koron.web.workorder.msgapp.bean.MsgappBean;
import com.koron.web.workorder.msgapp.bean.MsgappQueryBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.springframework.web.bind.annotation.*;
import org.swan.bean.MessageBean;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Api(tags = "it服务-信息资源申请")
@RequestMapping("/workorder/msgappAction")
@RestController
public class MsgappAction implements WorkflowFinishInterface {

    Logger log = Logger.getLogger(MsgappAction.class);

    @ApiOperation("---提交信息资源申请")
    @PostMapping(value = "/addMsgapp.htm")
    public MessageBean<?> addMsgapp(@RequestBody MsgappBean bean) {

        @SuppressWarnings("rawtypes")
        MessageBean info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", String.class);
        try {
            if(bean.getSubmitType()==null){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "请选择保存草稿、还是提交流程", void.class);
            }
            if(bean.getSubmitType()==1  && StringUtils.isEmpty(bean.getConfirmMan())){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "流程审核人不能为空", void.class);
            }
            if(StringUtils.isEmpty(bean.getSourceId())){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "请选择资产", void.class);
            }
            bean.setStatus(StatusMapper.STATUS_COMMON_DRAFT);//草稿
            bean = ADOConnection.runTask(new MsgappService(), "addMsgapp", MsgappBean.class,bean);
            bean.setId(bean.getId());

            //提交数据
            if (bean.getSubmitType() == 1){
                Boolean isWorkFlow = true;
                Integer workflowStartResult = workflowStart(bean, SessionUtil.getLoginUser());
                if(workflowStartResult == WorkflowUtil.WORKFLOW_STATUS_DISABEL){
                    //如果未启用审核流则过账
                    bean.setStatus(1);
                }
                else if (workflowStartResult == WorkflowUtil.WORKFLOW_STATUS_CHECKING){
                    //设置业务状态为审核中
                    bean.setStatus(3);
                }
                else if (workflowStartResult == WorkflowUtil.WorkFlow_STATUS_FAILS){
                    //工作流未能成功启动
                    isWorkFlow = false;
                }
                if(!isWorkFlow){
                    info = new MessageBean<>();
                    info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
                    info.setDescription("审核流程启动失败！无法提交成功");
                }
            }
            info.setData(bean.getId());
        } catch (Exception e) {
            log.error("提交信息资源申请异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("提交信息资源申请异常");
            return info;
        }
        return info;
    }

    @ApiOperation("---审核前保存表单")
    @PostMapping(value = "/updateMsgapp.htm")
    public MessageBean<?> updateMsgapp(@RequestBody MsgappBean bean, HttpServletRequest req) {

        MessageBean info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", String.class);
        try {
            if(bean.getSubmitType()==null){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "请选择保存草稿、还是提交流程", void.class);
            }
            //选择提交流程 但是下一步审核人为空
//            if(bean.getSubmitType()==1 && StringUtils.isEmpty(bean.getWorkflowid()) && StringUtils.isEmpty(bean.getConfirmMan())){
//                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "流程审核人不能为空", void.class);
//            }

            bean = ADOConnection.runTask(new MsgappService(), "updateMsgapp", MsgappBean.class,bean);

            //提交数据
            if (bean.getSubmitType() == 1 && StringUtils.isEmpty(bean.getWorkflowid())){
                Boolean isWorkFlow = true;
                Integer workflowStartResult = workflowStart(bean, SessionUtil.getLoginUser());
                if(workflowStartResult == WorkflowUtil.WORKFLOW_STATUS_DISABEL){
                    //如果未启用审核流则过账
                    bean.setStatus(1);
                }
                else if (workflowStartResult == WorkflowUtil.WORKFLOW_STATUS_CHECKING){
                    //设置业务状态为审核中
                    bean.setStatus(3);
                }
                else if (workflowStartResult == WorkflowUtil.WorkFlow_STATUS_FAILS){
                    //工作流未能成功启动
                    isWorkFlow = false;
                }
                if(!isWorkFlow){
                    info = new MessageBean<>();
                    info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
                    info.setDescription("审核流程启动失败！无法提交成功");
                }
            }
            info.setData(bean.getId());
        } catch (Exception e) {
            log.error("信息资源申请-审核前保存表单-异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("信息资源申请-审核前保存表单-异常");
            return info;
        }
        return info;
    }

    @ApiOperation("---删除信息资源申请")
    @PostMapping(value = "/delete.htm")
    public MessageBean<?> delete(@RequestParam("id") String id, HttpServletRequest req) {

        MessageBean info = null;
        try {

            info = ADOConnection.runTask(new MsgappService(), "deleteMsgapp", MessageBean.class,id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("信息资源申请-删除异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("信息资源申请-删除异常");
            return info;
        }
        return info;
    }

    @ApiOperation("---查询信息资源申请列表")
    @PostMapping(value = "/queryList.htm")
    public MessageBean<?> list(@RequestBody MsgappQueryBean queryBean, HttpServletRequest req) {

        @SuppressWarnings("rawtypes")
        MessageBean<PageInfo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", PageInfo.class);
        try {

            PageHelper.startPage(queryBean.getPage(), queryBean.getPageCount());
            List<MsgappBean> list = ADOConnection.runTask( new MsgappService(), "queryList", List.class,queryBean);

            info.setData(new PageInfo<>(list));
        } catch (Exception e) {
            log.error("信息资源申请-列表异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("信息资源申请-列表异常");
            return info;
        }
        return info;
    }

    @ApiOperation("---查询详情")
    @PostMapping(value = "/detail.htm")
    public MessageBean<?> detail(@RequestParam("id") String id) {

        @SuppressWarnings("rawtypes")
        MessageBean<MsgappBean> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", MsgappBean.class);
        try {

            MsgappBean bean = ADOConnection.runTask( new MsgappService(), "detail", MsgappBean.class,id);

            info.setData(bean);
        } catch (Exception e) {
            log.error("信息资源申请-查询详情", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("信息资源申请-查询详情");
            return info;
        }
        return info;
    }

    @Override
    public Integer workflowStart(Object dataBean, StaffunBean user) {
        MsgappBean bean =(MsgappBean) dataBean;
        bean.setProcInstName("信息资源申请");//工作流平台 名称
        WorkflowBusiness workflowBusiness = new WorkflowBusiness(bean.getId(), user, WorkflowUtil.WORKFLOW_TYPE_MSGAPP);
        if(workflowBusiness.getWorkflowBussionStatus() == 1 &&
                (workflowBusiness.getWorkFlowId() != null && !workflowBusiness.getWorkFlowId().equals(""))){
            try {
                bean.setOperation(32);//驳回再提交时，操作类型设置32为通过
                HashMap<String,Object> map = new HashMap<>();
                map.put("assignercode", bean.getAssignercode());
                MessageEntity<?> workflowsubmit = workflowBusiness.workflowsubmit(map, bean);
                System.out.println(workflowsubmit.toJson());
                return WorkflowUtil.WORKFLOW_STATUS_CHECKING;
            } catch (Exception e) {
                return WorkflowUtil.WorkFlow_STATUS_FAILS;
            }
        }
        //以下是单据添加保存后第一次提交审核
        if(workflowBusiness.getWorkflowBussionStatus() == 1){
            try {
                return workflowBusiness.StartWorkflow(bean);
            } catch (Exception e) {
                e.printStackTrace();
                return WorkflowUtil.WorkFlow_STATUS_FAILS;
            }
        }
        else{
            return WorkflowUtil.WORKFLOW_STATUS_DISABEL;
        }
    }


    @Override
    public Integer workflowFinishDo(StaffunBean user, WorkflowBean bean) {
        MsgappBean MsgappBean = new MsgappBean();
        MsgappBean.setId(bean.getProcInstId());
        //设置启动
        MsgappBean.setStatus(1);//流程生效状态
        MessageBean<?> runTask = ADOConnection.runTask(new MsgappService(), "updatemsgapp", MessageBean.class, MsgappBean);
        if(runTask != null){
            return WorkflowUtil.WORKFLOW_STATUS_FINISH;
        }
        else{
            return WorkflowUtil.WorkFlow_STATUS_FAILS;
        }
    }

}
