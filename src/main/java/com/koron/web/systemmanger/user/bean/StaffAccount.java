package com.koron.web.systemmanger.user.bean;

import com.koron.web.systemmanger.roles.bean.FpModelVo;
import com.koron.web.systemmanger.roles.bean.SysRoleBean;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


public class StaffAccount {

    @ApiModelProperty(value = "用户信息")
    private StaffunBean staffunBean;


    @ApiModelProperty(value = "角色")
    private List<SysRoleBean> sysroles;


    @ApiModelProperty(value = "阅览范围 all全部、my自己")
    private String broper;


    @ApiModelProperty(value = "接口权限")
    private FpModelVo interfaces;


    private StaffAccount(){}

    /**
     *
     * @param staffunBean 人员信息
     * @param sysroles 角色
     * @param broper    阅览范围
     * @param interfaces    接口权限
     */
    public StaffAccount(StaffunBean staffunBean, List<SysRoleBean> sysroles, String broper, FpModelVo interfaces) {
        this.staffunBean = staffunBean;
        this.sysroles = sysroles;
        this.broper = broper;
        this.interfaces = interfaces;
    }

    public StaffunBean getStaffunBean() {
        return staffunBean;
    }

    public void setStaffunBean(StaffunBean staffunBean) {
        this.staffunBean = staffunBean;
    }

    public String getBroper() {
        return broper;
    }

    public void setBroper(String broper) {
        this.broper = broper;
    }

    public FpModelVo getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(FpModelVo interfaces) {
        this.interfaces = interfaces;
    }

    public List<SysRoleBean> getSysroles() {
        return sysroles;
    }

    public void setSysroles(List<SysRoleBean> sysroles) {
        this.sysroles = sysroles;
    }

}
