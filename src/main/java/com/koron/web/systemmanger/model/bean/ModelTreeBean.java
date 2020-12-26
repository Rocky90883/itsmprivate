package com.koron.web.systemmanger.model.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ModelTreeBean {

    private String id;

    /**
     * 父id
     */
    @ApiModelProperty("子菜单")
    private String parentId;

    /**
     * code
     */
    @ApiModelProperty("code")
    private String code;

    /**
     * 父code
     */
    @ApiModelProperty("父code")
    private String parentCode;

    /**
     * 模块菜单
     */
    @ApiModelProperty("模块菜单名称")
    private String modelName;

    /**
     * 是否界面(0是界面、1是接口)
     */
    @ApiModelProperty(value = "是否界面(0是界面、1是接口)")
    private Integer isPage;

    /**
     * 状态(-1禁用、0鉴权、1不鉴权)
     */
    @ApiModelProperty("状态(-1禁用、0鉴权、1不鉴权)")
    private Integer status;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;

    /**
     * 请求地址
     */
    @ApiModelProperty("请求地址")
    private String url;

    /**
     * 子菜单
     */
    @ApiModelProperty("子菜单")
    private List<ModelTreeBean> children;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<ModelTreeBean> getChildren() {
        return children;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setChildren(List<ModelTreeBean> children) {
        this.children = children;
    }
}
