package com.koron.web.systemmanger.model.bean;

import io.swagger.annotations.ApiModelProperty;

public class Func {

    @ApiModelProperty(value = "编码 手工录入")
    private String dtoCode;

    @ApiModelProperty(value = "菜单名称")
    private String modelName;

    @ApiModelProperty(value = "菜单id")
    private String menuId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "是否有权限 0没有、1有")
    private String isJurisdiction;



    public String getDtoCode() {
        return dtoCode;
    }

    public void setDtoCode(String dtoCode) {
        this.dtoCode = dtoCode;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIsJurisdiction() {
        return isJurisdiction;
    }

    public void setIsJurisdiction(String isJurisdiction) {
        this.isJurisdiction = isJurisdiction;
    }
}
