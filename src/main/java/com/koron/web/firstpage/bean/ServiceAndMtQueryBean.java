package com.koron.web.firstpage.bean;

import io.swagger.annotations.ApiModelProperty;

public class ServiceAndMtQueryBean {

    @ApiModelProperty(value = "服务类型")
    private String procInstType;

    @ApiModelProperty(value = "审核状态")
    private Integer status;

    public String getProcInstType() {
        return procInstType;
    }

    public void setProcInstType(String procInstType) {
        this.procInstType = procInstType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
