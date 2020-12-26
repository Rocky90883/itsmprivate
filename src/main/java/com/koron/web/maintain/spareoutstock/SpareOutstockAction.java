package com.koron.web.maintain.spareoutstock;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.maintain.spareoutstock.bean.SpareAvailableInVo;
import com.koron.web.maintain.spareoutstock.bean.SpareOutstockBean;
import com.koron.web.maintain.spareoutstock.bean.SpareOutstockQueryBean;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.workflowUtils.StatusMapper;
import com.koron.web.workflowUtils.WorkflowFinishInterface;
import com.koron.web.workflowUtils.WorkflowUtil;
import com.koron.web.workflowUtils.bean.MessageEntity;
import com.koron.web.workflowUtils.bean.WorkflowBean;
import com.koron.web.workflowUtils.service.WorkflowBusiness;
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

@Api(tags = "IT运维-备件出库")
@RequestMapping("/maintain/SpareOutstockAction")
@RestController
public class SpareOutstockAction implements WorkflowFinishInterface {

    Logger log = Logger.getLogger(SpareOutstockAction.class);

    @ApiOperation("---提交备件出库申请")
    @PostMapping(value = "/addSpareOutstock.htm")
    public MessageBean<?> addSpareOutstock(@RequestBody SpareOutstockBean bean) {

        @SuppressWarnings("rawtypes")
        MessageBean info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", String.class);
        try {
            if(bean.getSubmitType()==null){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "请选择保存草稿、还是提交流程", void.class);
            }
            if(StringUtils.isEmpty(bean.getInstockId())){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "请选择入库单号", void.class);
            }
            if(StringUtils.isEmpty(bean.getEmployeeId())){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "请选择领用人", void.class);
            }
            if(bean.getQty()==null || bean.getQty()<=0){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "数量需要大于0", void.class);
            }
            bean.setStatus(StatusMapper.STATUS_COMMON_DRAFT);//草稿

            MessageBean check = ADOConnection.runTask(new SpareOutstockService(), "check", MessageBean.class, bean);
            if (check.getCode()!=0) {
                return check;
            }
            bean = ADOConnection.runTask(new SpareOutstockService(), "addSpareOutstock", SpareOutstockBean.class,bean);
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
            log.error("提交备件出库申请异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("提交备件出库申请异常");
            return info;
        }
        return info;
    }

    @ApiOperation("---审核前保存表单")
    @PostMapping(value = "/updateSpareOutstock.htm")
    public MessageBean<?> updateSpareOutstock(@RequestBody SpareOutstockBean bean, HttpServletRequest req) {

        MessageBean info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", String.class);
        try {
            if(bean.getSubmitType()==null){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "请选择保存草稿、还是提交流程", void.class);
            }
            if(StringUtils.isEmpty(bean.getInstockId())){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "请选择入库单号", void.class);
            }
            if(StringUtils.isEmpty(bean.getEmployeeId())){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "请选择领用人", void.class);
            }
            if(bean.getQty()==null || bean.getQty()<=0){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "数量需要大于0", void.class);
            }

            bean = ADOConnection.runTask(new SpareOutstockService(), "updateSpareOutstock", SpareOutstockBean.class,bean);

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
            log.error("备件出库申请-审核前保存表单-异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("备件出库申请-审核前保存表单-异常");
            return info;
        }
        return info;
    }

    @ApiOperation("---删除备件出库申请")
    @PostMapping(value = "/delete.htm")
    public MessageBean<?> delete(@RequestParam("id") String id, HttpServletRequest req) {

        MessageBean info = null;
        try {

            info = ADOConnection.runTask(new SpareOutstockService(), "deleteSpareOutstock", MessageBean.class,id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("备件出库申请-删除异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("备件出库申请-删除异常");
            return info;
        }
        return info;
    }

    @ApiOperation("---查询备件出库申请列表")
    @PostMapping(value = "/queryList.htm")
    public MessageBean<?> list(@RequestBody SpareOutstockQueryBean queryBean, HttpServletRequest req) {

        @SuppressWarnings("rawtypes")
        MessageBean<PageInfo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", PageInfo.class);
        try {

            PageHelper.startPage(queryBean.getPage(), queryBean.getPageCount());
            List<SpareOutstockBean> list = ADOConnection.runTask( new SpareOutstockService(), "queryList", List.class,queryBean);

            info.setData(new PageInfo<>(list));
        } catch (Exception e) {
            log.error("备件出库申请-列表异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("备件出库申请-列表异常");
            return info;
        }
        return info;
    }

    @ApiOperation("---查询详情")
    @PostMapping(value = "/detail.htm")
    public MessageBean<?> detail(@RequestParam("id") String id) {

        @SuppressWarnings("rawtypes")
        MessageBean<SpareOutstockBean> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", SpareOutstockBean.class);
        try {

            SpareOutstockBean bean = ADOConnection.runTask( new SpareOutstockService(), "detail", SpareOutstockBean.class,id);

            info.setData(bean);
        } catch (Exception e) {
            log.error("备件出库申请-异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("备件出库申请-异常");
            return info;
        }
        return info;
    }

    @Override
    public Integer workflowStart(Object dataBean, StaffunBean user) {

        SpareOutstockBean bean =(SpareOutstockBean) dataBean;
        bean.setProcInstName("备件出库申请");//工作流平台 名称
        WorkflowBusiness workflowBusiness = new WorkflowBusiness(bean.getId(), user, WorkflowUtil.WORKFLOW_TYPE_SPAREOUTSTOCK);
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

    @ApiOperation("---查询可申请备品备件")
    @PostMapping(value = "/queryInstock.htm")
    public MessageBean<?> queryInstock() {

        @SuppressWarnings("rawtypes")
        MessageBean<List> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {

            List<SpareAvailableInVo> list = ADOConnection.runTask( new SpareOutstockService(), "queryInstock", List.class);

            info.setData(list);
        } catch (Exception e) {
            log.error("查询可申请备品备件-列表异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("查询可申请备品备件-列表异常");
            return info;
        }
        return info;
    }


    @Override
    public Integer workflowFinishDo(StaffunBean user, WorkflowBean bean) {
        SpareOutstockBean SpareOutstockBean = new SpareOutstockBean();
        SpareOutstockBean.setId(bean.getProcInstId());
        //设置启动
        SpareOutstockBean.setStatus(1);//流程生效状态
        MessageBean<?> runTask = ADOConnection.runTask(new SpareOutstockService(), "finishDo", MessageBean.class, SpareOutstockBean);
        if(runTask != null){
            return WorkflowUtil.WORKFLOW_STATUS_FINISH;
        }
        else{
            return WorkflowUtil.WorkFlow_STATUS_FAILS;
        }
    }



//    @ApiOperation("--测试 - 调试数量增减")
//    @PostMapping(value = "/testqty.htm")
//    public MessageBean<?> testqty(@RequestBody SpareOutstockBean bean) {
//
//        @SuppressWarnings("rawtypes")
//        MessageBean<SpareOutstockBean> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", SpareOutstockBean.class);
//        try {
//
//            info = ADOConnection.runTask( new SpareOutstockService(), "finishDo", MessageBean.class,bean);
//
//        } catch (Exception e) {
//            log.error("备件出库申请-异常", e);
//            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
//            info.setDescription("备件出库申请-异常");
//            return info;
//        }
//        return info;
//    }

}
