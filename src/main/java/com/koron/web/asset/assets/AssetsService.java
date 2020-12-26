package com.koron.web.asset.assets;

import com.koron.util.*;
import com.koron.web.asset.assets.assetsrepdet.AssetsRepdetMapper;
import com.koron.web.asset.assets.assetsrepdet.bean.AssetsRepdetBean;
import com.koron.web.asset.assets.assetsrepdet.bean.AssetsRepdetDto;
import com.koron.web.asset.assets.attributes.AttributesBean;
import com.koron.web.asset.assets.attributes.AttributesMapper;
import com.koron.web.asset.assets.bean.AssetsBean;
import com.koron.web.asset.assets.bean.AssetsQueryBean;
import com.koron.web.asset.assets.bean.ImportAssetBean;
import com.koron.web.asset.assets.bean.ImportAssetErrBean;
import com.koron.web.asset.assettype.AssetTypeMapper;
import com.koron.web.asset.assettype.bean.AssetTypeBean;
import com.koron.web.asset.assettype.bean.AssetTypeQueryBean;
import com.koron.web.asset.myasset.bean.MyAssetDto;
import com.koron.web.meeting.ImportMeetingErrBean;
import com.koron.web.systemmanger.dept.DepartMapper;
import com.koron.web.systemmanger.dept.DepartunBean;
import com.koron.web.systemmanger.dictionary.DictionaryMapper;
import com.koron.web.systemmanger.dictionary.bean.Parameter;
import com.koron.web.systemmanger.user.UserMapper;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.swan.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssetsService {

    private static Logger log = Logger.getLogger(AssetsService.class);

    /**
     * 资产台账列表
     * @param factory
     * @param queryBean
     * @return
     */
    @TaskAnnotation("queryList")
    public List<AssetsBean> queryList(SessionFactory factory, AssetsQueryBean queryBean) {
        List<AssetsBean> assetslist;
        try {
            AssetsMapper mapper = factory.getMapper(AssetsMapper.class);
            assetslist = mapper.queryList(queryBean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return assetslist;
    }



    /**
     * 新增资产台账
     * @param sessionFactory
     * @param bean
     * @return
     */
    @TaskAnnotation("addAssets")
    public MessageBean addAssets(SessionFactory sessionFactory, AssetsBean bean) {
        MessageBean<String> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", String.class);
        try {
            String id= CommonUtil.get32Key();
            bean.setId(id);
            AssetsMapper mapper = sessionFactory.getMapper(AssetsMapper.class);

            //设置流水号
            AssetTypeQueryBean queryBean = new AssetTypeQueryBean();
            String typecode = sessionFactory.getMapper(AssetTypeMapper.class).selectByPrimaryKey(bean.getAssetTypeId()).getTypecode();
            String maxAssetsNumber = mapper.maxAssetsNumber();
            String assetsNumber = CommonUtil.generateStream(typecode, "", maxAssetsNumber);
            bean.setAssetsNumber(assetsNumber);

            bean.setCreateAccount(SessionUtil.getUseerInfoCode());
            bean.setCreateName(SessionUtil.getUseerInfoName());
            bean.setCreateTime(CommonUtil.getCurrentTime());
            mapper.insertSelective(bean);

            success.setData(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return success;
    }

    /**
     * 删除资产台账
     * @param sessionFactory
     * @param id
     * @return
     */
    @TaskAnnotation("deleteAssets")
    public MessageBean deleteAssets(SessionFactory sessionFactory, String id) {

        try {
            AssetsMapper mapper = sessionFactory.getMapper(AssetsMapper.class);

            mapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 更新资产台账
     * @param sessionFactory
     * @param bean
     * @return
     */
    @TaskAnnotation("updateAssets")
    public MessageBean updateAssets(SessionFactory sessionFactory, AssetsBean bean) {

        try {
            AssetsMapper mapper = sessionFactory.getMapper(AssetsMapper.class);

            mapper.updateByPrimaryKeySelective(bean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 我的资产
     * @param factory
     * @param queryBean
     * @return
     */
    @TaskAnnotation("queryMyAssetList")
    public List<AssetsBean> queryMyAssetList(SessionFactory factory, AssetsQueryBean queryBean) {
        List<AssetsBean> assetslist;
        try {
            AssetsMapper mapper = factory.getMapper(AssetsMapper.class);
            queryBean.setEmpId(SessionUtil.getUseerInfoCode());
            assetslist = mapper.queryList(queryBean);
            assetslist= assetslist.stream().filter(a -> a.getAssetsStatus() != -1).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return assetslist;
    }

    /**
     * 资产确认、接收、驳回
     * @param sessionFactory
     * @param dto 操作 1确认、2接收、3驳回
     * @return
     */
    @TaskAnnotation("assetOpt")
    public MessageBean assetOpt(SessionFactory sessionFactory, MyAssetDto dto) {

        try {
            AssetsMapper mapper = sessionFactory.getMapper(AssetsMapper.class);
            AssetsBean assetsBean = mapper.selectByPrimaryKey(dto.getAssetId());

            //操作等于确认  且  资产状态为待确认
            if(dto.getOpt()==1 && assetsBean.getAssetsStatus()==0){
                mapper.confirmAsset(dto.getAssetId());
            }
            //操作等于接收  且  资产状态为待接收
            if(dto.getOpt()==2 && assetsBean.getAssetsStatus()==1){
                mapper.confirmAsset(dto.getAssetId());
            }
            //操作等于确认  且  资产状态为待确认
            if(dto.getOpt()==3 && assetsBean.getAssetsStatus()==0){

                mapper.deletBySourceiId(assetsBean.getId());
                mapper.deleteByPrimaryKey(assetsBean.getId());

            }
            //操作等于接收  且  资产状态为待接收
            if(dto.getOpt()==3 && assetsBean.getAssetsStatus()==1){
//                mapper.confirmAsset(dto.getAssetId());
            }



        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }


    /**
     * 资产属性
     * @param factory
     * @param id       台账id
     * @return
     */
    @TaskAnnotation("assetAttributes")
    public AttributesBean assetAttributes(SessionFactory factory, String id) {
        AttributesBean atblist;
        try {
            AttributesMapper mapper = factory.getMapper(AttributesMapper.class);
            atblist = mapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return atblist;
    }

    /**
     * 查询资产维护记录
     * @param factory
     * @param dto
     * @return
     */
    @TaskAnnotation("queryRepdetList")
    public List<AssetsRepdetBean> queryRepdetList(SessionFactory factory, AssetsRepdetDto dto) {
        List<AssetsRepdetBean> list;
        try {
            AssetsRepdetMapper mapper = factory.getMapper(AssetsRepdetMapper.class);
            list = mapper.queryList(dto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }


    /**
     * 台账导入
     * @param factory
     * @param list
     * @return
     */
    @TaskAnnotation("assetImport")
    public List<ImportAssetErrBean> assetImport(SessionFactory factory, List<ImportAssetBean> list) {
        List<ImportAssetErrBean> errlist = new ArrayList<>();
        try {
            //获取所有部门
            List<DepartunBean> departmentAll = factory.getMapper(DepartMapper.class).getDepartmentAll();

            //获取所有人员
            List<StaffunBean> allStaffun = factory.getMapper(UserMapper.class).getAllStaffun();

            //获取所有资产类型
            List<AssetTypeBean> allAssetType = factory.getMapper(AssetTypeMapper.class).queryAllmjList();

            //获取所有品牌
            List<Parameter> allBrand = factory.getMapper(DictionaryMapper.class).paramListByCode("brand_type");

            List<AssetsBean> assetslist = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                ImportAssetBean imp = list.get(i);
                AssetsBean assetbean = new AssetsBean();

                AssetsBean assetsBean1 = new AssetsBean();

                //check部门
                String orgNodeCode = "";
                Optional<DepartunBean> optdep = departmentAll.stream().filter(d -> d.getName().equals(imp.getDepatName())).findFirst();
                if (optdep.isPresent()) {
                    orgNodeCode = optdep.get().getCode();
                    assetbean.setDepatCode(orgNodeCode);
                } else {
                    ImportAssetErrBean errbean = BeanCovertUtil.beanCovert(imp, ImportAssetErrBean.class);
                    errbean.setErrMsg("找不到对应的部门");
                    errlist.add(errbean);
                    continue;
                }

                //check人员
                Optional<StaffunBean> optstaff = allStaffun.stream()
                        .filter(s -> s.getName().equals(imp.getStaffunName()) && s.getOrgNodeCode().equals(assetbean.getDepatCode()))
                        .findFirst();
                if (optstaff.isPresent()) {
                    assetbean.setEmpId(optstaff.get().getCode());
                } else {
                    ImportAssetErrBean errbean = BeanCovertUtil.beanCovert(imp, ImportAssetErrBean.class);
                    errbean.setErrMsg("部门里找不到对应人员");
                    errlist.add(errbean);
                    continue;
                }

                //check资产类型
                Optional<AssetTypeBean> optType = allAssetType.stream()
                        .filter(t -> t.getTypename().equals(imp.getAssetTypeName()))
                        .findFirst();
                if (optType.isPresent()) {
                    assetbean.setAssetTypeId(optType.get().getId());
                } else {
                    ImportAssetErrBean errbean = BeanCovertUtil.beanCovert(imp, ImportAssetErrBean.class);
                    errbean.setErrMsg("找不到对应的资产分类项目");
                    errlist.add(errbean);
                    continue;
                }

                //check品牌
                Optional<Parameter> optBand = allBrand.stream()
                        .filter(b -> b.getParameterName().equals(imp.getBrandName()))
                        .findFirst();
                if (optBand.isPresent()) {
                    assetbean.setBrand(optBand.get().getParameterValue());
                } else {
                    ImportAssetErrBean errbean = BeanCovertUtil.beanCovert(imp, ImportAssetErrBean.class);
                    errbean.setErrMsg("找不到对应品牌，请在字典里维护");
                    errlist.add(errbean);
                    continue;
                }

                assetbean.setGoodsModel(imp.getGoodsModel());   //资产型号
                assetbean.setSnNumber(imp.getSnNumber());       //sn号
                assetbean.setFixedNumber(imp.getFixedNumber()); //固定资产编号
                assetbean.setAssetsStatus(2);   //使用中
                assetbean.setRegisStatus(1);    //已登记

                //待插入台账
//                assetslist.add(assetbean);
                AttributesBean atb = new AttributesBean();
                MessageBean<String> assetMsg = addAssets(factory, assetbean);
                if (assetMsg.getCode() == 0) {
                    atb = ConversionUtil.impCoattributes(imp, atb);
                    atb.setAssetId(assetMsg.getData());
                    //插入台账属性
                    factory.getMapper(AttributesMapper.class).insertSelective(atb);
                } else {
                    ImportAssetErrBean errbean = BeanCovertUtil.beanCovert(imp, ImportAssetErrBean.class);
                    errbean.setErrMsg("入库台账异常");
                    errlist.add(errbean);
                    continue;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return errlist;
    }


}
