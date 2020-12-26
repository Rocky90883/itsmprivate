package com.koron.common.web.mapper;

import com.koron.web.systemmanger.user.UserBean;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.systemmanger.user.bean.StaffunQueryBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.koron.ebs.mybatis.EnvSource;

import com.koron.common.bean.StaffBean;


import java.util.List;

@EnvSource("_common")
public interface StaffMapper {
	@Select("select * from tblstaff where loginid = #{loginid}")
	public StaffBean getStaffByLoginId(@Param("loginid") String loginId);
	@Select("select * from tblstaff where code = #{code}")
	public StaffBean getStaffByCode(@Param("code") String code);
	@Select("select tblstaff.*,tbldepartment.name as departmentname from tblstaff join tbldepartment on tblstaff.departmentId=tbldepartment.id where tblstaff.id = #{id}")
	public StaffBean getStaffById(@Param("id") String id);
	/**
	 * 执行登录动作
	 */
	@Select("select tblstaff.*,tbluser.loginname from tbluser " + 
			"left join tblstaff on tbluser.loginname=tblstaff.loginid " + 
			"where tbluser.loginname=#{name} and tbluser.password=#{password} and tblstaff.`status`=1")
	public StaffBean login(@Param("name") String name, @Param("password") String password);


    /**
     * 获取所有用户信息
     * @return
     */
	@Select("SELECT tb.id AS id,tb.name AS name,tb.code AS code,tb.departmentid AS departmentid,tb.position AS position, " +
            "tb.phone AS phone,tb.mobile AS mobile,tb.email AS email,tb.sex AS sex,tb.idcard AS idcard, " +
            "tb.status AS status,tb.loginid AS account,tb.weighting AS weighting,tb.photourl AS photourl,tb.openid AS openid, " +
            "tb.userid AS userid " +
            "FROM tblstaff tb")
    List<StaffBean> getComlibAllStaff();

//	//用户列表
//	@Select(" select tblstaff.*,tbldepartment.name as departmentname from  tblstaff left join tbldepartment on tbldepartment.id=tblstaff.departmentid where 1=1 ")
//	List<StaffBean> conditionAllStaff(StaffunQueryBean queryBean);

	@Select("select * from tblstaff")
	List<StaffBean> getAllStaff();


//
//    /**
//     * 批量修改
//     * @param list
//     */
//    public void updateStaffs(@Param("list")List<StaffunBean> list);
//
//    /**
//     * 批量添加
//     * @param list
//     */
//    public void addStaffs(@Param("list")List<StaffunBean> list);



}
