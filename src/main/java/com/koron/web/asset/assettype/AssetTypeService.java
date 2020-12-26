package com.koron.web.asset.assettype;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.asset.assets.AssetsMapper;
import com.koron.web.asset.assettype.bean.AssetTypeBean;
import com.koron.web.asset.assettype.bean.AssetTypeQueryBean;
import com.koron.web.asset.assettype.bean.SelectBean;
import com.koron.web.asset.assettype.bean.TreeAssetTypeBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.swan.bean.MessageBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AssetTypeService {

    private static Logger log = Logger.getLogger(AssetTypeService.class);


    /**
     * 添加分类
     * @param sessionFactory
     * @param bean
     * @return
     */
    @TaskAnnotation("addassettype")
    public MessageBean addassettype(SessionFactory sessionFactory, AssetTypeBean bean) {

        try {
            bean.setId(CommonUtil.get32Key());
            AssetTypeMapper mapper = sessionFactory.getMapper(AssetTypeMapper.class);
            bean.setCreateAccount(SessionUtil.getUseerInfoCode());
            bean.setCreateTime(CommonUtil.getCurrentTime());
            handCode(mapper,bean);
            mapper.addassettype(bean);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }


    /**
     * 自动生成分级code
     * @param mapper
     * @param bean
     */
    public static void handCode(AssetTypeMapper mapper,AssetTypeBean bean) {
        //生成code, 规则5位一级，下级自动继承上级
        String parentCode="";
        AssetTypeBean parentBean=null;
        if(StringUtils.isNotBlank(bean.getParentcode()) && !bean.getParentid().equals("0")) {
            parentBean = mapper.selectByPrimaryKey(bean.getParentid()); //通过id查询数据
            parentCode = parentBean.getClasscode();
        }
        //取父级目录下最大的下级目录
        String maxCode = mapper.findMaxChild(parentCode);//通过行政区域编号查询
        if(StringUtils.isBlank(maxCode)) {//如果未找到，说明下面没有子级，给初始值
            maxCode = parentCode+"00000";
        }
        //未位加1
        long last5 = Long.parseLong("1"+maxCode.substring(maxCode.length()-5))+1;
        maxCode = maxCode.substring(0, maxCode.length()-5)+(last5+"").substring(1);
        //重设code
        bean.setClasscode(maxCode);

//		SelectBean selectBean = mapper.findDistrictByCode(parentCode+"00001");
//		if(selectBean!=null){
//			//bean.setIsLeaf(selectBean.getIsLeaf());
//		}else{
//			bean.setIsLeaf(1);
//		}

        bean.setIscatalog(1);//新增加的数据，都是叶结点
        //自动更新其上级为目录
        if(parentBean != null) {
            parentBean.setIscatalog(0);
            parentBean.setUpdateTime(CommonUtil.getCurrentTime());
            mapper.updateByPrimaryKeySelective(parentBean);
        }
    }


    /**
     * 分类树
     * @param factory
     * @return
     */
    @TaskAnnotation("assetTypeTree")
    public MessageBean assetTypeTree(SessionFactory factory) {

        @SuppressWarnings("rawtypes")
        MessageBean<TreeAssetTypeBean> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", TreeAssetTypeBean.class);

        try {
            AssetTypeMapper mapper = factory.getMapper(AssetTypeMapper.class);

            AssetTypeBean selectBean = new AssetTypeBean();
//			selectBean.setIsLeaf(0);//只查询目录
            selectBean.setClasscode("all");
            List<AssetTypeBean> list = mapper.findAssetType(selectBean);
            TreeAssetTypeBean treeBean = new TreeAssetTypeBean();
            //创建根目录
            treeBean.setId("0");
            treeBean.setClasscode("");
            treeBean.setName("根目录");
            treeBean.setParent("");
            treeBean.setIsParent(true);
            treeBean.setIscatalog(1);
            //递归下级目录
            recTree(list,treeBean);

            info.setCode(Constant.MESSAGE_INT_SUCCESS);
            info.setDescription("success");
            info.setData(treeBean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());

        }
        return info;
    }


    /**
     * 递归查询下级目录树
     * @param list
     * @param treeBean
     */
    private void recTree(List<AssetTypeBean> list,TreeAssetTypeBean treeBean) {
        for(AssetTypeBean bean:list) {
            //如果code是以父级开头，且长度多5位，说明这是他的下级
            if(bean.getClasscode().startsWith(treeBean.getClasscode()) && bean.getClasscode().length()==treeBean.getClasscode().length()+5) {
                TreeAssetTypeBean b = new TreeAssetTypeBean();
                b.setId(bean.getId()+"");
                b.setClasscode(bean.getClasscode());
                b.setName(bean.getTypename());
                b.setParent(treeBean.getId()+"");
                b.setIsParent(false);
                treeBean.setIscatalog(0);
                treeBean.setIsParent(true);
                treeBean.setIscatalog(1);
                treeBean.getChildren().add(b);
                recTree(list,b); //递归循环下级目录
            }
        }
    }


    /**
     * 资产分类列表
     * @param factory
     * @return
     */
    @TaskAnnotation("queryList")
    public MessageBean queryList(SessionFactory factory, AssetTypeQueryBean bean) {

        @SuppressWarnings("rawtypes")
        MessageBean<PageInfo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", PageInfo.class);

        try {
            List<AssetTypeBean> beans = new ArrayList<AssetTypeBean>();
            AssetTypeMapper mapper = factory.getMapper(AssetTypeMapper.class);
//            bean.setIsLeaf(-1);//查所有数据
            PageHelper.startPage(bean.getPage(), bean.getPageCount());
            beans = mapper.queryList(bean);

            info.setData(new PageInfo<>(beans));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return info;
    }

    /**
     * 详情
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("detail")
    public AssetTypeBean detail(SessionFactory factory, String id) {
        try {
            AssetTypeMapper mapper = factory.getMapper(AssetTypeMapper.class);
            AssetTypeBean bean = mapper.selectByPrimaryKey(id);

            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 查资产分类上级
     * @param factory
     * @return
     */
    @TaskAnnotation("assetTypeSuperList")
    public MessageBean assetTypeSuperList(SessionFactory factory, AssetTypeQueryBean bean) {

        @SuppressWarnings("rawtypes")
        MessageBean<HashMap> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", HashMap.class);

        try {
            List<AssetTypeBean> beans = new ArrayList<AssetTypeBean>();
            AssetTypeMapper mapper = factory.getMapper(AssetTypeMapper.class);
//            bean.setIsLeaf(-1);//查所有数据
            PageHelper.startPage(bean.getPage(), bean.getPageCount());
            beans = mapper.assetTypeSuperList(bean);

            HashMap<String,Object> map = new HashMap<>();
            map.put("pageInfo", new PageInfo<>(beans));

            //找出所有上级目录，显示分级导航
            if(bean.getClasscode()!=null && !bean.getClasscode().equals("")){
                ArrayList<HashMap<String,String>> navList = new ArrayList<>();
                recParent(mapper, bean.getClasscode(), navList);
                map.put("navData", navList);
            }

            info.setCode(Constant.MESSAGE_INT_SUCCESS);
            info.setDescription("success");
            info.setData(map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return info;
    }

    /**
     * 递归查询每一级目录
     * @param classcode
     * @param navList
     */
    private void recParent(AssetTypeMapper mapper,String classcode,ArrayList<HashMap<String,String>> navList) {
        if(classcode.length()>0){
            SelectBean selb = mapper.findAssetTypeByCode(classcode);
            HashMap<String,String> m = new HashMap<>();
            m.put("code", classcode);
            m.put("name", selb.getTypeName());
            //存储id和parentId
            m.put("id", selb.getId());
            m.put("parentId", selb.getParentId());
            navList.add(0,m);
            recParent(mapper,classcode.substring(0, classcode.length()-5),navList);
        }
    }

    /**
     * 更新资产分类
     * @param factory
     * @return
     */
    @TaskAnnotation("updateAssetType")
    public MessageBean updateAssetType(SessionFactory factory,AssetTypeBean bean) {

        try {
            AssetTypeMapper mapper = factory.getMapper(AssetTypeMapper.class);

            bean.setUpdateAccount(SessionUtil.getUseerInfoCode());
            bean.setUpdateName(SessionUtil.getUseerInfoName());
            bean.setUpdateTime(CommonUtil.getCurrentTime());

            //查询原数据，比较是否修改父级目录
            AssetTypeBean oldBean = mapper.selectByPrimaryKey(bean.getId()+"");
            if(!(oldBean.getParentid()+"").equals((bean.getParentid()+""))) {
                //重新生成code
                handCode(mapper, bean);
            }
            mapper.updateByPrimaryKeySelective(bean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 删除资产分类
     * @param factory
     * @return
     */
    @TaskAnnotation("deleteAssetType")
    public MessageBean deleteAssetType(SessionFactory factory,AssetTypeBean bean) {

        try {
            AssetTypeMapper mapper = factory.getMapper(AssetTypeMapper.class);
            AssetsMapper assetsmapper = factory.getMapper(AssetsMapper.class);

            if(StringUtils.isEmpty(bean.getId())){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "非法参数", void.class);
            }
            AssetTypeBean assetTypeBean = mapper.selectByPrimaryKey(bean.getId());
            if(assetTypeBean!=null && assetTypeBean.getStatus()!=null && assetTypeBean.getStatus()==1){
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "该数据类型为系统默认数据类型不允许删除", void.class);
            }
            int typeQty = assetsmapper.countByType(assetTypeBean.getId());
            if(typeQty>0){
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "资产台账已经有资产使用该类型，不允许删除", void.class);
            }
            //查询是否存在下级
            if(mapper.countByParentid(bean.getId())>0){
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "分类存在子分类不允许删除", void.class);
            }
            mapper.deleteByPrimaryKey(bean.getId());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }


    /**
     * 选择资产分类
     * @param factory
     * @return
     */
    @TaskAnnotation("selectAssetTypeList")
    public MessageBean selectAssetTypeList(SessionFactory factory,Integer type) {

        @SuppressWarnings("rawtypes")
        MessageBean<List> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);

        try {
            List<SelectBean> list = new ArrayList<SelectBean>();
            AssetTypeMapper mapper = factory.getMapper(AssetTypeMapper.class);
            if(type == 1){
                list = mapper.selectAssetTypeList();
            }
            if(type == 2){
                list = mapper.selectAssetTypeListSpare();
            }
            info.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return info;
    }


}
