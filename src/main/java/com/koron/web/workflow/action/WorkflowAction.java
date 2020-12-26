package com.koron.web.workflow.action;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Joiner;
import com.koron.common.exception.ServiceException;
import com.koron.common.type.ErrorCode;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.maintain.mtproblem.MtProblemAction;
import com.koron.web.maintain.mtproblem.MtProblemService;
import com.koron.web.maintain.mtproblem.bean.MtProblemBean;
import com.koron.web.maintain.release.bean.MtReleaseBean;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.workflow.bean.AssignEmpDto;
import com.koron.web.workflow.bean.ViewEnableBean;
import com.koron.web.workflow.bean.ViewEnableQueryBean;
import com.koron.web.workflow.bean.WorkDbDto;
import com.koron.web.workflow.service.WorkflowService;
import com.koron.web.workflowUtils.WorkFlowActionMap;
import com.koron.web.workflowUtils.WorkflowUtil;
import com.koron.web.workflowUtils.bean.MessageEntity;
import com.koron.web.workflowUtils.bean.WorkflowBean;
import com.koron.web.workflowUtils.service.WorkflowBusiness;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.springframework.web.bind.annotation.*;
import org.swan.bean.MessageBean;
import springfox.documentation.annotations.ApiIgnore;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

@Api(tags = "流程相关")
@RestController
@RequestMapping("/workflow")
@SuppressWarnings("deprecation")
public class WorkflowAction {

    private static Logger log = Logger.getLogger(WorkflowAction.class);

    /**
     * 审核操作，提交到工作流
     * @return
     */
    @ApiOperation("--审核、回退、转办")
    @PostMapping("/workflowsubmit.htm")
    public String workflowsubmitApprove(@RequestBody WorkflowBean bean) {
        MessageEntity<?> msg = MessageEntity.success();
        try {
            StaffunBean user = SessionUtil.getLoginUser(); //登陆用户


            if (CollectionUtils.isNotEmpty(bean.getAssignercodes())) {
                HashMap from = new HashMap();
                //根据code 获取流程平台code
                List<String> workFlowCodes = ADOConnection.runTask(new WorkflowService(), "getWorkFlowCode", List.class, bean.getAssignercodes());
                System.out.println(Joiner.on(",").join(workFlowCodes));
                from.put("assignercode", Joiner.on(",").join(workFlowCodes));
                bean.setForm(from);
            }

            WorkflowBusiness workflowBusiness = new WorkflowBusiness(bean.getProcInstId(), user, bean.getProcInstType());
            //如果启用审核流，则提交审批
            if (workflowBusiness.getWorkflowBussionStatus() == workflowBusiness.BUSINESS_ENABLE) {

                HashMap<String, Object> form = bean.getForm();

                msg = workflowBusiness.workflowsubmit(form, bean);
                //审核流结束
                if (workflowBusiness.getWorkflowStatus() == 1) {
//                    //WorkflowFinishInterface workflowfinishInterface = getActionClass(bean.getProcInstType());
//                    //workflowfinishInterface.workflowFinishDo(user, bean);
//                  //审核流完成后进行业务处理。
                    workflowFinishDo(bean.getProcInstType(), user, bean);
                }
                return msg.toJson();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ErrorCode.WORKFLOW_ERROR);   //工作流提交失败
        }
        //返回成功消息体
        msg.setCode(-1);
        msg.setDescription("没有启有审批流");
        return msg.toJson();
    }


    /**
     * 查看任务列表，表单提交前页面
     *
     * @param procInstType 流程平台标识
     * @param procInstId   单据id
     * @return 返回的是一个前端可以用.html(respondStr)方法 展示的界面
     * @throws Exception
     */
    @ApiIgnore
    @ApiOperation("--获取流程信息")
    @GetMapping("/workflowsubmitpreMy.htm")
    public String workflowListAndsubmitpreMy(@RequestParam("procInstType") String procInstType, @RequestParam("procInstId") String procInstId) throws Exception {
        WorkflowBean bean = new WorkflowBean();
        bean.setProcInstId(procInstId);           //实例id
        bean.setProcInstType(procInstType);       //模板名称 表名称 流程平台标识
        String approve = null;
        WorkflowBusiness workflowBusiness = new WorkflowBusiness(bean.getProcInstId(), SessionUtil.getLoginUser(), bean.getProcInstType());
//        return workflowBusiness.workflowsubmitpre().toJson();
        return workflowBusiness.workflowsubmitpre();
    }
//    public String workflowListAndsubmitpre(@RequestBody WorkflowBean bean) throws Exception{

