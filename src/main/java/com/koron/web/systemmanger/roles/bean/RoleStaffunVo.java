package com.koron.web.systemmanger.roles.bean;

import io.swagger.annotations.ApiModelProperty;

public class RoleStaffunVo {


    @ApiModelProperty(value = "角色id")
    private String roleId;


    @ApiModelProperty(value = "角色名称")
    private String roleName;


    @ApiModelProperty(value = "员工名称")
    private String name;


    @ApiModelProperty(value = "员工code")
    private String staffunCode;


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStaffunCode() {
        return staffunCode;
    }

    public void setStaffunCode(String staffunCode) {
        this.staffunCode = staffunCode;
    }
}
