package com.koron.web.asset.assetportion;

import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.asset.assetportion.bean.AssetPortionBean;
import com.koron.web.asset.assetportion.bean.AssetPortionQueryBean;
import com.koron.web.asset.assetportiondet.AssetPortionDetMapper;
import com.koron.web.asset.assetregist.AssetRegistMapper;
import com.koron.web.asset.assetregist.bean.AssetRegistBean;
import com.koron.web.asset.assetregist.bean.AssetRegistQueryBean;
import com.koron.web.systemmanger.roles.SysRoleMapper;
import com.koron.web.systemmanger.roles.bean.SysRoleBean;
import com.koron.web.systemmanger.user.UserMapper;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.systemmanger.user.bean.StaffunQueryBean;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.swan.bean.MessageBean;

import java.util.List;

@Service
public class AssetPortionService {

    private static Logger log = Logger.getLogger(AssetPortionService.class);

    /**
     * 资产分配列表
     * @param factory
     * @param queryBean
     * @return
     */
    @TaskAnnotation("queryList")
    public List<AssetPortionBean> queryList(SessionFactory factory, AssetPortionQueryBean queryBean) {
        List<AssetPortionBean> assetslist;
        try {
            AssetPortionMapper mapper = factory.getMapper(AssetPortionMapper.class);
            AssetPortionDetMapper detmapper = factory.getMapper(AssetPortionDetMapper.class);

            SysRoleMapper sysRolemapper = factory.getMapper(SysRoleMapper.class);
            List<SysRoleBean> roles = sysRolemapper.getRoleBystaffuncode(SessionUtil.getUseerInfoCode());
            queryBean.setDepatCode(SessionUtil.getLoginUser().getOrgNodeCode());//默认查自己部门的分配单据
            //it运维人员、信息中心、it经理可以看到全部部门
            if(roles.stream().anyMatch(r->r.getBroper().equals("all"))){
                queryBean.setDepatCode("");
            }

            assetslist = mapper.queryList(queryBean);
            assetslist.stream().forEach(p->{
                p.setOverQty(p.getPortionDet().size());
//                p.setOverQty(detmapper.countByPortionId(p.getId()));
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return assetslist;
    }

    /**
     * 详情
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("detail")
    public AssetPortionBean detail(SessionFactory factory, String id) {
        try {
            AssetPortionMapper mapper = factory.getMapper(AssetPortionMapper.class);
            AssetPortionBean bean = mapper.selectByPrimaryKey(id);

            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 新增资产分配
     * @param sessionFactory
     * @param bean
     * @return
     */
    @TaskAnnotation("addPortion")
    public MessageBean addPortion(SessionFactory sessionFactory, AssetPortionBean bean) {

        try {
            bean.setId(CommonUtil.get32Key());
            AssetPortionMapper mapper = sessionFactory.getMapper(AssetPortionMapper.class);

            StaffunQueryBean queryBean = new StaffunQueryBean();
            queryBean.setOrgNodeCode(bean.getDepatCode());
            List<StaffunBean> existUser = sessionFactory.getMapper(UserMapper.class).queryStaffun(queryBean);
            if(CollectionUtils.isEmpty(existUser)){
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "该部门下没有人员", void.class);
            }

            AssetRegistBean assetRegistBean = sessionFactory.getMapper(AssetRegistMapper.class).selectByPrimaryKey(bean.getRegisterId());
            //检测资产 是否 为软件类型
            int exisoff = mapper.countBysoff(assetRegistBean.getAssetTypeId());
            if(exisoff>0){
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "该资产为软件资产，请走正版软件申请", void.class);
            }

            //检测是否有足够数量分配
            if (assetRegistBean.getQty()-assetRegistBean.getOverQty()<bean.getPortionQty()) {
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "登记单未分配数量不够", void.class);
            }
            bean.setAssetTypeId(assetRegistBean.getAssetTypeId());
            bean.setGoodsModel(assetRegistBean.getGoodsModel());

            //设置单据编号
            String maxBillNo = mapper.maxBillNo();//获取当天最大的单据编号
            bean.setPortionBillNo(CommonUtil.generateStream("FP", "", maxBillNo));

            bean.setCreateAccount(SessionUtil.getUseerInfoCode());
            bean.setCreateName(SessionUtil.getUseerInfoName());
            bean.setCreateTime(CommonUtil.getCurrentTime());
            mapper.insertSelective(bean);                               //写库
            mapper.updateRegistQty(bean.getPortionQty(),assetRegistBean.getId());  //更新资产登记已分配数量
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 删除资产分配
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("deletePortion")
    public MessageBean deletePortion(SessionFactory factory, String id) {

        try {
            AssetPortionMapper mapper = factory.getMapper(AssetPortionMapper.class);
            AssetPortionDetMapper detmapper = factory.getMapper(AssetPortionDetMapper.class);

            int i = detmapper.countByPortionId(id);
            if(i>0){
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "已存在分配记录不允许删除", void.class);
            }

            AssetPortionBean assetPortionBean = mapper.selectByPrimaryKey(id);

            mapper.deleteByPrimaryKey(id);
            mapper.updateRegistQtyjian(assetPortionBean.getPortionQty(),assetPortionBean.getRegisterId());//把数量加回登记单
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 更新资产分配
     * @param sessionFactory
     * @param bean
     * @return
     */
    @TaskAnnotation("updatePortion")
    public MessageBean updatePortion(SessionFactory sessionFactory, AssetPortionBean bean) {

        try {
            AssetPortionMapper mapper = sessionFactory.getMapper(AssetPortionMapper.class);
            AssetRegistMapper registmapper = sessionFactory.getMapper(AssetRegistMapper.class);
            AssetPortionDetMapper detmapper = sessionFactory.getMapper(AssetPortionDetMapper.class);

            int overqty = detmapper.countByPortionId(bean.getId());//获取单据分发到个人的数量
            if(bean.getPortionQty()<overqty){
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "单据已分配了"+overqty+"台，无法减少", void.class);
            }

            AssetPortionBean oldportionBean = mapper.selectByPrimaryKey(bean.getId());
            if(!bean.getDepatCode().equals(oldportionBean.getDepatCode())){
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "部门与原来单据的部门不一致", void.class);
            }
            AssetRegistBean registBean = registmapper.selectByPrimaryKey(bean.getRegisterId());
            double vQty = bean.getPortionQty() - oldportionBean.getPortionQty();
            //检测登记单是否有足够数量分配
            double overQty = (registBean.getOverQty() + vQty);
            double checkQty = registBean.getQty() - overQty;    //登记数量 - 分配数量
            if (checkQty<0) {
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "登记单号为"+registBean.getBillNo()+"未分配数量不足", void.class);
            }

            mapper.updateByPrimaryKeySelective(bean);   //写库
            mapper.updateOverQty(overQty,bean.getRegisterId()); //更新资产登记单的已分配数量
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

}
