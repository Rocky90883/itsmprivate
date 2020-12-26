package com.koron.web.asset.scrapped;

import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.asset.scrapped.bean.ScrappedBean;
import com.koron.web.asset.scrapped.bean.ScrappedQueryBean;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ScrappedService {

    private static Logger log = Logger.getLogger(ScrappedService.class);

    /**
     * 资产报废列表
     * @param factory
     * @param queryBean
     * @return
     */
    @TaskAnnotation("queryList")
    public List<ScrappedBean> queryList(SessionFactory factory, ScrappedQueryBean queryBean) {
        List<ScrappedBean> scrappedlist;
        try {
            ScrappedMapper mapper = factory.getMapper(ScrappedMapper.class);
            scrappedlist = mapper.queryList(queryBean);

//            UserMapper usermapper = factory.getMapper(UserMapper.class);
//            List<StaffunBean> allStaffun = usermapper.getAllWorkFlowStaffun();

            scrappedlist.stream().forEach(b->{
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
        return scrappedlist;
    }

    /**
     * 详情
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("detail")
    public ScrappedBean detail(SessionFactory factory, String id) {
        try {
            ScrappedMapper mapper = factory.getMapper(ScrappedMapper.class);
            ScrappedBean bean = mapper.selectByPrimaryKey(id);
            if(bean!=null){
                bean.setProcInstType("scrapped");
                bean.setWorkflowIsApprover(CommonUtil.checkedit(bean.getAuditor()));
            }

            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 新增资产报废
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("addScrapped")
    public ScrappedBean addScrapped(SessionFactory factory, ScrappedBean bean) {
        String id = CommonUtil.get32Key();
        try {
            bean.setId(id);
            ScrappedMapper mapper = factory.getMapper(ScrappedMapper.class);


            //设置单据编号
            String maxBillNo = mapper.maxBillNo();//获取当天最大的单据编号
            String billNo = CommonUtil.generateStream("BF", "", maxBillNo);
            bean.setBillNo(billNo);

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
     * 删除资产报废
     * @param sessionFactory
     * @param id
     * @return
     */
    @TaskAnnotation("deleteScrapped")
    public MessageBean deleteScrapped(SessionFactory sessionFactory, String id) {

        try {
            ScrappedMapper mapper = sessionFactory.getMapper(ScrappedMapper.class);
            ScrappedBean bean = mapper.selectByPrimaryKey(id);
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
     * 更新资产报废
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("updateScrapped")
    public ScrappedBean updateScrapped(SessionFactory factory, ScrappedBean bean) {

        try {
            ScrappedMapper mapper = factory.getMapper(ScrappedMapper.class);

            //根据实例id获取相应的报废bean
            ScrappedBean oldbean = mapper.selectByPrimaryKey(bean.getId());
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

            log.debug("审批触发资产报废修改  调试");

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
    public MessageBean finishDo(SessionFactory factory, ScrappedBean bean) {
        try {
            ScrappedMapper mapper = factory.getMapper(ScrappedMapper.class);

            //根据实例id获取相应的报废bean
            ScrappedBean oldbean = mapper.selectByPrimaryKey(bean.getId());

            if(oldbean.getStage()!=null && oldbean.getStage().equals("结束")){
                log.debug("进入资产报废 审核结束逻辑");
                oldbean.setUpdateAccount(SessionUtil.getUseerInfoCode());
                oldbean.setUpdateName(SessionUtil.getUseerInfoName());
                oldbean.setUpdateTime(CommonUtil.getCurrentTime());

                mapper.updateByPrimaryKeySelective(oldbean);   //写库
                mapper.updateAssetsStatus(oldbean.getSourceId(),oldbean.getId());//更新资产台账IT资产状态报废、登记状态
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 通过用户名获取code
     *
     * @param cods
     * @return
     */
    @TaskAnnotation("getUserCode")
    public MessageBean<?> getUserCode(SessionFactory factory, String cods) {
        BaseWorkflowMapper mapper = factory.getMapper(BaseWorkflowMapper.class);
        // 通过loginId获取code
        String code = null;
        if (!StringUtils.isEmpty(cods)) {
            String[] codearr = cods.split(",");
            List<String> codeList = mapper.getUserCode(codearr);
            code = String.join(",", codeList);
        }
        MessageBean<String> msg = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "通过code获取流程平台code", String.class);
        msg.setData(code);
        return msg;
    }


}
