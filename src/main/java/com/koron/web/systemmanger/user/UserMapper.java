package com.koron.web.systemmanger.user;

import com.koron.web.systemmanger.user.bean.StaffAccount;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.systemmanger.user.bean.StaffunQueryBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.koron.ebs.mybatis.EnvSource;

import java.util.List;

@EnvSource("_default")
public interface UserMapper {

    Integer add(UserBean bean);

    @Delete("delete from SYS_ALL_USER where LOGIN_ID = #{loginId}")
    Integer delete(Integer loginId);

    Integer update(UserBean bean);

    List<UserBean> list(UserQueryBean queryBean);

    Integer count(UserQueryBean queryBean);

    /**
     * 执行登录动作
     */
    @Select("select tbluser.loginname from tbluser "
            + "where tbluser.loginname=#{name} and tbluser.password=#{password}")
    public UserBean loginPwd(@Param("name") String name, @Param("password") String password);

    @Select("select max(LOGIN_ID) FROM SYS_ALL_USER ")
    Integer maxId();

    @Select("select count(*) FROM SYS_ALL_USER where USER_NAME = #{userName,jdbcType=VARCHAR} and LOGIN_ID != #{loginId ,jdbcType=VARCHAR} ")
    Integer countByUserName(UserBean bean);


    @Select("select * from SYS_ALL_USER where login_Name = #{loginName}")
    UserBean getUserInfo(UserBean bean);

    @Select("select * from tblstaffun where code= #{code}")
    StaffunBean getStaffByLoginId(@Param("code")String code);

    @Select("select * from tblstaffun where mobile= #{mobile}")
    StaffunBean getStaffByMobile(@Param("mobile")String mobile);

    @Select("select * from tblstaffun where code= #{code}")
    StaffAccount getLoginMsgByLoginId(@Param("code")String code);

    @Select(" select * from tblstaffun where workflow_code= #{workflowCode} ")
    StaffunBean getStaffByWorkCode(@Param("workflowCode")String workflowCode);

    @Select("select * from tblstaffun")
    List<StaffunBean> getAllStaffun();

    //工作流程code不为空的所有人员
    @Select(" select * from tblstaffun where workflow_code is not null ")
    List<StaffunBean> getAllWorkFlowStaffun();


    List<StaffunBean> queryStaffun(StaffunQueryBean queryBean);

    /**
     * 批量修改
     * @param list
     */
    public void updateStaffs(@Param("list")List<StaffunBean> list);

    /**
     * 批量添加
     * @param list
     */
    public void addStaffs(@Param("list")List<StaffunBean> list);

}


