package com.koron.web.systemmanger.user;

import com.koron.common.bean.BaseOracleQueryBean;

import java.math.BigDecimal;

public class UserQueryBean extends BaseOracleQueryBean{

    /**
     * 登录id
     */
    private Integer loginId;

    /**
     * 登录名称
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 部门id
     */
    private String deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 邮件
     */
    private String email;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 地址
     */
    private String addr;

    /**
     * 电话
     */
    private String tel;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 建立时间
     */
    private String createTime;

    /**
     * 备注
     */
    private String userDesc;

    /**
     * 是否停用
     */
    private String dFlag;

    /**
     * 排序号
     */
    private BigDecimal sort;

    /**
     * 领导类型
     */
    private String type;

    /**
     * 职位
     */
    private String job;

    /**
     * 公司（新加）
     */
    private Integer regId;

    /**
     * 公司领导(订餐系统）
     */
    private Integer leadFlag;

    @Override
    public String toString() {
        return "UserQueryBean{" +
                "loginId=" + loginId +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", email='" + email + '\'' +
                ", birthday='" + birthday + '\'' +
                ", addr='" + addr + '\'' +
                ", tel='" + tel + '\'' +
                ", mobile='" + mobile + '\'' +
                ", createTime='" + createTime + '\'' +
                ", userDesc='" + userDesc + '\'' +
                ", dFlag='" + dFlag + '\'' +
                ", sort=" + sort +
                ", type='" + type + '\'' +
                ", job='" + job + '\'' +
                ", regId=" + regId +
                ", leadFlag=" + leadFlag +
                '}';
    }

    public UserQueryBean() {
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public String getDFlag() {
        return dFlag;
    }

    public void setDFlag(String dFlag) {
        this.dFlag = dFlag;
    }

    public BigDecimal getSort() {
        return sort;
    }

    public void setSort(BigDecimal sort) {
        this.sort = sort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getRegId() {
        return regId;
    }

    public void setRegId(Integer regId) {
        this.regId = regId;
    }

    public Integer getLeadFlag() {
        return leadFlag;
    }

    public void setLeadFlag(Integer leadFlag) {
        this.leadFlag = leadFlag;
    }


}
