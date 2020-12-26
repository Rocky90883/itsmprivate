package com.koron.web.systemmanger.dept;

import com.koron.common.web.bean.DepartmentBean;
import com.koron.common.web.mapper.DepartmentMapper;

import com.koron.util.Constant;
import com.koron.util.Tools;
import com.koron.web.systemmanger.UnifiedUserImport;
import com.koron.web.systemmanger.WanhuDataImport;
import com.koron.web.systemmanger.WanhuDataImport.DataBean;
import com.koron.web.systemmanger.model.SysModelMapper;
import com.koron.web.systemmanger.model.bean.ModelTreeBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.swan.bean.MessageBean;

import java.util.*;

@Service
public class DeptService {

    private static Logger log = Logger.getLogger(DeptService.class);

    @TaskAnnotation("getDepartmentInfoById")
    public DepartmentBean getDepartmentInfoById(SessionFactory sessionFactory, String departmentid) {
        DepartmentBean departmentBean;
        try {
            DepartmentMapper dictionaryMapper = sessionFactory.getMapper(DepartmentMapper.class);
            departmentBean = dictionaryMapper.getDepartmentInfoById(departmentid);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return departmentBean;
    }


    /**
     * @desc 同步组织架构部门表
     * @param sessionFactory
     * @return
     */
    @TaskAnnotation("syncDep")
    public MessageBean<?> syncDep(SessionFactory sessionFactory){
        DepartMapper mapper = sessionFactory.getMapper(DepartMapper.class);
        //1.统一用户平台
        List<DepartunBean> allList = new ArrayList<>();
        allList = UnifiedUserImport.importDepartment();

        //2.查询本地所有数据
        List<DepartunBean> deps = mapper.getDepartmentAll();
        //3.匹配数据
        List<DepartunBean> nlist = new ArrayList<DepartunBean>(); //新增数据
        List<DepartunBean> ulist = new ArrayList<DepartunBean>(); //修改数据
        for (DepartunBean item : allList) {
            //2:部门信息
            if (!item.getCode().equals("yhszy") && StringUtils.isNotBlank(item.getCode())) {

                //构建新的部门数据
                DepartunBean dep = new DepartunBean();

                dep.setName(item.getName());
                dep.setShortname(item.getName());
                dep.setCode(item.getCode());
                dep.setParentCode(item.getParentCode());
                dep.setTreeType(item.getTreeType());
                dep.setMask(item.getMask());
                dep.setParentmask(item.getParentmask());
                dep.setLink(item.getLink());
                dep.setOrgKind(item.getOrgKind());
                dep.setBusinessKind(item.getBusinessKind());


                boolean flag = false; //配置库是否存在部门
                for(DepartunBean  departmentBean : deps){
                    if(item.getCode().equals(departmentBean.getCode())){ //配置库已经存在，则修改
                        ulist.add(dep);
                        flag = true;
                        break;
                    }
                }
                if(!flag){ //配置库不存在，则新增
                    nlist.add(dep);
                }
            }
        }
        //4.实例化到数据库
        int num = 300;//每次修改的数据量
        if(!nlist.isEmpty()){
            for(int i = 0 ;i < nlist.size(); i = i + num){
                List<DepartunBean> add = new ArrayList<>();
                if( i + num <= nlist.size()){
                    add = nlist.subList(i, i+num);
                }else {
                    add = nlist.subList(i, nlist.size());
                }
                if(!add.isEmpty()){
                    mapper.addDepartments(add);//批量添加
                }

            }
        }

        if(!ulist.isEmpty()){
            for(int i = 0 ;i < ulist.size(); i = i + num){
                List<DepartunBean> upd = new ArrayList<>();
                if( i + num < ulist.size()){
                    upd = ulist.subList(i, i+num);
                }else {
                    upd = ulist.subList(i, ulist.size());
                }
                if(!upd.isEmpty()){
                    mapper.updateDepartments(upd);//批量修改
                }

            }
        }

        //获取同步完成的部门
        List<DepartunBean> latestdepAll = mapper.getDepartmentAll();
        latestdepAll.stream().forEach(dep ->{
            //查看部门是否存在下级
            Optional<DepartunBean> optexist = latestdepAll.stream()
                    .filter(a -> a.getParentCode().equals(dep.getCode()))
                    .findFirst();
            if (optexist.isPresent()) {
                dep.setIsCatalog(1);
            }
//            int qty = mapper.countbyParentCode(dep.getCode());
//            if (qty > 0) {
//                dep.setIsCatalog(1);
//            }
        });

        if(!latestdepAll.isEmpty()){
            for(int i = 0 ;i < latestdepAll.size(); i = i + num){
                List<DepartunBean> upd = new ArrayList<>();
                if( i + num < latestdepAll.size()){
                    upd = latestdepAll.subList(i, i+num);
                }else {
                    upd = latestdepAll.subList(i, latestdepAll.size());
                }
                if(!upd.isEmpty()){
                    mapper.updateDepartments(upd);//批量修改
                }

            }
        }

        MessageBean<String> msg = MessageBean.create(0, "同步部门完成", String.class);
        msg.setDescription("同步部门完成,新增"+nlist.size()+"条,修改"+ulist.size()+"条");
        return msg;
    }


    /**
     * 根据flag和code查询部门树，一级部门集合不查询code字段
     * @param factory
     * @param code
     * @return
     */
    @TaskAnnotation("newGetDepartmentTree")
    public MessageBean<?> newGetDepartmentTree(SessionFactory factory,String code) {
        DepartMapper mapper = factory.getMapper(DepartMapper.class);
        String msg = "";
        //如果是一级，直接查询出来
        List<DepartunBean> list = mapper.newGetDepartmentTree(code);
//        list.stream().forEach(bean ->{
//            int qty = mapper.countbyParentCode(bean.getCode());
//            if (qty > 0) {
//                bean.setIsCatalog(1);
//            }
//        });

        MessageBean<List> bean = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, msg, List.class);
        bean.setData(list);
        //如果没有flag字段，需要根据supercode获取下级的部门集
        return bean;
    }

