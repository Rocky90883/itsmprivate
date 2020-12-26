package com.koron.web.systemmanger.roles.bean;

import io.swagger.annotations.ApiModelProperty;

public class RoleStaffunEntity {

    /**
     * id
     */
    private String id;


    @ApiModelProperty(value = "角色id")
    private String roleId;


    @ApiModelProperty(value = "用户code")
    private String staffunCode;


    public RoleStaffunEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getStaffunCode() {
        return staffunCode;
    }

    public void setStaffunCode(String staffunCode) {
        this.staffunCode = staffunCode;
    }

}
