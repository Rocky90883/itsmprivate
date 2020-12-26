package com.koron.web.systemmanger.roles.bean;

import io.swagger.annotations.ApiModelProperty;

public class RoleModelBean {

    private String id;


    @ApiModelProperty(value = "角色id")
    private String roleId;


    @ApiModelProperty(value = "模块id")
    private String modelId;


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

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }
}
