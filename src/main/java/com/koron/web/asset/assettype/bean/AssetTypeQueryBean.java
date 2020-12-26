package com.koron.web.asset.assettype.bean;

import com.koron.common.bean.BaseQueryBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * asset_type
 * @author 
 */
@Data
public class AssetTypeQueryBean extends BaseQueryBean {
    private String id;

    @ApiModelProperty(value = "组织id")
    private String orgid;

    @ApiModelProperty(value = "资产分级编码")
    private String classcode;

    @ApiModelProperty(value = "父级id")
    private String parentid;

    @ApiModelProperty(value = "父级编码")
    private String parentcode;

    @ApiModelProperty(value = "父级名称")
    private String parentName;

    @ApiModelProperty(value = "是否目录")
    private Integer iscatalog;

    @ApiModelProperty(value = "资产编码自己录入")
    private String typecode;

    @ApiModelProperty(value = "资产类型")
    private String typename;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    public AssetTypeQueryBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getClasscode() {
        return classcode;
    }

    public void setClasscode(String classcode) {
        this.classcode = classcode;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getParentcode() {
        return parentcode;
    }

    public void setParentcode(String parentcode) {
        this.parentcode = parentcode;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getIscatalog() {
        return iscatalog;
    }

    public void setIscatalog(Integer iscatalog) {
        this.iscatalog = iscatalog;
    }

    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
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
}