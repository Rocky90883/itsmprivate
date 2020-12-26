package com.koron.web.systemmanger.roles.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class RoleStaffunDto {


    @ApiModelProperty(value = "角色id")
    private String roleId;


    @ApiModelProperty(value = "人员code arr")
    private List<String> staffunCodes;


    @ApiModelProperty(value = "人员code")
    private String staffunCode;


    @ApiModelProperty(value = "角色id arr")
    private List<String> roleIds;


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getStaffunCodes() {
        return staffunCodes;
    }

    public void setStaffunCodes(List<String> staffunCodes) {
        this.staffunCodes = staffunCodes;
    }

    public String getStaffunCode() {
        return staffunCode;
    }

    public void setStaffunCode(String staffunCode) {
        this.staffunCode = staffunCode;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }
}
