package com.koron.web.annex.bean;

import java.io.Serializable;

import com.koron.common.bean.BaseBean;
import io.swagger.annotations.ApiModelProperty;


public class AnnexBean extends BaseBean implements Serializable {

    private String id;


    @ApiModelProperty(value = "单据id")
    private String sourceId;


    @ApiModelProperty(value = "单据标识")
    private String billType;


    @ApiModelProperty(value = "后缀")
    private String suffix;


    @ApiModelProperty(value = "附件名称")
    private String annexName;


    @ApiModelProperty(value = "上传时间")
    private String createTime;


    @ApiModelProperty(value = "上传人")
    private String createName;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getAnnexName() {
        return annexName;
    }

    public void setAnnexName(String annexName) {
        this.annexName = annexName;
    }

    @Override
    public String getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getCreateName() {
        return createName;
    }

    @Override
    public void setCreateName(String createName) {
        this.createName = createName;
    }
}