package com.koron.web.workflow.service;

import com.github.pagehelper.PageInfo;
import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.web.maintain.mtmodify.MtModifyMapper;
import com.koron.web.maintain.mtmodify.bean.MtModifyBean;
import com.koron.web.systemmanger.user.UserMapper;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.workflow.bean.*;
import com.koron.web.workflow.mapper.ViewEnableMapper;
import com.koron.web.workflow.mapper.WorkflowMapper;
import com.koron.web.workflowUtils.WorkflowUtil;
import com.koron.web.workflowUtils.bean.MessageEntity;
import com.koron.web.workflowUtils.bean.TaskListBean;
import com.koron.web.workflowUtils.bean.TaskListVo;
import com.koron.web.workflowUtils.mapper.BaseWorkflowMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.swan.bean.MessageBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WorkflowService {

    private static Logger log =  Logger.getLogger(WorkflowService.class);

    @TaskAnnotation("getviewdraft")
    public MessageEntity<List<TaskListVo>>  getviewdraft(SessionFactory factory, StaffunBean user,WorkDbDto dto){
        ViewEnableMapper mapper = factory.getMapper(ViewEnableMapper.class);
        MessageEntity<List<TaskListVo>> res = new MessageEntity<>();    //返回页面vo
        try {
            ViewEnableQueryBean queryBean = new ViewEnableQueryBean();
            queryBean.setPage(dto.getPage());
            queryBean.setPageCount(dto.getPageCount());
            queryBean.setAppContent(dto.getAppContent());
            queryBean.setProcInstType(dto.getProcInstType());
            queryBean.setOrderNo(dto.getOrderNo());

            queryBean.setEmployeeId(user.getCode());
            List<TaskListVo>  volist = mapper.getviewdraft(queryBean);
            PageInfo<TaskListVo> info = new PageInfo<>(volist);
            res.setData(volist);
            res.setPagecount(dto.getPageCount());
            res.setPage(dto.getPage());
            res.setTotal(Integer.valueOf(String.valueOf(info.getTotal())));

        }catch (Exception e){
            e.printStackTrace();
            log.info("审核列表获取失败！");
            factory.close(false);
            res.setCode(Constant.MESSAGE_INT_OPERATION);
            res.setData(null);
        }

        return res;
    }

    /**
     * 获取待审列表
     * @param factory
     * @param user          登陆人
     * @param type          1我发起的2我待审的（包含未办理和我可认领）3参与的(我发起、我办理、我沟通)4我沟通的5结束（包含我发起和我经办）
     * @param procInstType  工作流模板
     * @param page          页码
     * @param pageCount     页数
     * @param status        2已结束 1未结束  0或不传  所有
     * @return
     */
    @TaskAnnotation("getWorkflowList")
    public MessageEntity<List<TaskListVo>>  getWorkflowList(SessionFactory factory, StaffunBean user, WorkDbDto dto, Integer status){
        BaseWorkflowMapper mapper = factory.getMapper(BaseWorkflowMapper.class);

        MessageEntity<List<TaskListBean>> ret = new MessageEntity<>();  //流程接口放回实体类
        MessageEntity<List<TaskListVo>> res = new MessageEntity<>();    //返回页面vo

        List<TaskListBean> taskList = new ArrayList<>();    //流程接口数据
        List<TaskListVo> volist = new ArrayList<>();        //返回界面数据
        try {
            List<BaseWorkflowLogoBean> logopolist = mapper.queryBaseWorkflow();
            String procInstType = "";
            //如果前端没有传模板，默认拼接全部模板
            if(StringUtils.isEmpty(dto.getProcInstType())){
                List<String> logolist = logopolist.stream().map(BaseWorkflowLogoBean::getWorkflowlogo).collect(Collectors.toList());
                procInstType = String.join(",", logolist);
            }
            int httppage = 0;
            if(dto.getPage()>=1){
                httppage = dto.getPage() - 1;
            }

            //调接口查询 个人 工作流中的待办信息
            ret = WorkflowUtil.getWorkflowList(user.getWorkflowCode(), dto.getType(),dto.getProcInstType(),httppage,dto.getPageCount(),status);
            List<TaskListBean> beanList = ret.getData();
            int totalCount = ret.getTotalCount();

            List<TaskListBean> taskBeanList = new ArrayList<>();    //接口返回的模型
            for (BaseWorkflowLogoBean workflowBean : logopolist) {
                if (!CollectionUtils.isEmpty(beanList)) {
                    //过滤获取与模板对应的审核流列表
                    taskBeanList = beanList.stream().filter(t -> t.getTemplateKey().equals(workflowBean.getWorkflowlogo())).collect(Collectors.toList());
                    //附上模板类型 业务名称 业务 code
                    if(workflowBean.getModelType()==2){
                        taskBeanList.stream().forEach(t->{
                            t.setModelType(2);
                            t.setBusinessName(workflowBean.getBusinessName());
                            t.setBusinessType(workflowBean.getBusinessCode());
                        });
                    }else{
                        taskBeanList.stream().forEach(t->{
                            t.setModelType(1);
                            t.setBusinessName(workflowBean.getBusinessName());
                            t.setBusinessType(workflowBean.getBusinessCode());
                        });
                    }
                    taskList.addAll(taskBeanList);
                }
            }
            //过滤掉无效待审数据
            for (int i = 0; i < taskList.size() ; i++) {
                int count = WorkflowService.getOriginalData(factory,taskList.get(i));
                if (count == 0){
                    taskList.remove(taskList.get(i));
                    totalCount = totalCount - 1;
                    i --;
                }else{
                    //获取具体信息
                    TaskListVo vo = WorkflowService.getOriginalDataMsg(factory, taskList.get(i));
                    volist.add(vo);
                }
            }
            res.setPage(dto.getPage());
            res.setPagecount(dto.getPageCount());
//            res.setTotalCount(totalCount);
            res.setTotal(totalCount);
//            //过滤掉无效待审数据
//            for (int i = 0; i < taskList.size() ; i++) {
//                int count = WorkflowService.getOriginalData(factory,taskList.get(i));
//                if (count == 0){
//                    taskList.remove(taskList.get(i));
//                    i --;
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
            factory.close(false);
            log.info("审核列表获取失败！");
            ret.setCode(Constant.MESSAGE_INT_OPERATION);
            ret.setData(null);
            return res;
        }

        //设置流程状态
        volist.stream().forEach(b-> b.setFlowStatusName(CommonUtil.converStatusName(b.getStatus())));

        //4.排序
        volist.sort((t1, t2) -> t2.getLastprocesstime().compareTo(t1.getLastprocesstime()));

        res.setData(volist);
//        ret.setData(taskList);
//        ret.setTotalCount(taskList.size());
        res.setCode(Constant.MESSAGE_INT_SUCCESS);
        res.setDescription("获取待审列表");//修改返回描述
        return res;
    }

    /**
     * 获取原始数据数目
     * @param bean
     * @return
     */
    public static Integer getOriginalData(SessionFactory factory, TaskListBean bean){
        BaseWorkflowMapper mapper = factory.getMapper(BaseWorkflowMapper.class);
        int count = mapper.getOriginalData(bean);
        return count;
    }

    /**
     * 获取原始数据数目
     * @param bean
     * @return
     */
    public static TaskListVo getOriginalDataMsg(SessionFactory factory, TaskListBean bean){
        BaseWorkflowMapper mapper = factory.getMapper(BaseWorkflowMapper.class);
        TaskListVo vo = new TaskListVo();

        //服务 or 运维
        if(bean.getModelType()==1){
            vo = mapper.getOriginalDataMsgApp(bean).get(0);
        }
        //资产
        if(bean.getModelType()==2){
            if(bean.getBusinessType().equals("asset_shift")){
                vo = mapper.getOriginalDataMsgtoShift(bean).get(0);
            }else{
                vo = mapper.getOriginalDataMsgtoAsset(bean).get(0);
            }
        }
        vo.setDraftsman(bean.getDraftsman());                   //起草人
        vo.setProcInstType(bean.getBusinessType());             //业务标识
        vo.setProcInstName(bean.getBusinessName());             //业务名称
        vo.setCurrenttaskname(bean.getCurrenttaskname());       //当前节点名称
        vo.setLastprocesstime(bean.getLastprocesstime());       //上一环节办理时间
        return vo;
    }


    /**
     * 根据code 获取流程平台code
     * @param factory
     * @param codelist
     * @return
     */
    @TaskAnnotation("getWorkFlowCode")
    public List<String> getWorkFlowCode(SessionFactory factory, List<String> codelist) {
        List<String> workflowcodes = new ArrayList<>();
        try {
            for (String code : codelist){
                String workFlowCode = CommonUtil.getWorkFlowCode(factory, code);
                workflowcodes.add(workFlowCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return workflowcodes;
    }

    /**
     * 申请人、审核人装箱
     * @param factory
     * @param volist
     * @return
     */
    private static List<TaskListVo> packeMsg(SessionFactory factory,List<TaskListVo> volist){
        try {
//            List<StaffunBean> allstaffun = factory.getMapper(UserMapper.class).getAllWorkFlowStaffun();
//
            volist.stream().forEach(b->{
                //转换流程状态
                b.setFlowStatusName(CommonUtil.converStatusName(b.getStatus()));
                //设置审核人名称
//                b.setAuditorName(CommonUtil.getDsrNameNew(allstaffun,b.getAuditor()));
                //设置申请人名称
//                b.setStaffunName(CommonUtil.getStaffNameBycodepacke(allstaffun,b.getEmployeeId()));
            });

        } catch (Exception e) {
            e.printStackTrace();
            log.error("申请人、审核人装箱异常");
            throw new RuntimeException(e.getMessage());
        }
        return volist;
    }


    /**
     * 视图-服务单号
     * @param factory
     * @param querybean
     * @return
     */
    @TaskAnnotation("getServiceOrderNo")
    public List<ViewEnableBean> getServiceOrderNo(SessionFactory factory, ViewEnableQueryBean querybean) {
        List<ViewEnableBean> list = new ArrayList<>();
        try {
            list = factory.getMapper(ViewEnableMapper.class).queryList(querybean);
            List<StaffunBean> allstaffun = factory.getMapper(UserMapper.class).getAllWorkFlowStaffun();
            list.stream().forEach(v->{
                v.setStaffunName(CommonUtil.getStaffNameBycodepacke(allstaffun,v.getEmployeeId()));
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }


    /**
     * 分配责任人
     * @param factory
     * @param dto
     * @return
     */
    @TaskAnnotation("assignPer")
    public MessageBean assignPer(SessionFactory factory, AssignEmpDto dto) {
        MessageBean<String> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", String.class);
        try {
            WorkflowMapper wfmapper = factory.getMapper(WorkflowMapper.class);
            UserMapper usermapper = factory.getMapper(UserMapper.class);

            //如果人员为空，则清空责任人
            if(StringUtils.isEmpty(dto.getAssignEmpid())){
                wfmapper.assignper(dto);
                success.setData(dto.getId());
                return success;
            }
            StaffunBean staffunbean = usermapper.getStaffByLoginId(dto.getAssignEmpid());
            if(staffunbean!=null) {
                dto.setAssignName(staffunbean.getName());
                wfmapper.assignper(dto);
            }
            success.setData(dto.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return success;
    }


    /**
     * 检测单据审核状态
     * @param factory
     * @param tableName
     * @param id
     * @return
     */
    @TaskAnnotation("checkWorkflowStats")
    public Integer checkWorkflowStats(SessionFactory factory, String tableName, String id ) {
//        状态:【/1:启用/2:草稿/3审核中/4:已驳回/5:废弃/6:转办中】
        Integer status = 3;
        try {
            WorkflowMapper mapper = factory.getMapper(WorkflowMapper.class);
            status = mapper.getworkflowStatus(tableName, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return status;
    }
}
