package com.koron.web.workorder.accountapp;

import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.systemmanger.user.UserMapper;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.workflowUtils.mapper.BaseWorkflowMapper;
import com.koron.web.workorder.accountapp.bean.AccountappBean;
import com.koron.web.workorder.accountapp.bean.AccountappQueryBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.swan.bean.MessageBean;

import java.util.List;

@Service
public class AccountappService {

    private static Logger log = Logger.getLogger(AccountappService.class);

    /**
     * 账号权限申请列表
     * @param factory
     * @param queryBean
     * @return
     */
    @TaskAnnotation("queryList")
    public List<AccountappBean> queryList(SessionFactory factory, AccountappQueryBean queryBean) {
        List<AccountappBean> accountapplist;
        try {
            AccountappMapper mapper = factory.getMapper(AccountappMapper.class);
            accountapplist = mapper.queryList(queryBean);

//            List<StaffunBean> allStaffun = factory.getMapper(UserMapper.class).getAllWorkFlowStaffun();

            accountapplist.stream().forEach(b->{
                //转换流程状态
                b.setFlowStatusName(CommonUtil.converStatusName(b.getStatus()));
                //设置审核人名称
//                b.setAuditorName(CommonUtil.getDsrName(factory,b.getAuditor()));
//                b.setAuditorName(CommonUtil.getDsrNameNew(allStaffun,b.getAuditor()));
                //表单审核过程不允许修改
                b.setWorkflowIsApprover(CommonUtil.checkedit(b.getAuditor()));
            });

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return accountapplist;
    }

    /**
     * 详情
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("detail")
    public AccountappBean detail(SessionFactory factory, String id) {
        try {
            AccountappMapper mapper = factory.getMapper(AccountappMapper.class);
            AccountappBean bean = mapper.selectByPrimaryKey(id);
            if(bean!=null){
                bean.setProcInstType("accountapp");
                bean.setWorkflowIsApprover(CommonUtil.checkedit(bean.getAuditor()));
            }

            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 新增账号权限申请
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("addAccountapp")
    public AccountappBean addAccountapp(SessionFactory factory, AccountappBean bean) {
        String id = CommonUtil.get32Key();
        try {
            bean.setId(id);
            AccountappMapper mapper = factory.getMapper(AccountappMapper.class);


            //设置单据编号
            String maxOrderNo = mapper.maxOrderNo();//获取当天最大的服务单号
            String billNo = CommonUtil.generateStream("AC", "", maxOrderNo);
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
     * 删除账号权限申请
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("deleteAccountapp")
    public MessageBean deleteAccountapp(SessionFactory factory, String id) {

        try {
            AccountappMapper mapper = factory.getMapper(AccountappMapper.class);
            AccountappBean bean = mapper.selectByPrimaryKey(id);
            //工作流状态不等于 草稿  ，不允许删除
            if(bean.getStatus()!=null && bean.getStatus()!=2){
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
     * 更新账号权限申请
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("updateAccountapp")
    public AccountappBean updateAccountapp(SessionFactory factory, AccountappBean bean) {

        try {
            AccountappMapper mapper = factory.getMapper(AccountappMapper.class);

            //根据实例id获取相应的原来bean
            AccountappBean oldbean = mapper.selectByPrimaryKey(bean.getId());
            bean.setWorkflowid(oldbean.getWorkflowid());

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

            log.debug("审批触发账号权限申请修改  调试");

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
    public MessageBean finishDo(SessionFactory factory, AccountappBean bean) {
        try {
            AccountappMapper mapper = factory.getMapper(AccountappMapper.class);

            //根据实例id获取相应的报废bean
            AccountappBean oldbean = mapper.selectByPrimaryKey(bean.getId());

            if(oldbean.getStage()!=null && oldbean.getStage().equals("结束")){
                log.debug("进入账号权限申请 审核结束逻辑");
                oldbean.setOverTime(CommonUtil.getCurrentTime());
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
