package com.koron.web.asset.assettype.bean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 下拉框
 */
public class SelectBean {

    @ApiModelProperty(value = "资产编码")
    private String classcode;

    @ApiModelProperty(value = "资产类型")
    private String typeName;

    private String id;

    @ApiModelProperty(value = "父级id")
    private String parentId;

    public String getClasscode() {
        return classcode;
    }

    public void setClasscode(String classcode) {
        this.classcode = classcode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

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
}
