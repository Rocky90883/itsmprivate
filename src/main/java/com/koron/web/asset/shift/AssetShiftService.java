package com.koron.web.asset.shift;

import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.asset.shift.bean.AssetShiftBean;
import com.koron.web.asset.shift.bean.AssetShiftQueryBean;
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
public class AssetShiftService {

    private static Logger log = Logger.getLogger(AssetShiftService.class);

    /**
     * 资产变更列表
     * @param factory
     * @param queryBean
     * @return
     */
    @TaskAnnotation("queryList")
    public List<AssetShiftBean> queryList(SessionFactory factory, AssetShiftQueryBean queryBean) {
        List<AssetShiftBean> shiftlist;
        try {
            AssetShiftMapper mapper = factory.getMapper(AssetShiftMapper.class);
            shiftlist = mapper.queryList(queryBean);

//            List<StaffunBean> allStaffun = factory.getMapper(UserMapper.class).getAllWorkFlowStaffun();

            shiftlist.stream().forEach(b->{
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
        return shiftlist;
    }

    /**
     * 新增资产变更
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("addShift")
    public AssetShiftBean addShift(SessionFactory factory, AssetShiftBean bean) {
        String id = CommonUtil.get32Key();
        try {
            bean.setId(id);
            AssetShiftMapper mapper = factory.getMapper(AssetShiftMapper.class);


            //设置单据编号
            String maxBillNo = mapper.maxBillNo();//获取当天最大的单据编号
            String billNo = CommonUtil.generateStream("ZY", "", maxBillNo);
            bean.setBillNo(billNo);
            bean.setRemoveFlag(0);//

            bean.setCreateAccount(SessionUtil.getUseerInfoCode());
            bean.setCreateName(SessionUtil.getUseerInfoName());
            bean.setCreateTime(CommonUtil.getCurrentTime());

//            MessageBean<?> msgb = getUserCode(factory, bean.getConfirmMan());
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
     * 新增资产变更
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("detail")
    public AssetShiftBean detail(SessionFactory factory, String id) {
        try {
            AssetShiftMapper mapper = factory.getMapper(AssetShiftMapper.class);
            AssetShiftBean bean = mapper.selectByPrimaryKey(id);
            if(bean!=null){
                bean.setProcInstType("asset_shift");
                bean.setWorkflowIsApprover(CommonUtil.checkedit(bean.getAuditor()));
            }


            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 删除资产变更
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("deleteShift")
    public MessageBean deleteShift(SessionFactory factory, String id) {

        try {
            AssetShiftMapper mapper = factory.getMapper(AssetShiftMapper.class);
            AssetShiftBean bean = mapper.selectByPrimaryKey(id);
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
     * 更新资产变更
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("updateShift")
    public AssetShiftBean updateShift(SessionFactory factory, AssetShiftBean bean) {

        try {
            AssetShiftMapper mapper = factory.getMapper(AssetShiftMapper.class);
            log.debug("审批触发资产变更修改  调试");
            //根据实例id获取相应的报废bean
            AssetShiftBean oldbean = mapper.selectByPrimaryKey(bean.getId());
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
    public MessageBean finishDo(SessionFactory factory, AssetShiftBean bean) {
        try {
            AssetShiftMapper mapper = factory.getMapper(AssetShiftMapper.class);

            //根据实例id获取相应的报废bean
            AssetShiftBean oldbean = mapper.selectByPrimaryKey(bean.getId());

            if(oldbean.getStage()!=null && oldbean.getStage().equals("结束")){
                log.debug("进入资产变更 审核结束逻辑");
                oldbean.setStatus(bean.getStatus());
                oldbean.setUpdateAccount(SessionUtil.getUseerInfoCode());
                oldbean.setUpdateName(SessionUtil.getUseerInfoName());
                oldbean.setUpdateTime(CommonUtil.getCurrentTime());

                mapper.updateByPrimaryKeySelective(oldbean);   //写库
                mapper.updateAssetsEmpId(oldbean);//更新资产台账 所有人 所有部门
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
