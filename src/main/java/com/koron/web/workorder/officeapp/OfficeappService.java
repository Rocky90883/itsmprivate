package com.koron.web.workorder.officeapp;

import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.asset.assetregist.AssetRegistMapper;
import com.koron.web.asset.assetregist.bean.AssetRegistBean;
import com.koron.web.asset.assetregist.bean.RegistSoftwareVo;
import com.koron.web.systemmanger.user.UserMapper;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.workorder.officeapp.bean.OfficeappBean;
import com.koron.web.workorder.officeapp.bean.OfficeappQueryBean;
import com.koron.web.workorder.officeapp.officeappdet.OfficeappdetMapper;
import com.koron.web.workorder.officeapp.officeappdet.bean.OfficeappdetBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.swan.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfficeappService {

    private static Logger log = Logger.getLogger(OfficeappService.class);

    /**
     * 正版软件申请列表
     * @param factory
     * @param queryBean
     * @return
     */
    @TaskAnnotation("queryList")
    public List<OfficeappBean> queryList(SessionFactory factory, OfficeappQueryBean queryBean) {
        List<OfficeappBean> Officeapplist;
        try {
            OfficeappMapper mapper = factory.getMapper(OfficeappMapper.class);
            Officeapplist = mapper.queryList(queryBean);

//            UserMapper usermapper = factory.getMapper(UserMapper.class);
//            List<StaffunBean> allStaffun = usermapper.getAllWorkFlowStaffun();

            Officeapplist.stream().forEach(b->{
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
        return Officeapplist;
    }

    /**
     * 详情
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("detail")
    public OfficeappBean detail(SessionFactory factory, String id) {
        try {
            OfficeappMapper mapper = factory.getMapper(OfficeappMapper.class);
            OfficeappBean bean = mapper.selectByPrimaryKey(id);
            if(bean!=null){
                bean.setProcInstType("officeapp");
                bean.setWorkflowIsApprover(CommonUtil.checkedit(bean.getAuditor()));
                List<OfficeappdetBean> det = bean.getDet();
                det.stream().forEach(d->d.setAssetTypeName(d.getTypeName()));
                bean.setDet(det);

            }

            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 新增正版软件申请
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("addOfficeapp")
    public MessageBean addOfficeapp(SessionFactory factory, OfficeappBean bean) {
        String id = CommonUtil.get32Key();
        try {
            bean.setId(id);
            OfficeappMapper mapper = factory.getMapper(OfficeappMapper.class);
            AssetRegistMapper regisMapper = factory.getMapper(AssetRegistMapper.class);

            //明细检查装箱
            List<OfficeappdetBean> detlist = new ArrayList<>();
            MessageBean<List> packmsg = packing(regisMapper, bean);
            if (packmsg.getCode()!=0) {
                return packmsg;
            }else{
                detlist = packmsg.getData();
            }


            //设置单据编号
            String maxOrderNo = mapper.maxOrderNo();//获取当天最大的单据编号
            String orderNo = CommonUtil.generateStream("OF", "", maxOrderNo);
            bean.setOrderNo(orderNo);
            bean.setRemoveFlag(0);

            bean.setCreateAccount(SessionUtil.getUseerInfoCode());
            bean.setCreateName(SessionUtil.getUseerInfoName());
            bean.setCreateTime(CommonUtil.getCurrentTime());


            String workflowcodes = CommonUtil.getWorkFlowCodesBySplit(factory, bean.getConfirmMan());
            bean.setAssignercode(workflowcodes);                        //设置审核人
            mapper.insertSelective(bean);                               //写主表
            //写明细
            OfficeappdetMapper offdetmapper = factory.getMapper(OfficeappdetMapper.class);
            detlist.stream().forEach(d->{
                offdetmapper.insertSelective(d);
            });

            MessageBean<OfficeappBean> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success",OfficeappBean.class);
            success.setData(bean);
            return success;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
    
    /**
     * 删除正版软件申请
     * @param sessionFactory
     * @param id
     * @return
     */
    @TaskAnnotation("deleteOfficeapp")
    public MessageBean deleteOfficeapp(SessionFactory sessionFactory, String id) {

        try {
            OfficeappMapper mapper = sessionFactory.getMapper(OfficeappMapper.class);
            OfficeappBean bean = mapper.selectByPrimaryKey(id);
            //工作流状态不等于 草稿，不允许删除
            if(bean.getStatus()!=null && bean.getStatus()!=2 ){
                return MessageBean.create(Constant.MESSAGE_WORKDELERROR, "已存在工作流 不允许删除", void.class);
            }
//            List<OfficeappdetBean> detlist = bean.getDet();
//            for(OfficeappdetBean det: detlist){
//                if(det.getIsstart().equals(1)){
//                    return MessageBean.create(Constant.MESSAGE_WORKDELERROR, "该记录生效不允许删除", void.class);
//                }
//            }

            mapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 更新正版软件申请
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("updateOfficeapp")
    public MessageBean updateOfficeapp(SessionFactory factory, OfficeappBean bean) {
        MessageBean<OfficeappBean> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", OfficeappBean.class);
        try {
            OfficeappMapper mapper = factory.getMapper(OfficeappMapper.class);
            AssetRegistMapper regisMapper = factory.getMapper(AssetRegistMapper.class);     //登记mapper
            OfficeappdetMapper detMapper = factory.getMapper(OfficeappdetMapper.class);     //明细mapper

            //根据实例id获取相应的报废bean
            OfficeappBean oldbean = mapper.selectByPrimaryKey(bean.getId());
//            bean.set .setWorkflowid(oldbean.getWorkflowid());

            //如果状态是提交==1
            if(bean.getSubmitType()==1 ){
                //且 流程还没启动 才需要设置审核人 (草稿提交审核)
                if(StringUtils.isEmpty(oldbean.getWorkflowid())){
                    String workflowcodes = CommonUtil.getWorkFlowCodesBySplit(factory, bean.getConfirmMan());
                    bean.setAssignercode(workflowcodes);                        //设置审核人
                }

                detMapper.deleteByfReg(bean.getId());                   //先删除明细
                //重新装明细
                List<OfficeappdetBean> detlist = new ArrayList<>();
                MessageBean<List> packmsg = packing(regisMapper, bean);
                if (packmsg.getCode()!=0) {
                    return packmsg;
                }else{
                    detlist = packmsg.getData();
                }
                //重新插明细
                detlist.stream().forEach(d->{
                    detMapper.insertSelective(d);
                });

                bean.setUpdateAccount(SessionUtil.getUseerInfoCode());
                bean.setUpdateName(SessionUtil.getUseerInfoName());
                bean.setUpdateTime(CommonUtil.getCurrentTime());
                mapper.updateByPrimaryKeySelective(bean);                   //写库

                success.setData(bean);
                return success;
            }

            //如果状态是草稿==2 只是修改单据     (草稿保存草稿)
            if(bean.getSubmitType()==2 && StringUtils.isEmpty(oldbean.getWorkflowid())){
                bean.setUpdateAccount(SessionUtil.getUseerInfoCode());
                bean.setUpdateName(SessionUtil.getUseerInfoName());
                bean.setUpdateTime(CommonUtil.getCurrentTime());

                detMapper.deleteByfReg(bean.getId());                   //先删除明细
                //重新装明细
                List<OfficeappdetBean> detlist = new ArrayList<>();
                MessageBean<List> packmsg = packing(regisMapper, bean);
                if (packmsg.getCode()!=0) {
                    return packmsg;
                }else{
                    detlist = packmsg.getData();
                }
                //重新插明细
                detlist.stream().forEach(d->{
                    detMapper.insertSelective(d);
                });


                mapper.updateByPrimaryKeySelective(bean);                   //写库

                success.setData(bean);
                return success;
            }
            success.setData(bean);
            log.debug("审批触发正版软件申请修改  调试");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return success;
    }

    /**
     * 流程结束后调用
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("finishDo")
    public MessageBean finishDo(SessionFactory factory, OfficeappBean bean) {
        try {
            OfficeappMapper mapper = factory.getMapper(OfficeappMapper.class);
            OfficeappdetMapper detmapper = factory.getMapper(OfficeappdetMapper.class);
            AssetRegistMapper registmapper = factory.getMapper(AssetRegistMapper.class);

            //根据实例id获取相应的报废bean
            OfficeappBean oldbean = mapper.selectByPrimaryKey(bean.getId());

            if(oldbean.getStage()!=null && oldbean.getStage().equals("结束")){
                //更新登记单已分配数量
                List<OfficeappdetBean> det = oldbean.getDet();
                det.stream().forEach(d->{
                    registmapper.updateOverQty(d.getSourceId(),d.getQty());
                });

                log.debug("进入正版软件申请 审核结束逻辑");
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

    /**
     * 明细更新
     */
    private static MessageBean packing(AssetRegistMapper regisMapper,OfficeappBean bean){

        List<OfficeappdetBean> detlist = new ArrayList<>();
        for(OfficeappdetBean det: bean.getDet()){
            OfficeappdetBean detbean = new OfficeappdetBean();

            AssetRegistBean regisbean = regisMapper.selectByPrimaryKey(det.getSourceId());
            double availableqty = regisbean.getQty() - regisbean.getOverQty();
            if(availableqty<det.getQty()){
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "数量不足无法申请",OfficeappBean.class);
            }
            detbean.setAppEmp(bean.getEmployeeId());                //申请人
            detbean.setAssetTypeId(regisbean.getAssetTypeId());     //资产类型
            detbean.setGoodsModel(regisbean.getGoodsModel());       //资产型号
            detbean.setSourceId(regisbean.getId());                 //登记单id
            detbean.setInstock(availableqty);                       //当时可用库存
            detbean.setIsstart(0);                                  //流程是否通过
            detbean.setQty(det.getQty());                           //申请数量
            detbean.setEmpids(det.getEmpids());                     //用户清单
            detbean.setUsemsg(det.getUsemsg());                     //用途
            detbean.setfRef(bean.getId());                          //f_ref
            detbean.setId(CommonUtil.get32Key());                   //id
            detlist.add(detbean);
        }
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        success.setData(detlist);
        return success;
    }


    /**
     * 查询可申请软件列表
     * @param factory
     * @return
     */
    @TaskAnnotation("queryRegist")
    public List<RegistSoftwareVo> queryRegist(SessionFactory factory) {
        List<RegistSoftwareVo> registSoftwareVos;
        try {
            AssetRegistMapper mapper = factory.getMapper(AssetRegistMapper.class);
            registSoftwareVos = mapper.queryListByOffice();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return registSoftwareVos;
    }

}
