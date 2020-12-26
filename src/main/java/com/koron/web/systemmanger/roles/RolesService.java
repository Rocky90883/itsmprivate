package com.koron.web.systemmanger.roles;

import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.systemmanger.model.SysModelService;
import com.koron.web.systemmanger.model.bean.ModelTreeBean;
import com.koron.web.systemmanger.roles.bean.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.swan.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolesService {

    private static Logger log = Logger.getLogger(RolesService.class);

    /**
     * 角色列表
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("queryList")
    public List<SysRoleBean> queryList(SessionFactory factory, SysRoleQueryBean bean) {
        List<SysRoleBean> assetslist;
        try {
            SysRoleMapper mapper = factory.getMapper(SysRoleMapper.class);

            assetslist = mapper.queryList(bean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return assetslist;
    }

    /**
     * 新增角色
     * @param sessionFactory
     * @param bean
     * @return
     */
    @TaskAnnotation("addSysRole")
    public MessageBean addSysRole(SessionFactory sessionFactory, SysRoleBean bean) {

        try {
            bean.setId(CommonUtil.get32Key());
            SysRoleMapper mapper = sessionFactory.getMapper(SysRoleMapper.class);
            
            if (mapper.countByRoleName(bean.getRoleName())>0) {
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION,"角色名已存在",void.class);
            }
            bean.setIsSys(0);//新增的都是常规角色
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
     * 删除角色
     * @param sessionFactory
     * @param id
     * @return
     */
    @TaskAnnotation("deleteSysRole")
    public MessageBean deleteSysRole(SessionFactory sessionFactory, String id) {

        try {
            SysRoleMapper mapper = sessionFactory.getMapper(SysRoleMapper.class);
            SysRoleBean sysRoleBean = mapper.selectByPrimaryKey(id);
            if (sysRoleBean.getIsSys()==1) {
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "该角色为系统角色不允许删除", void.class);
            }

            mapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 更新角色
     * @param sessionFactory
     * @param bean
     * @return
     */
    @TaskAnnotation("updateSysRole")
    public MessageBean updateSysRole(SessionFactory sessionFactory, SysRoleBean bean) {

        try {
            SysRoleMapper mapper = sessionFactory.getMapper(SysRoleMapper.class);
            
            mapper.updateByPrimaryKeySelective(bean);   //写库
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 分配人员給角色
     * @param factory
     * @param dto
     * @return
     */
    @TaskAnnotation("portionStaffuntoRole")
    public MessageBean portionStaffuntoRole(SessionFactory factory, RoleStaffunDto dto) {

        try {
            SysRoleMapper mapper = factory.getMapper(SysRoleMapper.class);
            SysRoleBean sysRoleBean = mapper.selectByPrimaryKey(dto.getRoleId());
            if(sysRoleBean==null){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "角色不存在", void.class);
            }

            SysRoleQueryBean queryBean = new SysRoleQueryBean();
            queryBean.setId(dto.getRoleId());
            List<RoleStaffunVo> roleStaffunVos = mapper.staffunListByroleId(queryBean);
            //角色原有人员
            List<String> oldstaffun = roleStaffunVos.stream().map(RoleStaffunVo::getStaffunCode).collect(Collectors.toList());


            if(CollectionUtils.isNotEmpty(dto.getStaffunCodes())){
                List<RoleStaffunEntity> list = new ArrayList<>();
                for(String staffunid : dto.getStaffunCodes()){
                    //如果不存在则add
                    if(!oldstaffun.contains(staffunid)){
                        RoleStaffunEntity rs = new RoleStaffunEntity();
                        rs.setId(CommonUtil.get32Key());
                        rs.setRoleId(dto.getRoleId());
                        rs.setStaffunCode(staffunid);
                        list.add(rs);
                    }
                }
                if(list.size()>0){
                    mapper.insertStaffuntoRole(list);   //写库
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 给角色分配模块菜单
     * @param sessionFactory
     * @param dto
     * @return
     */
    @TaskAnnotation("portionModeltoRole")
    public MessageBean portionModeltoRole(SessionFactory sessionFactory, RoleModelDto dto) {

        try {
            SysRoleMapper mapper = sessionFactory.getMapper(SysRoleMapper.class);

//            SysRoleQueryBean queryBean = new SysRoleQueryBean();
//            queryBean.setId(dto.getRoleId());
//            List<RoleModelVo> roleModelVos = mapper.modelListByroleId(queryBean);
//            //角色原有模块菜单
//            List<String> oldModelId = roleModelVos.stream().map(RoleModelVo::getRoleId).collect(Collectors.toList());

            //先移除角色所有菜单权限
            mapper.removeAllModelByRole(dto);

            if(CollectionUtils.isNotEmpty(dto.getModelIds())){
                List<RoleModelEntity> list = new ArrayList<>();
                for(String modelId : dto.getModelIds()){
                        RoleModelEntity rs = new RoleModelEntity();
                        rs.setId(CommonUtil.get32Key());
                        rs.setRoleId(dto.getRoleId());
                        rs.setModelId(modelId);
                        list.add(rs);
                }
                if(list.size()>0){
                    mapper.insertModeltoRole(list);   //写库
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }


    /**
     * 根据角色查人员
     * @param factory
     * @param queryBean
     * @return
     */
    @TaskAnnotation("staffunListByroleId")
    public List<RoleStaffunVo> staffunListByroleId(SessionFactory factory, SysRoleQueryBean queryBean) {
        List<RoleStaffunVo> volist;
        try {
            SysRoleMapper mapper = factory.getMapper(SysRoleMapper.class);

            volist = mapper.staffunListByroleId(queryBean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return volist;
    }

    /**
     * 移除人员角色
     * @param sessionFactory
     * @param dto
     * @return
     */
    @TaskAnnotation("removePersonRole")
    public MessageBean removePersonRole(SessionFactory sessionFactory, RoleStaffunDto dto) {

        try {
            SysRoleMapper mapper = sessionFactory.getMapper(SysRoleMapper.class);
            mapper.removePersonRole(dto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }


// =======================================================================================================================

    /**
     * 根据角色查人员
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("queryModelByroleId")
    public FpModelVo queryModelByroleId(SessionFactory factory,String id) {
        FpModelVo vo = new FpModelVo();
        try {
            SysRoleMapper mapper = factory.getMapper(SysRoleMapper.class);
            SysRoleQueryBean querybean = new SysRoleQueryBean();
            querybean.setId(id);
            List<RoleModelVo> role = mapper.modelListByroleId(querybean);
            SysModelService modelService = new SysModelService();
            List<ModelTreeBean> modelTree = modelService.findModelTree(factory);

            List<String> roles = role.stream().map(RoleModelVo::getModelId).collect(Collectors.toList());

            vo.setModelTree(modelTree);
            vo.setModelId(roles);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return vo;
    }
}
