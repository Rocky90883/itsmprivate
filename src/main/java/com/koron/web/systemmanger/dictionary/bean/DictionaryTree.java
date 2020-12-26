package com.koron.web.systemmanger.dictionary.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class DictionaryTree {

    @ApiModelProperty(value = "组id")
    private String id;

    @ApiModelProperty(value = "组code")
    private String regionCode;

    @ApiModelProperty(value = "组名字")
    private String regionName;

    @ApiModelProperty(value = "组织id")
    private String orgId;

    @ApiModelProperty(value = "参数")
    private List<Parameter> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public List<Parameter> getChildren() {
        return children;
    }

    public void setChildren(List<Parameter> children) {
        this.children = children;
    }
}
