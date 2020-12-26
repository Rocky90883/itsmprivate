package com.koron.web.workorder.demandapp;

import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.systemmanger.user.UserMapper;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.workflowUtils.mapper.BaseWorkflowMapper;
import com.koron.web.workorder.demandapp.bean.DemandappBean;
import com.koron.web.workorder.demandapp.bean.DemandappQueryBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.swan.bean.MessageBean;

import java.util.List;

@Service
public class DemandappService {

    private static Logger log = Logger.getLogger(DemandappService.class);

    /**
     * 信息系统需求申请列表
     * @param factory
     * @param queryBean
     * @return
     */
    @TaskAnnotation("queryList")
    public List<DemandappBean> queryList(SessionFactory factory, DemandappQueryBean queryBean) {
        List<DemandappBean> Demandapplist;
        try {
            DemandappMapper mapper = factory.getMapper(DemandappMapper.class);
            Demandapplist = mapper.queryList(queryBean);

//            UserMapper usermapper = factory.getMapper(UserMapper.class);
//            List<StaffunBean> allStaffun = usermapper.getAllWorkFlowStaffun();

            Demandapplist.stream().forEach(b->{
                //转换流程状态
                b.setFlowStatusName(CommonUtil.converStatusName(b.getStatus()));
                //设置审核人名称
//                b.setAuditorName(CommonUtil.getDsrName(factory,b.getAuditor()));
//                b.setAuditorName(CommonUtil.getDsrNameNew(allStaffun,b.getAuditor()));
                //表单审核过程不允许修改
//                b.setIfedit(CommonUtil.checkedit(b.getAuditor()));
                b.setWorkflowIsApprover(CommonUtil.checkedit(b.getAuditor()));
            });

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return Demandapplist;
    }

    /**
     * 详情
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("detail")
    public DemandappBean detail(SessionFactory factory, String id) {
        try {
            DemandappMapper mapper = factory.getMapper(DemandappMapper.class);
            DemandappBean bean = mapper.selectByPrimaryKey(id);
            if(bean!=null){
                bean.setProcInstType("demandapp");
                bean.setWorkflowIsApprover(CommonUtil.checkedit(bean.getAuditor()));
            }

            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 新增信息系统需求申请
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("addDemandapp")
    public DemandappBean addDemandapp(SessionFactory factory, DemandappBean bean) {
        String id = CommonUtil.get32Key();
        try {
            bean.setId(id);
            DemandappMapper mapper = factory.getMapper(DemandappMapper.class);


            //设置单据编号
            String maxOrderNo = mapper.maxOrderNo();//获取当天最大的单据编号
            String orderNo = CommonUtil.generateStream("XT", "", maxOrderNo);
            bean.setOrderNo(orderNo);
            bean.setRemoveFlag(0);

            bean.setCreateAccount(SessionUtil.getUseerInfoCode());
            bean.setCreateName(SessionUtil.getUseerInfoName());
            bean.setCreateTime(CommonUtil.getCurrentTime());

//            MessageBean<?> msgb = getUserCode(sessionFactory, bean.getConfirmMan());
//            bean.setAssignercode(String.valueOf(msgb.getData()));
            String workflowcodes = CommonUtil.getWorkFlowCodesBySplit(factory, bean.getConfirmMan());
            bean.setAssignercode(workflowcodes);                        //设置审核人
            mapper.insertSelective(bean);                               //写库
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    
    /**
     * 删除信息系统需求申请
     * @param sessionFactory
     * @param id
     * @return
     */
    @TaskAnnotation("deleteDemandapp")
    public MessageBean deleteDemandapp(SessionFactory sessionFactory, String id) {

        try {
            DemandappMapper mapper = sessionFactory.getMapper(DemandappMapper.class);
            DemandappBean bean = mapper.selectByPrimaryKey(id);
            //工作流状态不等于 草稿 ，不允许删除
            if(bean.getStatus()!=null && bean.getStatus()!=2 ){
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
     * 更新信息系统需求申请
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("updateDemandapp")
    public DemandappBean updateDemandapp(SessionFactory factory, DemandappBean bean) {

        try {
            DemandappMapper mapper = factory.getMapper(DemandappMapper.class);

            //根据实例id获取相应的报废bean
            DemandappBean oldbean = mapper.selectByPrimaryKey(bean.getId());
//            bean.set .setWorkflowid(oldbean.getWorkflowid());

            //如果状态是提交==1
            if(bean.getSubmitType()==1 ){
                //且 流程还没启动 才需要设置审核人 (草稿提交审核)
                if(StringUtils.isEmpty(oldbean.getWorkflowid())){
                    String workflowcodes = CommonUtil.getWorkFlowCodesBySplit(factory, bean.getConfirmMan());
                    bean.setAssignercode(workflowcodes);                        //设置审核人
                }

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

            log.debug("审批触发信息系统需求申请修改  调试");

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
    public MessageBean finishDo(SessionFactory factory, DemandappBean bean) {
        try {
            DemandappMapper mapper = factory.getMapper(DemandappMapper.class);

            //根据实例id获取相应的报废bean
            DemandappBean oldbean = mapper.selectByPrimaryKey(bean.getId());

            if(oldbean.getStage()!=null && oldbean.getStage().equals("结束")){
                log.debug("进入信息系统需求申请 审核结束逻辑");
                oldbean.setOverTime(CommonUtil.getCurrentTime());
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
