package com.koron.web.workflow.bean;

import io.swagger.annotations.ApiModelProperty;

public class AssignEmpDto {

    @ApiModelProperty(value = "单据id")
    private String id;

    @ApiModelProperty(value = "业务类型")
    private String procInstType;

    @ApiModelProperty(value = "责任人code")
    private String assignEmpid;

    @ApiModelProperty(hidden = true)
    private String assignName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcInstType() {
        return procInstType;
    }

    public void setProcInstType(String procInstType) {
        this.procInstType = procInstType;
    }

    public String getAssignEmpid() {
        return assignEmpid;
    }

    public void setAssignEmpid(String assignEmpid) {
        this.assignEmpid = assignEmpid;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }
}
