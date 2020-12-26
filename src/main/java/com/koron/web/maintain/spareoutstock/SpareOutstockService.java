package com.koron.web.maintain.spareoutstock;

import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.asset.assettype.AssetTypeMapper;
import com.koron.web.asset.assettype.bean.AssetTypeBean;
import com.koron.web.maintain.spareinstock.SpareInstockMapper;
import com.koron.web.maintain.spareinstock.bean.SpareInstockBean;
import com.koron.web.maintain.spareoutstock.bean.SpareAvailableInVo;
import com.koron.web.maintain.spareoutstock.bean.SpareOutstockBean;
import com.koron.web.maintain.spareoutstock.bean.SpareOutstockQueryBean;
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
public class SpareOutstockService {

    private static Logger log = Logger.getLogger(SpareOutstockService.class);

    /**
     * 备件出库列表
     * @param factory
     * @param queryBean
     * @return
     */
    @TaskAnnotation("queryList")
    public List<SpareOutstockBean> queryList(SessionFactory factory, SpareOutstockQueryBean queryBean) {
        List<SpareOutstockBean> SpareOutstocklist;
        try {
            SpareOutstockMapper mapper = factory.getMapper(SpareOutstockMapper.class);
            SpareOutstocklist = mapper.queryList(queryBean);

//            List<StaffunBean> allStaffun = factory.getMapper(UserMapper.class).getAllWorkFlowStaffun();

            SpareOutstocklist.stream().forEach(b->{
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
        return SpareOutstocklist;
    }

    /**
     * 详情
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("detail")
    public SpareOutstockBean detail(SessionFactory factory, String id) {
        try {
            SpareOutstockMapper mapper = factory.getMapper(SpareOutstockMapper.class);
            SpareOutstockBean bean = mapper.selectByPrimaryKey(id);
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
     * 新增备件出库
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("addSpareOutstock")
    public SpareOutstockBean addSpareOutstock(SessionFactory factory, SpareOutstockBean bean) {
        String id = CommonUtil.get32Key();
        try {
            bean.setId(id);
            SpareOutstockMapper mapper = factory.getMapper(SpareOutstockMapper.class);
            SpareInstockMapper inmapper = factory.getMapper(SpareInstockMapper.class);

            SpareInstockBean inbean = inmapper.selectByPrimaryKey(bean.getInstockId());
            bean.setAssetTypeName(inbean.getAssetTypeName());
            bean.setAssetTypeId(inbean.getAssetTypeId());
            bean.setGoodsModel(inbean.getGoodsModel());
            bean.setInstockNo(inbean.getOrderNo());
            bean.setInstockId(inbean.getId());

            //设置单据编号
            String maxOrderNo = mapper.maxOrderNo();//获取当天最大的服务单号
            String billNo = CommonUtil.generateStream("SO", "", maxOrderNo);
            bean.setOrderNo(billNo);
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
     * 删除备件出库
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("deleteSpareOutstock")
    public MessageBean deleteSpareOutstock(SessionFactory factory, String id) {

        try {
            SpareOutstockMapper mapper = factory.getMapper(SpareOutstockMapper.class);
            SpareOutstockBean bean = mapper.selectByPrimaryKey(id);
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
     * 更新备件出库
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("updateSpareOutstock")
    public SpareOutstockBean updateSpareOutstock(SessionFactory factory, SpareOutstockBean bean) {

        try {
            SpareOutstockMapper mapper = factory.getMapper(SpareOutstockMapper.class);
            AssetTypeBean assetTypeBean = factory.getMapper(AssetTypeMapper.class).selectByPrimaryKey(bean.getAssetTypeId());

            //根据实例id获取相应的原来bean
            SpareOutstockBean oldbean = mapper.selectByPrimaryKey(bean.getId());
            bean.setWorkflowid(oldbean.getWorkflowid());

            //如果状态是提交==1
            if(bean.getSubmitType()==1 ){
                //设置分类名称
                bean.setAssetTypeName(assetTypeBean.getTypename());

                bean.setUpdateAccount(SessionUtil.getUseerInfoCode());
                bean.setUpdateName(SessionUtil.getUseerInfoName());
                bean.setUpdateTime(CommonUtil.getCurrentTime());
                mapper.updateByPrimaryKeySelective(bean);                   //写库
                return bean;
            }

            //如果状态是草稿==2 只是修改单据     (草稿保存草稿)
            if(bean.getSubmitType()==2 && StringUtils.isEmpty(oldbean.getWorkflowid())){
                //设置分类名称
                bean.setAssetTypeName(assetTypeBean.getTypename());

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
    public MessageBean finishDo(SessionFactory factory, SpareOutstockBean bean) {
        try {
            SpareOutstockMapper mapper = factory.getMapper(SpareOutstockMapper.class);

            //根据实例id获取相应的报废bean
            SpareOutstockBean oldbean = mapper.selectByPrimaryKey(bean.getId());

            if(oldbean.getStage()!=null && oldbean.getStage().equals("结束")){
                log.debug("进入备件出库 审核结束逻辑");

                mapper.updateOutQty(oldbean.getQty(),oldbean.getInstockId());    //更新对应入库单的出库数量

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
     * 检验
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("check")
    public MessageBean check(SessionFactory factory, SpareOutstockBean bean) {
        try {
            SpareOutstockMapper mapper = factory.getMapper(SpareOutstockMapper.class);
            SpareInstockMapper inmapper = factory.getMapper(SpareInstockMapper.class);

            SpareInstockBean inbean = inmapper.selectByPrimaryKey(bean.getInstockId());
            if(inbean==null || inbean.getInQty()==null){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "找不到对应的备件入库单", void.class);
            }

            //在审核数量
            double shenheQtyv = mapper.getshenheQty(bean.getInstockId());
            //采购入库数目 小于 已出库数量 + 单据领用数 + 审核数量
            double keyongqty = inbean.getInQty() - (inbean.getOutQty()+bean.getQty()+shenheQtyv);
            if (keyongqty<0) {
                double msgqty = inbean.getInQty() - shenheQtyv;
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "单据备件剩余"+msgqty+"个，数量不足无法申请", void.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }



    /**
     * 查询可申请备品备件
     * @param factory
     * @return
     */
    @TaskAnnotation("queryInstock")
    public List<SpareAvailableInVo> queryInstock(SessionFactory factory) {
        List<SpareAvailableInVo> volist;
        try {
            SpareOutstockMapper mapper = factory.getMapper(SpareOutstockMapper.class);
            volist = mapper.availableSpare();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return volist;
    }

}
