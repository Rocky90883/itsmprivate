package com.koron.web.workflowUtils.mapper;

import java.util.List;

import com.koron.web.workflowUtils.bean.DepartmentTreeBean;
import com.koron.web.workflowUtils.bean.StaffInfoBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.koron.ebs.mybatis.EnvSource;

import com.koron.common.bean.StaffBean;
//import com.koron.laboratory.functions.workflowUtils.bean.DepartmentTreeBean;
//import com.koron.laboratory.functions.workflowUtils.bean.StaffInfoBean;




@EnvSource("_common")
public interface DepartmentMapper {
	@Select("select tbltree.seq,tbltree.parentmask,tbldepartment.* from tbltree \n" + 
			"inner join tbldepartment on tbltree.foreignkey=tbldepartment.id \n" + 
			"inner join (\n" + 
			"	select tbltree.* from tbltree \n" + 
			"where type = 1 and seq =#{id}) a on (tbltree.`seq` & ~((1 << (62 - a.`parentMask`-a.`mask`))-1)) = a.`seq`")
	public List<DepartmentTreeBean> getDescendantByParentId(@Param("id") Long id);
	@Select("select tbltree.seq,tbltree.parentmask,tbldepartment.* from tbltree \n" + 
			"inner join tbldepartment on tbltree.foreignkey=tbldepartment.id \n" + 
			"where (#{seq} & ~((1 << (62 - parentmask-mask))-1)) = seq\n" + 
			"order by `seq`\n")
	public List<DepartmentTreeBean> getPathById(@Param("seq") Long seq);
	@Select("select id,name,code from tblstaff where departmentid = (select id from tbldepartment where `code` = #{department})")
	public List<StaffBean> getStaffOfDep(@Param("department")String code);
	
	@Select("select * from tblstaff where loginid = #{loginid}")
	public StaffBean getStaffByLoginId(@Param("loginid") String loginId);
	@Select("select a.name,a.code as loginid,b.name as department from tblstaff a left join tbldepartment b on a.departmentid = b.id where b.code = #{code}")
	public List<StaffInfoBean> getStaffOfDemartment(@Param("code") String code);
	
}