    /**
     * 查看任务列表，表单提交前页面
     *
     * @param procInstType 流程平台标识
     * @param procInstId   单据id
     * @return 返回的是一个前端可以用.html(respondStr)方法 展示的界面
     * @throws Exception
     */
    @ApiOperation("--获取流程信息")
    @GetMapping("/workflowsubmitpre.htm")
    public MessageBean workflowListAndsubmitpre(@RequestParam("procInstType") String procInstType, @RequestParam("procInstId") String procInstId) throws Exception {
        MessageBean<String> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", String.class);
        if (StringUtils.isEmpty(procInstType)) {
            MessageBean.create(Constant.MESSAGE_INT_PARAMS, "单据固定标识不能为空", String.class);
        }
        if (StringUtils.isEmpty(procInstId)) {
            MessageBean.create(Constant.MESSAGE_INT_PARAMS, "单据id不能为空", String.class);
        }
        WorkflowBean bean = new WorkflowBean();
        bean.setProcInstId(procInstId);           //实例id
        bean.setProcInstType(procInstType);       //模板名称 表名称 流程平台标识
        String approve = null;
        WorkflowBusiness workflowBusiness = new WorkflowBusiness(bean.getProcInstId(), SessionUtil.getLoginUser(), bean.getProcInstType());
//        return workflowBusiness.workflowsubmitpre().toJson();

        success.setData(workflowBusiness.workflowsubmitpre());
        return success;
    }


    /**
     * 审核流结束后，调用业务处理方法(方式二)
     *
     * @param procInstType
     * @param user
     * @param bean
     * @return
     * @throws Exception
     */
    private Integer workflowFinishDo(String procInstType, StaffunBean user, WorkflowBean bean) throws Exception {
        String className = WorkFlowActionMap.workflowFinishActionMap.get(procInstType);
        Class<?> userClass = Class.forName(className);
        Object obj = userClass.newInstance();
        Method workflowFinishDo = userClass.getMethod("workflowFinishDo", StaffunBean.class, WorkflowBean.class);//得到方法对象,有参的方法需要指定参数类型
        return (Integer) workflowFinishDo.invoke(obj, user, bean);
    }

    /**
     * 获取工作流列表
     *
     * @param type        1我发起的2我待审的（包含未办理和我可认领）3参与的(我发起、我办理、我沟通)4我沟通的5结束（包含我发起和我经办）
     * @param templateKey 模板
     * @param page        当前页
     * @param pagecount   每页条数
     * @return
     */
    @ApiOperation("--获取工作流列表 1我发起的2我待审的（包含未办理和我可认领）3参与的(我发起、我办理、我沟通)")
    @PostMapping("/getWorkflowList.htm")
    @ResponseBody
    public MessageEntity getWorkflowList(@RequestBody WorkDbDto dto) {
//    public MessageEntity getWorkflowList(@RequestParam("type") Integer type, @RequestParam(value = "procInstType",required = false)String procInstType,
//                                  @RequestParam("page") Integer page, @RequestParam("pageCount")Integer pageCount){

        if (SessionUtil.getUseerInfoCode() == null) {
            MessageEntity res = new MessageEntity();
            res.setCode(Constant.MESSAGE_INT_NOLOGIN);
            res.setDescription("获取待办需登陆");
            return res;
        }

        StaffunBean user = SessionUtil.getLoginUser();      //获取用户信息
        if (dto.getType() == 0) {
            PageHelper.startPage(dto.getPage(), dto.getPageCount());
            MessageEntity draftvo = ADOConnection.runTask(new WorkflowService(), "getviewdraft", MessageEntity.class, user, dto);
            return draftvo;
        }


        //2已结束 1未结束  0或不传  所有
        Integer status = 0;
        if (dto.getType() == 5) {
            status = 2;
        }
        if (dto.getType() == 1) {
            status = 1;
        }

//        if(dto.getType()==0){
//            MessageEntity ret = ADOConnection.runTask(new WorkflowService(), "getWorkflowList", MessageEntity.class, user, dto,status);
//        }


        MessageEntity ret = ADOConnection.runTask(new WorkflowService(), "getWorkflowList", MessageEntity.class, user, dto, status);
        return ret;
    }


