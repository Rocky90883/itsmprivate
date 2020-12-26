package com.koron.web.maintain.mtmodify;

import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.maintain.mtmodify.bean.MtModifyBean;
import com.koron.web.maintain.mtmodify.bean.MtModifyQueryBean;
import com.koron.web.systemmanger.user.UserMapper;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.workflowUtils.mapper.BaseWorkflowMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.swan.bean.MessageBean;

import java.util.List;

@Service
public class MtModifyService {

    private static Logger log = Logger.getLogger(MtModifyService.class);

    /**
     * 变更管理列表
     * @param factory
     * @param queryBean
     * @return
     */
    @TaskAnnotation("queryList")
    public List<MtModifyBean> queryList(SessionFactory factory, MtModifyQueryBean queryBean) {
        List<MtModifyBean> MtModifylist;
        try {
            MtModifyMapper mapper = factory.getMapper(MtModifyMapper.class);
            MtModifylist = mapper.queryList(queryBean);

//            List<StaffunBean> allStaffun = factory.getMapper(UserMapper.class).getAllWorkFlowStaffun();

            MtModifylist.stream().forEach(b->{
                //转换流程状态
                b.setFlowStatusName(CommonUtil.converStatusName(b.getStatus()));
                //设置审核人名称
//                b.setAuditorName(CommonUtil.getDsrName(factory,b.getAuditor()));
//                b.setAuditorName(CommonUtil.getDsrNameNew(allStaffun,b.getAuditor()));
                //表单审核过程不允许修改
                b.setWorkflowIsApprover(CommonUtil.checkedit(b.getAuditor()));
                //设置业务类型
                b.setProcInstType("mt_modify");
            });

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MtModifylist;
    }

    /**
     * 详情
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("detail")
    public MtModifyBean detail(SessionFactory factory, String id) {
        try {
            MtModifyMapper mapper = factory.getMapper(MtModifyMapper.class);
            MtModifyBean bean = mapper.selectByPrimaryKey(id);
            if(bean!=null){
                bean.setProcInstType("mt_modify");
                bean.setWorkflowIsApprover(CommonUtil.checkedit(bean.getAuditor()));
            }

            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 新增变更管理
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("addMtModify")
    public MtModifyBean addMtModify(SessionFactory factory, MtModifyBean bean) {
        String id = CommonUtil.get32Key();
        try {
            bean.setId(id);
            MtModifyMapper mapper = factory.getMapper(MtModifyMapper.class);


            //设置单据编号
            String maxOrderNo = mapper.maxOrderNo();//获取当天最大的服务单号
            String billNo = CommonUtil.generateStream("BG", "", maxOrderNo);
            bean.setOrderNo(billNo);
            bean.setHandleStatus(0);    //默认未分配
            bean.setRemoveFlag(0);

            bean.setCreateAccount(SessionUtil.getUseerInfoCode());
            bean.setCreateName(SessionUtil.getUseerInfoName());
            bean.setCreateTime(CommonUtil.getCurrentTime());

            mapper.insertSelective(bean);                               //写库
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 删除变更管理
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("deleteMtModify")
    public MessageBean deleteMtModify(SessionFactory factory, String id) {

        try {
            MtModifyMapper mapper = factory.getMapper(MtModifyMapper.class);
            MtModifyBean bean = mapper.selectByPrimaryKey(id);
            //工作流状态不等于 草稿 或 不等于 废弃 ，不允许删除
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
     * 更新变更管理
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("updateMtModify")
    public MtModifyBean updateMtModify(SessionFactory factory, MtModifyBean bean) {

        try {
            MtModifyMapper mapper = factory.getMapper(MtModifyMapper.class);

            //根据实例id获取相应的原来bean
            MtModifyBean oldbean = mapper.selectByPrimaryKey(bean.getId());
            bean.setWorkflowid(oldbean.getWorkflowid());

            //如果状态是提交==1
            if(bean.getSubmitType()==1 ){
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
    public MessageBean finishDo(SessionFactory factory, MtModifyBean bean) {
        try {
            MtModifyMapper mapper = factory.getMapper(MtModifyMapper.class);

            //根据实例id获取相应的报废bean
            MtModifyBean oldbean = mapper.selectByPrimaryKey(bean.getId());

            if(oldbean.getStage()!=null && oldbean.getStage().equals("结束")){
                log.debug("进入变更管理 审核结束逻辑");
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
