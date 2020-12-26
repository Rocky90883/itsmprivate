package com.koron.web.maintain.spareinstock;

import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.asset.assettype.AssetTypeMapper;
import com.koron.web.asset.assettype.bean.AssetTypeBean;
import com.koron.web.maintain.spareinstock.bean.SpareInstockBean;
import com.koron.web.maintain.spareinstock.bean.SpareInstockQueryBean;
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
public class SpareInstockService {

    private static Logger log = Logger.getLogger(SpareInstockService.class);

    /**
     * 备件入库列表
     * @param factory
     * @param queryBean
     * @return
     */
    @TaskAnnotation("queryList")
    public List<SpareInstockBean> queryList(SessionFactory factory, SpareInstockQueryBean queryBean) {
        List<SpareInstockBean> SpareInstocklist;
        try {
            SpareInstockMapper mapper = factory.getMapper(SpareInstockMapper.class);
            SpareInstocklist = mapper.queryList(queryBean);

//            List<StaffunBean> allStaffun = factory.getMapper(UserMapper.class).getAllWorkFlowStaffun();

            SpareInstocklist.stream().forEach(b->{
                //转换流程状态
                b.setFlowStatusName(CommonUtil.converStatusName(b.getStatus()));
                //设置审核人名称
//                b.setAuditorName(CommonUtil.getDsrNameNew(allStaffun,b.getAuditor()));
                //表单审核过程不允许修改
                b.setWorkflowIsApprover(CommonUtil.checkedit(b.getAuditor()));
            });

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return SpareInstocklist;
    }

    /**
     * 详情
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("detail")
    public SpareInstockBean detail(SessionFactory factory, String id) {
        try {
            SpareInstockMapper mapper = factory.getMapper(SpareInstockMapper.class);
            SpareInstockBean bean = mapper.selectByPrimaryKey(id);
            if(bean!=null){
                bean.setProcInstType("spare_instock");
                bean.setWorkflowIsApprover(CommonUtil.checkedit(bean.getAuditor()));
            }

            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 新增备件入库
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("addSpareInstock")
    public SpareInstockBean addSpareInstock(SessionFactory factory, SpareInstockBean bean) {
        String id = CommonUtil.get32Key();
        try {
            bean.setId(id);
            SpareInstockMapper mapper = factory.getMapper(SpareInstockMapper.class);

            //设置分类名称
            AssetTypeBean assetTypeBean = factory.getMapper(AssetTypeMapper.class).selectByPrimaryKey(bean.getAssetTypeId());
            bean.setAssetTypeName(assetTypeBean.getTypename());

            //设置单据编号
            String maxOrderNo = mapper.maxOrderNo();//获取当天最大的服务单号
            String billNo = CommonUtil.generateStream("SI", "", maxOrderNo);
            bean.setOrderNo(billNo);
            bean.setOutQty(0.0);    //设置出库数量为零（新增的都是零）
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
     * 删除备件入库
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("deleteSpareInstock")
    public MessageBean deleteSpareInstock(SessionFactory factory, String id) {

        try {
            SpareInstockMapper mapper = factory.getMapper(SpareInstockMapper.class);
            SpareInstockBean bean = mapper.selectByPrimaryKey(id);
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
     * 更新备件入库
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("updateSpareInstock")
    public MessageBean updateSpareInstock(SessionFactory factory, SpareInstockBean bean) {
        MessageBean msg = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", MessageBean.class);
        try {
            SpareInstockMapper mapper = factory.getMapper(SpareInstockMapper.class);
            AssetTypeBean assetTypeBean = factory.getMapper(AssetTypeMapper.class).selectByPrimaryKey(bean.getAssetTypeId());

            //根据实例id获取相应的原来bean
            SpareInstockBean oldbean = mapper.selectByPrimaryKey(bean.getId());
            bean.setWorkflowid(oldbean.getWorkflowid());
            if(bean.getInQty()<oldbean.getOutQty()){
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "入库数量不能少于出库数量", MessageBean.class);
            }

            //如果状态是提交==1
            if(bean.getSubmitType()==1 ){
                //设置分类名称
                bean.setAssetTypeName(assetTypeBean.getTypename());

                bean.setUpdateAccount(SessionUtil.getUseerInfoCode());
                bean.setUpdateName(SessionUtil.getUseerInfoName());
                bean.setUpdateTime(CommonUtil.getCurrentTime());
                mapper.updateByPrimaryKeySelective(bean);                   //写库
                msg.setData(bean);
                return msg;
            }

            //如果状态是草稿==2 只是修改单据     (草稿保存草稿)
            if(bean.getSubmitType()==2 && StringUtils.isEmpty(oldbean.getWorkflowid())){
                //设置分类名称
                bean.setAssetTypeName(assetTypeBean.getTypename());

                bean.setUpdateAccount(SessionUtil.getUseerInfoCode());
                bean.setUpdateName(SessionUtil.getUseerInfoName());
                bean.setUpdateTime(CommonUtil.getCurrentTime());
                mapper.updateByPrimaryKeySelective(bean);                   //写库
                msg.setData(bean);
                return msg;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return msg;
    }

    /**
     * 流程结束后调用
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("finishDo")
    public MessageBean finishDo(SessionFactory factory, SpareInstockBean bean) {
        try {
            SpareInstockMapper mapper = factory.getMapper(SpareInstockMapper.class);

            SpareInstockBean oldbean = mapper.selectByPrimaryKey(bean.getId());

            if(oldbean.getStage()!=null && oldbean.getStage().equals("结束")){
                log.debug("进入备件入库 审核结束逻辑");
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


//    @TaskAnnotation("check")
//    public MessageBean check(SessionFactory factory, SpareInstockBean bean) {
//        try {
//            SpareInstockMapper mapper = factory.getMapper(SpareInstockMapper.class);
//
//            //根据实例id获取相应的报废bean
//            SpareInstockBean oldbean = mapper.selectByPrimaryKey(bean.getId());
//
////            oldbean.getInQty()<oldbean.getOutQty()+bean.get
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e.getMessage());
//        }
//        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
//    }


}