    @ApiOperation("---获取已结束服务工单")
    @PostMapping(value = "/getServiceOrderNo.htm")
    public MessageBean<?> getServiceOrderNo(@RequestBody ViewEnableQueryBean queryBean) {

        @SuppressWarnings("rawtypes")
        MessageBean<PageInfo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", PageInfo.class);
        try {

            PageHelper.startPage(queryBean.getPage(), queryBean.getPageCount());
            List<ViewEnableBean> list = ADOConnection.runTask(new WorkflowService(), "getServiceOrderNo", List.class, queryBean);

            info.setData(new PageInfo<>(list));
        } catch (Exception e) {
            log.error("查询已结束服务流程-异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("查询已结束服务流程-异常");
            return info;
        }
        return info;
    }


    @ApiOperation("--分配责任人")
    @PostMapping(value = "/assignPer.htm")
    public MessageBean<?> assignPer(@RequestBody AssignEmpDto dto) {

        @SuppressWarnings("rawtypes")
        MessageBean<MtReleaseBean> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", MtReleaseBean.class);
        try {
            info = ADOConnection.runTask(new WorkflowService(), "assignPer", MessageBean.class, dto);
        } catch (Exception e) {
            log.error("分配责任人-异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("分配责任人-异常");
            return info;
        }
        return info;
    }

    @ApiOperation("--废弃(被驳回的废弃)")
    @PostMapping(value = "/abandonment.htm")
        @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "单据ID", dataType = "String"),
            @ApiImplicitParam(name = "procInstType", value = "模块类型", dataType = "String")
    })
    public String abandonment(@RequestParam("id") String id, @RequestParam("procInstType") String procInstType) {

        MessageEntity<?> msg = MessageEntity.success();
        try {
            StaffunBean user = SessionUtil.getLoginUser(); //登陆用户
            WorkflowBean bean = new WorkflowBean();
            bean.setProcInstId(id);
            bean.setProcInstType(procInstType);
            bean.setOperation(16);
            bean.setComment("");

            WorkflowBusiness workflowBusiness = new WorkflowBusiness(bean.getProcInstId(), user, bean.getProcInstType());
            //如果启用审核流，则提交审批
            if (workflowBusiness.getWorkflowBussionStatus() == workflowBusiness.BUSINESS_ENABLE) {
                //检测单据的审核状态
                int wfs = ADOConnection.runTask(new WorkflowService(), "checkWorkflowStats", Integer.class, procInstType,id);
                if(wfs!=4){
                    msg.setCode(-1);
                    msg.setDescription("流程还没未被驳回");
                    return msg.toJson();
                }
                HashMap<String, Object> form = bean.getForm();
                msg = workflowBusiness.workflowsubmit(form, bean);
                return msg.toJson();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ErrorCode.WORKFLOW_ERROR);   //工作流提交失败
        }
        //返回成功消息体
        msg.setCode(-1);
        msg.setDescription("没有启有审批流");
        return msg.toJson();
    }



    @ApiOperation("--生成问题")
    @PostMapping(value = "/generateMtProblem.htm")
    public MessageBean<?> generateMtProblem(@RequestBody MtProblemBean bean) {

        @SuppressWarnings("rawtypes")
        MessageBean info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", String.class);
        try {

            if(StringUtils.isEmpty(bean.getServiceId())){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "单据id不能为空", void.class);
            }
            if(StringUtils.isEmpty(bean.getServiceType())){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "服务类型不能为空", void.class);
            }
            bean.setEmployeeId(SessionUtil.getUseerInfoCode());
            bean.setStaffunName(SessionUtil.getUseerInfoName());

            bean = ADOConnection.runTask(new MtProblemService(), "addMtProblem", MtProblemBean.class,bean);

            if (bean.getSubmitType() == 1){
                Boolean isWorkFlow = true;
                MtProblemAction mp  = new MtProblemAction();
                Integer workflowStartResult = mp.workflowStart(bean, SessionUtil.getLoginUser());
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
            log.error("生成问题-异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("生成问题-异常");
            return info;
        }
        return info;
    }

}
