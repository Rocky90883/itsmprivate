package com.koron.web.systemmanger.dept;

import com.koron.common.web.bean.DepartmentBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DepartMapper {

    @Select("select * from tbldepartun")
    List<DepartunBean> getDepartmentAll();

    @Select("select * from tbldepartun")
    List<DepartunTreeBean> getTreeDepartmentAll();

    /**
     * 批量添加部门数据
     * @param list
     */
    void addDepartments(@Param("list")List<DepartunBean> list);


    /**
     * 批量更新部门数据
     * @param list
     */
    void updateDepartments(@Param("list")List<DepartunBean> list);


    /**
     * 根据父code字段查询对应的部门集
     * @param parentCode
     * @return
     */
    List<DepartunBean> newGetDepartmentTree( @Param("parentCode") String parentCode);

    /**
     *根据条件全数据查询
     * @param condition
     * @return
     */
    List<DepartunBean> listAllcondition(DepartunQueyBean condition);

    @Select(" select count(0) from tbldepartun where parent_code =#{parentCode} ")
    int countbyParentCode(String parentCode);

}
