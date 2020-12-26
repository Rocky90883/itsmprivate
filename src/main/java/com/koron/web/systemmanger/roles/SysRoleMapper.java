package com.koron.web.systemmanger.roles;

import com.koron.web.systemmanger.roles.bean.*;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysRoleMapper {

    List<SysRoleBean> queryList(SysRoleQueryBean queryBean);

    int deleteByPrimaryKey(String id);

    int insertSelective(SysRoleBean record);

    SysRoleBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRoleBean record);

    @Select( " select count(0) from sys_role where role_name=#{roleName} " )
    int countByRoleName(String roleName);

    //根据人员code查看角色
    List<SysRoleBean> getRoleBystaffuncode(String staffunCode);

    //分配人员給角色
    int insertStaffuntoRole(@Param("roleStaffunEntity") List<RoleStaffunEntity> roleStaffunEntity);

    //分配模菜单給角色
    int insertModeltoRole(@Param("roleModelEntity") List<RoleModelEntity> roleModelEntity);

    //根据角色查看人员列表
    List<RoleStaffunVo> staffunListByroleId(SysRoleQueryBean queryBean);

    //根据角色查看模块菜单列表
    List<RoleModelVo> modelListByroleId(SysRoleQueryBean queryBean);

    @Select( "select * from role_staffun where staffun_code=#{staffunCode} " )
    List<RoleStaffunEntity> roleStaffunByStaffunCode(String staffunCode);

    //移除人员角色
    int removePersonRole(RoleStaffunDto dto);

    //移除人员角色
    int removeAllPersonRole(RoleStaffunDto dto);

    //移除角色所有权限
    @Delete( " delete from role_model where role_id = #{roleId}" )
    int removeAllModelByRole(RoleModelDto dto);

}