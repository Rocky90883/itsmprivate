package com.koron.web.systemmanger.model;

import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.systemmanger.model.bean.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.swan.bean.MessageBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysModelService {

    private static Logger log = Logger.getLogger(SysModelService.class);

    /**
     * 模块菜单列表
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("queryList")
    public List<SysModelBean> queryList(SessionFactory factory, SysModelQueryBean bean) {
        List<SysModelBean> assetslist;
        try {
            SysModelMapper mapper = factory.getMapper(SysModelMapper.class);

            assetslist = mapper.queryList(bean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return assetslist;
    }

    /**
     * 新增模块菜单
     * @param sessionFactory
     * @param bean
     * @return
     */
    @TaskAnnotation("addSysModel")
    public MessageBean addSysModel(SessionFactory sessionFactory, SysModelBean bean) {

        try {
            bean.setId(CommonUtil.get32Key());
            SysModelMapper mapper = sessionFactory.getMapper(SysModelMapper.class);
            //父id不为空带上父code
            if(!StringUtils.isBlank(bean.getParentId())){
                bean.setParentCode(mapper.selectByPrimaryKey(bean.getParentId()).getCode());
            }else{
                bean.setParentId("root");
            }

            handCode(mapper,bean);
            bean.setCreateAccount(SessionUtil.getUseerInfoCode());
            bean.setCreateName(SessionUtil.getUseerInfoName());
            bean.setCreateTime(CommonUtil.getCurrentTime());
            mapper.insertSelective(bean);                               //写库
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 自动生成分级code
     * @param mapper
     * @param bean
     */
    public static void handCode(SysModelMapper mapper, SysModelBean bean) {
        //生成code, 规则5位一级，下级自动继承上级
        String parentCode="";
        SysModelBean parentBean=null;
        if(StringUtils.isNotBlank(bean.getParentCode()) && !bean.getParentId().equals("0")) {
            parentBean = mapper.selectByPrimaryKey(bean.getParentId()); //通过id查询数据
            parentCode = parentBean.getCode();
        }
        //取父级目录下最大的下级目录
        String maxCode = mapper.findMaxChild(parentCode);//通过父code查询
        if(StringUtils.isBlank(maxCode)) {//如果未找到，说明下面没有子级，给初始值
            maxCode = parentCode+"00000";
        }
        //未位加1
        long last5 = Long.parseLong("1"+maxCode.substring(maxCode.length()-5))+1;
        maxCode = maxCode.substring(0, maxCode.length()-5)+(last5+"").substring(1);
        //重设code
        bean.setCode(maxCode);
        bean.setIsPage(1);//新增加的数据，都是叶结点
        //自动更新其上级为目录
        if(parentBean != null) {
            parentBean.setIsPage(0);
            parentBean.setUpdateTime(CommonUtil.getCurrentTime());
            mapper.updateByPrimaryKeySelective(parentBean);
        }
    }

    /**
     * 删除模块菜单
     * @param sessionFactory
     * @param id
     * @return
     */
    @TaskAnnotation("deleteSysModel")
    public MessageBean deleteSysModel(SessionFactory sessionFactory, String id) {

        try {
            SysModelMapper mapper = sessionFactory.getMapper(SysModelMapper.class);
            //查询是否存在下级
            if(mapper.countByParentid(id)>0){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "分类存在子接口不允许删除", void.class);
            }
            mapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 更新模块菜单
     * @param sessionFactory
     * @param bean
     * @return
     */
    @TaskAnnotation("updateSysModel")
    public MessageBean updateSysModel(SessionFactory sessionFactory, SysModelBean bean) {

        try {
            SysModelMapper mapper = sessionFactory.getMapper(SysModelMapper.class);

            //查询原数据，比较是否修改父级目录
            SysModelBean oldBean = mapper.selectByPrimaryKey(bean.getId()+"");
            if(!(oldBean.getParentId()+"").equals((bean.getParentId()+""))) {
                //重新生成code
                handCode(mapper, bean);
            }
            
            mapper.updateByPrimaryKeySelective(bean);   //写库
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }


    /**
     * 模块树
     * @param sessionFactory
     * @return
     */
    @TaskAnnotation("findModelTree")
    public List<ModelTreeBean>  findModelTree(SessionFactory sessionFactory){
        
        Map<String,Object> data = new HashMap<String,Object>();
        try {
            SysModelMapper mapper = sessionFactory.getMapper(SysModelMapper.class);
            //查询所有菜单
            List<ModelTreeBean> allMenu = mapper.findAllModel();
            //根节点
            List<ModelTreeBean> rootModel = new ArrayList<ModelTreeBean>();
            for (ModelTreeBean nav : allMenu) {
                if (nav.getParentId().equals("root")){ //父节点是root的，为一级菜单
                    rootModel.add(nav);
                }
            }
            /* 根据Menu类的order排序 */
//            Collections.sort(rootModel, order());
            //为根菜单设置子菜单，getClild是递归调用的
            for (ModelTreeBean nav : rootModel) {
                /* 获取根节点下的所有子节点 使用getChild方法*/
                List<ModelTreeBean> childList = getChild(nav.getId(), allMenu);
                nav.setChildren(childList);//给根节点设置子节点
            }

            return rootModel;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * 获取子节点
     * @param id 父节点id
     * @param allModel 所有菜单列表
     * @return 每个根节点下，所有子菜单列表
     */
    public List<ModelTreeBean> getChild(String id, List<ModelTreeBean> allModel){
        //子菜单
        List<ModelTreeBean> childList = new ArrayList<ModelTreeBean>();
        for (ModelTreeBean nav : allModel) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if (nav.getParentId().equals(id)){
                childList.add(nav);
            }
        }
        //递归
        for (ModelTreeBean nav : childList) {
            nav.setChildren(getChild(nav.getId(), allModel));
        }
//        Collections.sort(childList,order()); //排序
        //如果节点下没有子节点，返回一个空List（递归退出）
        if (childList.size() == 0 ){
            return new ArrayList<ModelTreeBean>();
        }
        return childList;
    }





    /**
     * 获取角色的权限菜单
     * @param sessionFactory
     * @return
     */
    @TaskAnnotation("getSysModelVo")
    public List<SysModelVo>  getSysModelVo(SessionFactory sessionFactory,String staffuncode){

        Map<String,Object> data = new HashMap<String,Object>();
        try {
            SysModelMapper mapper = sessionFactory.getMapper(SysModelMapper.class);
            //查询所有菜单
            List<SysModelVo> vo = mapper.getMoldelByStaffuncodeVo(staffuncode);
            //获取所有按钮
            List<Func> funclist = mapper.getMoldelByStaffuncodefunVo(staffuncode);
            Map<String, List<Func>> funcmap = funclist.stream().collect(Collectors.groupingBy(Func::getMenuId));
            vo.stream().forEach(v->{
                List<Func> func = new ArrayList<>();
                if(funcmap.get(v.getId())==null){
                    v.setFuncList(func);
                }else{
                    v.setFuncList(funcmap.get(v.getId()));
                }
            });
            return vo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
