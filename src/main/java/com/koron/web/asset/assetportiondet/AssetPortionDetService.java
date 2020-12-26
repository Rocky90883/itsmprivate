package com.koron.web.asset.assetportiondet;

import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.ConversionUtil;
import com.koron.util.SessionUtil;
import com.koron.web.asset.assetportion.AssetPortionMapper;
import com.koron.web.asset.assetportion.bean.AssetPortionBean;
import com.koron.web.asset.assetportiondet.bean.AssetPortionDetBean;
import com.koron.web.asset.assetportiondet.bean.AssetPortionDetQueryBean;
import com.koron.web.asset.assetregist.AssetRegistMapper;
import com.koron.web.asset.assetregist.bean.AssetRegistBean;
import com.koron.web.asset.assets.AssetsMapper;
import com.koron.web.asset.assets.attributes.AttributesBean;
import com.koron.web.asset.assets.attributes.AttributesMapper;
import com.koron.web.asset.assets.bean.AssetsBean;
import com.koron.web.asset.assettype.AssetTypeMapper;
import com.koron.web.asset.assettype.bean.AssetTypeQueryBean;
import com.koron.web.systemmanger.roles.SysRoleMapper;
import com.koron.web.systemmanger.roles.bean.RoleStaffunEntity;
import com.koron.web.systemmanger.user.UserMapper;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.systemmanger.user.bean.StaffunQueryBean;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.swan.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetPortionDetService {

    private static Logger log = Logger.getLogger(AssetPortionDetService.class);

    /**
     * 资产分配明细列表
     * @param factory
     * @param queryBean
     * @return
     */
    @TaskAnnotation("queryList")
    public List<AssetPortionDetBean> queryList(SessionFactory factory, AssetPortionDetQueryBean queryBean) {
        List<AssetPortionDetBean> assetslist;
        try {
            AssetPortionDetMapper mapper = factory.getMapper(AssetPortionDetMapper.class);
            assetslist = mapper.queryList(queryBean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return assetslist;
    }

    /**
     * 资产分配明细列表
     * @param factory
     * @param queryBean
     * @return
     */
    @TaskAnnotation("queryStaffunbydeptCode")
    public List<StaffunBean> queryStaffunbydeptCode(SessionFactory factory, StaffunQueryBean queryBean) {
        List<StaffunBean> staffunBeans = new ArrayList<>();
        try {
            AssetPortionDetMapper mapper = factory.getMapper(AssetPortionDetMapper.class);
            SysRoleMapper rolemapper = factory.getMapper(SysRoleMapper.class);
            UserMapper userMapper = factory.getMapper(UserMapper.class);
            List<RoleStaffunEntity> rolelist = rolemapper.roleStaffunByStaffunCode(SessionUtil.getUseerInfoCode());
            if(CollectionUtils.isNotEmpty(rolelist)) {
                List<String> collect = rolelist.stream().map(RoleStaffunEntity::getRoleId).collect(Collectors.toList());
//                //员工不允许分配直接返回空列表
//                if(collect.contains("e0443cc90dbe4b6785ce73f1971b2f97")){
//                    return staffunBeans;
//                }
//                //IT资产员 资管员可以看所属部门里所有人
//                if (collect.contains("e0443cc90dbe4b6785ce73f1971b2f97")) {
//                    queryBean.setOrgNodeCode(SessionUtil.getLoginUser().getOrgNodeCode());
//                }
//                //IT经理 看全部
//                if (collect.contains("ae81014b72ca4286a3bf0dda5c08bfc0")) {
//                    queryBean.setOrgNodeCode(null);
//                }
//                //信息中心人员
//                if (collect.contains("93a80214a569401d92f934c68db5b8bc")) {
//                    queryBean.setOrgNodeCode(null);
//                }
//                //IT运维人员
//                if (collect.contains("1999ebc166674b8f910f4a8395b31f92")) {
//                    queryBean.setOrgNodeCode(null);
//                }
                staffunBeans = userMapper.queryStaffun(queryBean);
                return staffunBeans;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return staffunBeans;
    }

    /**
     * 新增资产分配明细
     * @param sessionFactory
     * @param bean
     * @return
     */
    @TaskAnnotation("addPortionDet")
    public MessageBean addPortionDet(SessionFactory sessionFactory, AssetPortionDetBean bean) {

        try {
            bean.setId(CommonUtil.get32Key());
            AssetPortionDetMapper mapper = sessionFactory.getMapper(AssetPortionDetMapper.class);
            UserMapper userMapper = sessionFactory.getMapper(UserMapper.class);
            StaffunBean staffunBean = userMapper.getStaffByLoginId(bean.getEmpId());    //获取被分配人
            //获取主表bean
            AssetPortionBean portionBean = sessionFactory.getMapper(AssetPortionMapper.class).selectByPrimaryKey(bean.getfRef());
            //比对被分配人 于 主表的部门是否一致
            if(StringUtils.isEmpty(staffunBean.getOrgNodeCode()) || !portionBean.getDepatCode().equals(staffunBean.getOrgNodeCode())){
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "不能分配到部门以外的人员", void.class);
            }
            //获取明细总数量
            int qty = mapper.countByPortionId(bean.getfRef());
            if (portionBean.getPortionQty()<qty+1) {
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "单据数量不足无法分配", void.class);
            }

            bean.setCreateAccount(SessionUtil.getUseerInfoCode());
            bean.setCreateName(SessionUtil.getUseerInfoName());
            bean.setCreateTime(CommonUtil.getCurrentTime());


            //分配个人时同时插入台账信息
            AssetsBean assetsbean = new AssetsBean();
            AssetsMapper assetsMapper = sessionFactory.getMapper(AssetsMapper.class);

            String assetsId = CommonUtil.get32Key();
            assetsbean.setId(assetsId);
            assetsbean.setAssetTypeId(portionBean.getAssetTypeId());
            assetsbean.setGoodsModel(portionBean.getGoodsModel());
            assetsbean.setRegisterId(portionBean.getRegisterId());
            assetsbean.setPortionId(bean.getfRef());                //台账的分配id记录的是分配主表的id
            assetsbean.setDepatCode(portionBean.getDepatCode());
            assetsbean.setAssetsStatus(0);                          //分配到个人的资产是本人确认。
            assetsbean.setRegisStatus(1);                           //已登记
            //获取登记单bean
            AssetRegistBean registbean = sessionFactory.getMapper(AssetRegistMapper.class).selectByPrimaryKey(portionBean.getRegisterId());
            //供应商
            if(StringUtils.isNotBlank(registbean.getCompanyId())){
                assetsbean.setCompanyId(registbean.getCompanyId());
            }
            //品牌
            if(StringUtils.isNotBlank(registbean.getBrand())){
                assetsbean.setBrand(registbean.getBrand());
            }

            assetsbean.setEmpId(bean.getEmpId());

            AssetTypeQueryBean queryBean = new AssetTypeQueryBean();
            String typecode = sessionFactory.getMapper(AssetTypeMapper.class).selectByPrimaryKey(portionBean.getAssetTypeId()).getTypecode();
            String maxAssetsNumber = assetsMapper.maxAssetsNumber();
            String assetsNumber = CommonUtil.generateStream(typecode, "", maxAssetsNumber);
            assetsbean.setAssetsNumber(assetsNumber);


            assetsbean.setCreateAccount(SessionUtil.getUseerInfoCode());
            assetsbean.setCreateName(SessionUtil.getUseerInfoName());
            assetsbean.setCreateTime(CommonUtil.getCurrentTime());
            assetsMapper.insertSelective(assetsbean);                       //入台账库
            addAttributes(sessionFactory,assetsbean.getId(),portionBean);   //入台账属性库

            bean.setAssetsNumber(assetsNumber);         //把台账信息的资产编号回填回来
            bean.setSourceId(assetsId);                 //把台账信息id回填回来
            mapper.insertSelective(bean);               //入库
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 删除资产分配明细
     * @param sessionFactory
     * @param id
     * @return
     */
    @TaskAnnotation("deletePortionDet")
    public MessageBean deletePortionDet(SessionFactory sessionFactory, String id) {

        try {
            AssetPortionDetMapper mapper = sessionFactory.getMapper(AssetPortionDetMapper.class);
            AssetsMapper assetsMapp = sessionFactory.getMapper(AssetsMapper.class);
            AssetPortionDetBean assetPortionDetBean = mapper.selectByPrimaryKey(id);
            AssetsBean assetsBean = assetsMapp.selectByPrimaryKey(assetPortionDetBean.getSourceId());
            //资产是否曾经发生转移
            if(StringUtils.isNotEmpty(assetsBean.getAssShiftId())){
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "资产曾经发生转移不允许删除", void.class);
            }
            //资产是否报废
            if(StringUtils.isNotEmpty(assetsBean.getScrappedId())){
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "资产已报废不允许删除", void.class);
            }
            mapper.deleteByPrimaryKey(id);//写库
            assetsMapp.deleteByPrimaryKey(assetPortionDetBean.getSourceId());//删除台账记录
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 更新资产分配明细
     * @param sessionFactory
     * @param bean
     * @return
     */
    @TaskAnnotation("updatePortionDet")
    public MessageBean updatePortionDet(SessionFactory sessionFactory, AssetPortionDetBean bean) {

        try {
            AssetPortionDetMapper mapper = sessionFactory.getMapper(AssetPortionDetMapper.class);

            mapper.updateByPrimaryKeySelective(bean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 插入台账同时插入对应的台账属性
     * @param sessionFactory
     * @param assetId   台账id
     * @param bean      分配主表
     */
    public static void addAttributes(SessionFactory sessionFactory,String assetId,AssetPortionBean bean){

        try {
            AttributesMapper mapper = sessionFactory.getMapper(AttributesMapper.class);
            AssetRegistBean registBean = sessionFactory.getMapper(AssetRegistMapper.class).selectByPrimaryKey(bean.getRegisterId());

            //登记属性装台账属性
            AttributesBean atbBean = new AttributesBean();

            atbBean = ConversionUtil.registCoattributes(registBean,atbBean);
            atbBean.setAssetId(assetId);
            mapper.insertSelective(atbBean);
        } catch (Exception e) {
            log.error("插入台账同时插入对应的台账属性异常");
            e.printStackTrace();
        }

        return;
    }
}
