package com.koron.web.systemmanger.model;

import com.koron.web.systemmanger.model.bean.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysModelMapper {

    List<SysModelBean> queryList(SysModelQueryBean queryBean);

    int deleteByPrimaryKey(String id);

    int insertSelective(SysModelBean record);

    SysModelBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysModelBean record);


    //找当前classcode最大值
    String findMaxChild(String classcode);

    //是否存在下级
    @Select("select count(0) from sys_model where parent_id=#{parentId}")
    int countByParentid(String parentid);

    //所有一级菜单
    @Select( "select * from sys_model ")
    List<ModelTreeBean> findAllModel();


    @Select( "select count(0) from sys_model where url = like concat('%',#{url},'%')" )
    public List<SysModelBean> existUrl(SysModelBean url);

    //根根据用户code获取所有权限
    public List<SysModelBean> getMoldelByStaffuncode(String staffuncode);

    //根据用户code 查菜单权限
    public List<SysModelVo> getMoldelByStaffuncodeVo(@Param("staffuncode") String staffuncode);

    //根据用户code 查菜单权限  isJurisdiction 0无权限、1有权限
    public List<Func> getMoldelByStaffuncodefunVo(@Param("staffuncode") String staffuncode);

    @Select(" select * from sys_model where is_page=0 order by code asc ")
    public List<SysModelVo> getPage();

//    @Select( "select * from sys_model where is_page=0 order by code asc ")
//    public List<SysModelVo> getFunById
}