    /**
     * 根据条件进行全数据查询
     * @param factory
     * @param condition
     * @return
     */
    @TaskAnnotation("newDepartmentQuery")
    public List<DepartunBean> newDepartmentQuery(SessionFactory factory, DepartunQueyBean condition) {
        List<DepartunBean> departunQueyBeans = factory.getMapper(DepartMapper.class).listAllcondition(condition);
        return departunQueyBeans;
    }

    /**
     * 部门树
     * @param sessionFactory
     * @return
     */
    @TaskAnnotation("findDepartTree")
    public List<DepartunTreeBean>  findModelTree(SessionFactory sessionFactory){

        Map<String,Object> data = new HashMap<String,Object>();
        try {
            DepartMapper mapper = sessionFactory.getMapper(DepartMapper.class);
            //查询所有菜单
            List<DepartunTreeBean> allDepart = mapper.getTreeDepartmentAll();
            //根节点
            List<DepartunTreeBean> rootModel = new ArrayList<DepartunTreeBean>();
            for (DepartunTreeBean nav : allDepart) {
                if (nav.getParentCode().equals("yhszy")){ //父节点是root的，为一级菜单
                    rootModel.add(nav);
                }
            }
            /* 根据Menu类的order排序 */
//            Collections.sort(rootModel, order());
            //为根菜单设置子菜单，getClild是递归调用的
            for (DepartunTreeBean nav : rootModel) {
                /* 获取根节点下的所有子节点 使用getChild方法*/
                List<DepartunTreeBean> childList = getChild(nav.getCode(), allDepart);
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
     * @param allDepart 所有部门列表
     * @return 每个根节点下，所有子菜单列表
     */
    public List<DepartunTreeBean> getChild(String id, List<DepartunTreeBean> allDepart){
        //子菜单
        List<DepartunTreeBean> childList = new ArrayList<DepartunTreeBean>();
        for (DepartunTreeBean nav : allDepart) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if (nav.getParentCode().equals(id)){
                childList.add(nav);
            }
        }
        //递归
        for (DepartunTreeBean nav : childList) {
            nav.setChildren(getChild(nav.getCode(), allDepart));
        }
//        Collections.sort(childList,order()); //排序
        //如果节点下没有子节点，返回一个空List（递归退出）
        if (childList.size() == 0 ){
            return new ArrayList<DepartunTreeBean>();
        }
        return childList;
    }
}
