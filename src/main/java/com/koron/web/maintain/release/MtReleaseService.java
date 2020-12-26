package com.koron.web.maintain.release;

import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.maintain.release.bean.MtReleaseBean;
import com.koron.web.maintain.release.bean.MtReleaseQueryBean;
import com.koron.web.systemmanger.user.UserMapper;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.swan.bean.MessageBean;

import java.util.List;

@Service
public class MtReleaseService {

    private static Logger log = Logger.getLogger(MtReleaseService.class);

    /**
     * 发布管理列表
     * @param factory
     * @param queryBean
     * @return
     */
    @TaskAnnotation("queryList")
    public List<MtReleaseBean> queryList(SessionFactory factory, MtReleaseQueryBean queryBean) {
        List<MtReleaseBean> MtReleaselist;
        try {
            MtReleaseMapper mapper = factory.getMapper(MtReleaseMapper.class);
            MtReleaselist = mapper.queryList(queryBean);

//            List<StaffunBean> allStaffun = factory.getMapper(UserMapper.class).getAllWorkFlowStaffun();

            MtReleaselist.stream().forEach(b->{
                //转换流程状态
                b.setFlowStatusName(CommonUtil.converStatusName(b.getStatus()));
                //设置审核人名称
//                b.setAuditorName(CommonUtil.getDsrName(factory,b.getAuditor()));
//                b.setAuditorName(CommonUtil.getDsrNameNew(allStaffun,b.getAuditor()));
                //表单审核过程不允许修改
                b.setWorkflowIsApprover(CommonUtil.checkedit(b.getAuditor()));
                //设置业务类型
                b.setProcInstType("mt_release");
            });

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MtReleaselist;
    }

    /**
     * 详情
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("detail")
    public MtReleaseBean detail(SessionFactory factory, String id) {
        try {
            MtReleaseMapper mapper = factory.getMapper(MtReleaseMapper.class);
            MtReleaseBean bean = mapper.selectByPrimaryKey(id);
            if(bean!=null){
                bean.setProcInstType("mt_release");
                bean.setWorkflowIsApprover(CommonUtil.checkedit(bean.getAuditor()));
            }

            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
    
    /**
     * 新增发布管理
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("addMtRelease")
    public MtReleaseBean addMtRelease(SessionFactory factory, MtReleaseBean bean) {
        String id = CommonUtil.get32Key();
        try {
            bean.setId(id);
            MtReleaseMapper mapper = factory.getMapper(MtReleaseMapper.class);


            //设置单据编号
            String maxOrderNo = mapper.maxOrderNo();//获取当天最大的服务单号
            String billNo = CommonUtil.generateStream("FB", "", maxOrderNo);
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
     * 删除发布管理
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("deleteMtRelease")
    public MessageBean deleteMtRelease(SessionFactory factory, String id) {

        try {
            MtReleaseMapper mapper = factory.getMapper(MtReleaseMapper.class);
            MtReleaseBean bean = mapper.selectByPrimaryKey(id);
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
     * 更新发布管理
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("updateMtRelease")
    public MtReleaseBean updateMtRelease(SessionFactory factory, MtReleaseBean bean) {

        try {
            MtReleaseMapper mapper = factory.getMapper(MtReleaseMapper.class);

            //根据实例id获取相应的原来bean
            MtReleaseBean oldbean = mapper.selectByPrimaryKey(bean.getId());
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
    public MessageBean finishDo(SessionFactory factory, MtReleaseBean bean) {
        try {
            MtReleaseMapper mapper = factory.getMapper(MtReleaseMapper.class);

            //根据实例id获取相应的报废bean
            MtReleaseBean oldbean = mapper.selectByPrimaryKey(bean.getId());

            if(oldbean.getStage()!=null && oldbean.getStage().equals("结束")){
                log.debug("进入发布管理 审核结束逻辑");
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

    /**
     * 分配责任人
     * @param factory
     * @param id
     * @param assignEmpid
     * @return
     */
    @TaskAnnotation("assignPer")
    public MessageBean assignPer(SessionFactory factory, String id, String assignEmpid) {
        MessageBean<MtReleaseBean> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", MtReleaseBean.class);
        try {
            MtReleaseMapper mapper = factory.getMapper(MtReleaseMapper.class);
            UserMapper usermapper = factory.getMapper(UserMapper.class);

            //根据实例id获取相应的报废bean
            MtReleaseBean oldbean = mapper.selectByPrimaryKey(id);

            //流程结束后才能分配责任人
            if(oldbean.getStage()!=null && oldbean.getStage().equals("结束")){
                StaffunBean staffunbean = usermapper.getStaffByLoginId(assignEmpid);
                oldbean.setAssignName(staffunbean.getName());
                oldbean.setAssignEmpid(assignEmpid);
                oldbean.setStatus(1);
                mapper.assignper(id,assignEmpid,staffunbean.getName());   //写库
            }

            success.setData(oldbean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return success;
    }




}
