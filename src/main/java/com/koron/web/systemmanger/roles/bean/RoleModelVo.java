package com.koron.web.systemmanger.roles.bean;

import io.swagger.annotations.ApiModelProperty;

public class RoleModelVo {

    @ApiModelProperty(value = "角色id")
    private String roleId;


    @ApiModelProperty(value = "角色名称")
    private String roleName;


    @ApiModelProperty(value = "模块菜单id")
    private String modelId;


    @ApiModelProperty(value = "模块菜单名称")
    private String modelName;


    @ApiModelProperty(value = "是否界面(0是界面、1是接口)")
    private Integer isPage;


    @ApiModelProperty(value = "状态(-1禁用、0鉴权、1不鉴权)")
    private Integer status;

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

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getIsPage() {
        return isPage;
    }

    public void setIsPage(Integer isPage) {
        this.isPage = isPage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
