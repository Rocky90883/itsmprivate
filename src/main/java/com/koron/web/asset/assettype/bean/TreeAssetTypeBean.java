package com.koron.web.asset.assettype.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

public class TreeAssetTypeBean {

    private String id; // 树id

    @ApiModelProperty(value = "资产编码")
    private String classcode; //

    @ApiModelProperty(value = "资产名称")
    private String name; // 树名称

    @ApiModelProperty(value = "父id")
    private String parent; // 父级id

    @ApiModelProperty(value = "是否含有子级")
    private Integer iscatalog; // 叶子节点

    @ApiModelProperty(value = "是否含有子级。 1有、0没有")
    private boolean isParent;

    private String group;


    private String[] districtArr;// 前端操作变量

    @ApiModelProperty(value = "子级数组")
    private ArrayList<TreeAssetTypeBean> children = new ArrayList<>(); // 下级目录

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClasscode() {
        return classcode;
    }

    public void setClasscode(String classcode) {
        this.classcode = classcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Integer getIscatalog() {
        return iscatalog;
    }

    public void setIscatalog(Integer iscatalog) {
        this.iscatalog = iscatalog;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setIsParent(boolean parent) {
        isParent = parent;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String[] getDistrictArr() {
        return districtArr;
    }

    public void setDistrictArr(String[] districtArr) {
        this.districtArr = districtArr;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public ArrayList<TreeAssetTypeBean> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<TreeAssetTypeBean> children) {
        this.children = children;
    }
}
