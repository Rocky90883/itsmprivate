package com.koron.web.systemmanger.roles.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class RoleModelDto {

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "模块id arr")
    private List<String> modelIds;


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getModelIds() {
        return modelIds;
    }

    public void setModelIds(List<String> modelIds) {
        this.modelIds = modelIds;
    }
}
