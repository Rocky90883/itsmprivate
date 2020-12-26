package com.koron.web.systemmanger.model.bean;

import com.koron.common.bean.BaseQueryBean;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * sys_model
 * @author 
 */
public class SysModelQueryBean extends BaseQueryBean {
    private String id;


    @ApiModelProperty(value = "父id")
    private String parentId;


    @ApiModelProperty(value = "code")
    private String code;


    @ApiModelProperty(value = "父code")
    private String parentCode;


    @ApiModelProperty(value = "模块名称")
    private String modelName;


    @ApiModelProperty(value = "编码 手工录入")
    private String dtoCode;


    @ApiModelProperty(value = "是否界面(0是界面、1是接口)")
    private Integer isPage;


    @ApiModelProperty(value = "状态(-1禁用、0鉴权、1不鉴权)")
    private Integer status;


    @ApiModelProperty(value = "排序")
    private Integer sort;


    @ApiModelProperty(value = "描述")
    private String description;


    @ApiModelProperty(value = "请求地址")
    private String url;


    private static final long serialVersionUID = 1L;

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

    public String getDtoCode() {
        return dtoCode;
    }

    public void setDtoCode(String dtoCode) {
        this.dtoCode = dtoCode;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}