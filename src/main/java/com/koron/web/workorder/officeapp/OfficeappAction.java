package com.koron.web.workorder.officeapp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.asset.assetregist.bean.RegistSoftwareVo;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.workflowUtils.StatusMapper;
import com.koron.web.workflowUtils.WorkflowFinishInterface;
import com.koron.web.workflowUtils.WorkflowUtil;
import com.koron.web.workflowUtils.bean.MessageEntity;
import com.koron.web.workflowUtils.bean.WorkflowBean;
import com.koron.web.workflowUtils.service.WorkflowBusiness;
import com.koron.web.workorder.officeapp.bean.OfficeappBean;
import com.koron.web.workorder.officeapp.bean.OfficeappQueryBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.springframework.web.bind.annotation.*;
import org.swan.bean.MessageBean;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Api(tags = "it服务-正版软件申请")
@RequestMapping("/workorder/OfficeappAction")
@RestController
public class OfficeappAction implements WorkflowFinishInterface {

    Logger log = Logger.getLogger(OfficeappAction.class);

    @ApiOperation("---提交正版软件申请")
    @PostMapping(value = "/addOfficeapp.htm")
    public MessageBean<?> addOfficeapp(@RequestBody OfficeappBean bean) {

        @SuppressWarnings("rawtypes")
        MessageBean info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", String.class);
        try {
            if(bean.getSubmitType()==null){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "请选择保存草稿、还是提交流程", void.class);
            }
            if(bean.getSubmitType()==1  && StringUtils.isEmpty(bean.getConfirmMan())){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "流程审核人不能为空", void.class);
            }
            if (CollectionUtils.isEmpty(bean.getDet())) {
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "请选择软件", void.class);
            }
            bean.setStatus(StatusMapper.STATUS_COMMON_DRAFT);//草稿

            MessageBean<OfficeappBean> addmsg = ADOConnection.runTask(new OfficeappService(), "addOfficeapp", MessageBean.class, bean);
            if (addmsg.getCode()!=0) {
                return addmsg;
            }else{
                bean = addmsg.getData();
            }

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
            log.error("提交正版软件申请异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("提交正版软件申请异常");
            return info;
        }
        return info;
    }

    @ApiOperation("---审核前保存表单")
    @PostMapping(value = "/updateOfficeapp.htm")
    public MessageBean<?> updateOfficeapp(@RequestBody OfficeappBean bean, HttpServletRequest req) {

        MessageBean info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", String.class);
        try {
            if(bean.getSubmitType()==null){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "请选择保存草稿、还是提交流程", void.class);
            }
            //选择提交流程 但是下一步审核人为空
            if(bean.getSubmitType()==1 && StringUtils.isEmpty(bean.getWorkflowid()) && StringUtils.isEmpty(bean.getConfirmMan())){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "流程审核人不能为空", void.class);
            }

            MessageBean<OfficeappBean> updatemsg = ADOConnection.runTask(new OfficeappService(), "updateOfficeapp", MessageBean.class, bean);
            if (updatemsg.getCode()!=0) {
                return updatemsg;
            }else{
                bean = updatemsg.getData();
            }

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
            log.error("正版软件申请-审核前保存表单-异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("正版软件申请-审核前保存表单-异常");
            return info;
        }
        return info;
    }

    @ApiOperation("---删除正版软件申请")
    @PostMapping(value = "/delete.htm")
    public MessageBean<?> delete(@RequestParam("id") String id, HttpServletRequest req) {

        MessageBean info = null;
        try {

            info = ADOConnection.runTask(new OfficeappService(), "deleteOfficeapp", MessageBean.class,id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("正版软件申请-删除异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("正版软件申请-删除异常");
            return info;
        }
        return info;
    }

    @ApiOperation("---查询正版软件申请列表")
    @PostMapping(value = "/queryList.htm")
    public MessageBean<?> list(@RequestBody OfficeappQueryBean queryBean) {

        @SuppressWarnings("rawtypes")
        MessageBean<PageInfo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", PageInfo.class);
        try {

            PageHelper.startPage(queryBean.getPage(), queryBean.getPageCount());
            List<OfficeappBean> list = ADOConnection.runTask( new OfficeappService(), "queryList", List.class,queryBean);

            info.setData(new PageInfo<>(list));
        } catch (Exception e) {
            log.error("正版软件申请-列表异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("正版软件申请-列表异常");
            return info;
        }
        return info;
    }

    @ApiOperation("---查询详情")
    @PostMapping(value = "/detail.htm")
    public MessageBean<?> detail(@RequestParam("id") String id) {

        @SuppressWarnings("rawtypes")
        MessageBean<OfficeappBean> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", OfficeappBean.class);
        try {

            OfficeappBean bean = ADOConnection.runTask( new OfficeappService(), "detail", OfficeappBean.class,id);

            info.setData(bean);
        } catch (Exception e) {
            log.error("正版软件申请-查询详情", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("正版软件申请-查询详情");
            return info;
        }
        return info;
    }

    @ApiOperation("---可选择软件清单")
    @PostMapping(value = "/queryRegist.htm")
    public MessageBean<?> queryRegist() {

        @SuppressWarnings("rawtypes")
        MessageBean<List> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {

//            PageHelper.startPage(queryBean.getPage(), queryBean.getPageCount());
            List<RegistSoftwareVo> list = ADOConnection.runTask( new OfficeappService(), "queryRegist", List.class);

//            info.setData(new PageInfo<>(list));
            info.setData(list);
        } catch (Exception e) {
            log.error("正版软件申请-列表异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("正版软件申请-列表异常");
            return info;
        }
        return info;
    }

    @Override
    public Integer workflowStart(Object dataBean, StaffunBean user) {
        OfficeappBean bean =(OfficeappBean) dataBean;
        bean.setProcInstName("正版软件申请");//工作流平台 名称
        WorkflowBusiness workflowBusiness = new WorkflowBusiness(bean.getId(), user, WorkflowUtil.WORKFLOW_TYPE_OFFICEAPP);
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
        OfficeappBean OfficeappBean = new OfficeappBean();
        OfficeappBean.setId(bean.getProcInstId());
        //设置启动
        OfficeappBean.setStatus(1);//流程生效状态
        MessageBean<?> runTask = ADOConnection.runTask(new OfficeappService(), "finishDo", MessageBean.class, OfficeappBean);
        if(runTask != null){
            return WorkflowUtil.WORKFLOW_STATUS_FINISH;
        }
        else{
            return WorkflowUtil.WorkFlow_STATUS_FAILS;
        }
    }

}
