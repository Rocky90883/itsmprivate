package com.koron.web.workorder.repairapp;

import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.asset.assets.assetsrepdet.AssetsRepdetMapper;
import com.koron.web.asset.assets.assetsrepdet.bean.AssetsRepdetBean;
import com.koron.web.systemmanger.user.UserMapper;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.workorder.repairapp.bean.RepairappBean;
import com.koron.web.workorder.repairapp.bean.RepairappQueryBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.swan.bean.MessageBean;

import java.util.List;

@Service
public class RepairappService {

    private static Logger log = Logger.getLogger(RepairappService.class);

    /**
     * 报障报修申请列表
     * @param factory
     * @param queryBean
     * @return
     */
    @TaskAnnotation("queryList")
    public List<RepairappBean> queryList(SessionFactory factory, RepairappQueryBean queryBean) {
        List<RepairappBean> Repairapplist;
        try {
            RepairappMapper mapper = factory.getMapper(RepairappMapper.class);
            Repairapplist = mapper.queryList(queryBean);

//            UserMapper usermapper = factory.getMapper(UserMapper.class);
//            List<StaffunBean> allStaffun = usermapper.getAllWorkFlowStaffun();

            Repairapplist.stream().forEach(b->{
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
        return Repairapplist;
    }

    /**
     * 详情
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("detail")
    public RepairappBean detail(SessionFactory factory, String id) {
        try {
            RepairappMapper mapper = factory.getMapper(RepairappMapper.class);
            RepairappBean bean = mapper.selectByPrimaryKey(id);
            if(bean!=null){
                bean.setProcInstType("repairapp");
                bean.setWorkflowIsApprover(CommonUtil.checkedit(bean.getAuditor()));
            }

            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 新增报障报修申请
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("addRepairapp")
    public RepairappBean addRepairapp(SessionFactory factory, RepairappBean bean) {
        String id = CommonUtil.get32Key();
        try {
            bean.setId(id);
            RepairappMapper mapper = factory.getMapper(RepairappMapper.class);


            //设置单据编号
            String maxOrderNo = mapper.maxOrderNo();//获取当天最大的单据编号
            String orderNo = CommonUtil.generateStream("GZ", "", maxOrderNo);
            bean.setOrderNo(orderNo);
            bean.setRemoveFlag(0);

            bean.setCreateAccount(SessionUtil.getUseerInfoCode());
            bean.setCreateName(SessionUtil.getUseerInfoName());
            bean.setCreateTime(CommonUtil.getCurrentTime());
            
//            String workflowcodes = CommonUtil.getWorkFlowCodesBySplit(factory, bean.getConfirmMan());
//            bean.setAssignercode(workflowcodes);                        //设置审核人
            
            mapper.insertSelective(bean);                                 //写库
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
    
    /**
     * 删除报障报修申请
     * @param sessionFactory
     * @param id
     * @return
     */
    @TaskAnnotation("deleteRepairapp")
    public MessageBean deleteRepairapp(SessionFactory sessionFactory, String id) {

        try {
            RepairappMapper mapper = sessionFactory.getMapper(RepairappMapper.class);
            RepairappBean bean = mapper.selectByPrimaryKey(id);
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
     * 更新报障报修申请
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("updateRepairapp")
    public RepairappBean updateRepairapp(SessionFactory factory, RepairappBean bean) {

        try {
            RepairappMapper mapper = factory.getMapper(RepairappMapper.class);

            //根据实例id获取相应的报废bean
            RepairappBean oldbean = mapper.selectByPrimaryKey(bean.getId());
//            bean.set .setWorkflowid(oldbean.getWorkflowid());

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

            log.debug("审批触发报障报修申请修改  调试");

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
    public MessageBean finishDo(SessionFactory factory, RepairappBean bean) {
        try {
            RepairappMapper mapper = factory.getMapper(RepairappMapper.class);

            //根据实例id获取相应的报废bean
            RepairappBean oldbean = mapper.selectByPrimaryKey(bean.getId());

            if(oldbean.getStage()!=null && oldbean.getStage().equals("结束")){

                //有选资产 插入对应的维修记录
                if(StringUtils.isNotBlank(oldbean.getAssetsId())){
                    AssetsRepdetBean redetBean = new AssetsRepdetBean();
                    redetBean.setId(CommonUtil.get32Key());
                    redetBean.setfRef(oldbean.getAssetsId());
                    redetBean.setBillDate(oldbean.getBillDate());
                    redetBean.setServiceOrderNo(oldbean.getOrderNo());
                    redetBean.setServiceType("repairapp");
                    redetBean.setServiceId(oldbean.getId());

                    redetBean.setEmployeeId(SessionUtil.getUseerInfoCode());
                    redetBean.setStaffunName(SessionUtil.getUseerInfoName());
                    redetBean.setAppContent(oldbean.getAppContent());

                    factory.getMapper(AssetsRepdetMapper.class).insertSelective(redetBean);

                }
                log.debug("进入报障报修申请 审核结束逻辑");
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
