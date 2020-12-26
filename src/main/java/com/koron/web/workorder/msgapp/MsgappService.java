package com.koron.web.workorder.msgapp;

import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.systemmanger.user.UserMapper;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.workflowUtils.mapper.BaseWorkflowMapper;
import com.koron.web.workorder.msgapp.bean.MsgappBean;
import com.koron.web.workorder.msgapp.bean.MsgappQueryBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.swan.bean.MessageBean;

import java.util.List;

@Service
public class MsgappService {

    private static Logger log = Logger.getLogger(MsgappService.class);

    /**
     * 信息资源申请列表
     * @param factory
     * @param queryBean
     * @return
     */
    @TaskAnnotation("queryList")
    public List<MsgappBean> queryList(SessionFactory factory, MsgappQueryBean queryBean) {
        List<MsgappBean> msgapplist;
        try {
            MsgappMapper mapper = factory.getMapper(MsgappMapper.class);
            msgapplist = mapper.queryList(queryBean);

//            List<StaffunBean> allstaffun = factory.getMapper(UserMapper.class).getAllWorkFlowStaffun();

            msgapplist.stream().forEach(b->{
                //转换流程状态
                b.setFlowStatusName(CommonUtil.converStatusName(b.getStatus()));
                //设置审核人名称
//                b.setAuditorName(CommonUtil.getDsrName(factory,b.getAuditor()));
//                b.setAuditorName(CommonUtil.getDsrNameNew(allstaffun,b.getAuditor()));
                //表单审核过程不允许修改
                b.setWorkflowIsApprover(CommonUtil.checkedit(b.getAuditor()));
            });

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return msgapplist;
    }

    /**
     * 详情
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("detail")
    public MsgappBean detail(SessionFactory factory, String id) {
        try {
            MsgappMapper mapper = factory.getMapper(MsgappMapper.class);
            MsgappBean bean = mapper.selectByPrimaryKey(id);
            if(bean!=null){
                bean.setProcInstType("msgapp");
                bean.setWorkflowIsApprover(CommonUtil.checkedit(bean.getAuditor()));
            }

            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 新增信息资源申请
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("addMsgapp")
    public MsgappBean addMsgapp(SessionFactory factory, MsgappBean bean) {
        String id = CommonUtil.get32Key();
        try {
            bean.setId(id);
            MsgappMapper mapper = factory.getMapper(MsgappMapper.class);


            //设置单据编号
            String maxOrderNo = mapper.maxOrderNo();//获取当天最大的服务单号
            String billNo = CommonUtil.generateStream("MS", "", maxOrderNo);
            bean.setOrderNo(billNo);
            bean.setScore(null);    //新增的评分都为空
            bean.setRemoveFlag(0);

            bean.setCreateAccount(SessionUtil.getUseerInfoCode());
            bean.setCreateName(SessionUtil.getUseerInfoName());
            bean.setCreateTime(CommonUtil.getCurrentTime());

            //如果状态是提交==1 ，才需要设置审核人
            if(bean.getSubmitType()==1){
//            MessageBean<?> msgb = getUserCode(factory, bean.getConfirmMan());
//            bean.setAssignercode(String.valueOf(msgb.getData()));
                String workflowcodes = CommonUtil.getWorkFlowCodesBySplit(factory, bean.getConfirmMan());
                bean.setAssignercode(workflowcodes);                        //设置审核人
            }
            mapper.insertSelective(bean);                               //写库
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 删除信息资源申请
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("deleteMsgapp")
    public MessageBean deleteMsgapp(SessionFactory factory, String id) {

        try {
            MsgappMapper mapper = factory.getMapper(MsgappMapper.class);
            MsgappBean bean = mapper.selectByPrimaryKey(id);
            //工作流状态不等于 草稿 或 不等于 废弃 ，不允许删除
            if(bean.getStatus()!=null && bean.getStatus()!=2 && bean.getStatus()!=5){
                return MessageBean.create(Constant.MESSAGE_WORKDELERROR, "已存在工作流 不允许删除", void.class);
            }
            mapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 更新信息资源申请
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("updateMsgapp")
    public MsgappBean updateMsgapp(SessionFactory factory, MsgappBean bean) {

        try {
            MsgappMapper mapper = factory.getMapper(MsgappMapper.class);

            //根据实例id获取相应的原来bean
            MsgappBean oldbean = mapper.selectByPrimaryKey(bean.getId());
            bean.setWorkflowid(oldbean.getWorkflowid());

            //如果状态是提交==1
            if(bean.getSubmitType()==1 ){
                //且 流程还没启动 才需要设置审核人 (草稿提交审核)
//                if(StringUtils.isEmpty(oldbean.getWorkflowid())){
//                    String workflowcodes = CommonUtil.getWorkFlowCodesBySplit(factory, bean.getConfirmMan());
//                    bean.setAssignercode(workflowcodes);                        //设置审核人
//                }

                bean.setUpdateAccount(SessionUtil.getUseerInfoCode());
                bean.setUpdateName(SessionUtil.getUseerInfoName());
                bean.setUpdateTime(CommonUtil.getCurrentTime());
                mapper.updateByPrimaryKeySelective(bean);                   //写库
                return bean;
            }

            //如果状态是草稿==2 只是修改单据     (草稿保存草稿)
            if(bean.getSubmitType()==2 && StringUtils.isEmpty(oldbean.getWorkflowid())){
                bean.setUpdateAccount(SessionUtil.getUseerInfoCode());
                bean.setUpdateName(SessionUtil.getUseerInfoName());
                bean.setUpdateTime(CommonUtil.getCurrentTime());
                mapper.updateByPrimaryKeySelective(bean);                   //写库
                return bean;
            }

            log.debug("审批触发信息资源申请修改  调试");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return bean;
    }


    /**
     * 流程结束后调用
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("finishDo")
    public MessageBean finishDo(SessionFactory factory, MsgappBean bean) {
        try {
            MsgappMapper mapper = factory.getMapper(MsgappMapper.class);

            //根据实例id获取相应的报废bean
            MsgappBean oldbean = mapper.selectByPrimaryKey(bean.getId());

            if(oldbean.getStage()!=null && oldbean.getStage().equals("结束")){
                log.debug("进入信息资源申请 审核结束逻辑");
                oldbean.setStatus(bean.getStatus());
                oldbean.setUpdateAccount(SessionUtil.getUseerInfoCode());
                oldbean.setUpdateName(SessionUtil.getUseerInfoName());
                oldbean.setUpdateTime(CommonUtil.getCurrentTime());

                mapper.updateByPrimaryKeySelective(oldbean);   //写库
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }




}
