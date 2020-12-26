package com.koron.common.bean;


import io.swagger.annotations.ApiModelProperty;

public class BaseBean  {

    @ApiModelProperty(hidden = true)
    private String createAccount;

    @ApiModelProperty(hidden = true)
    private String createTime;

    @ApiModelProperty(hidden = true)
    private String createName;

    @ApiModelProperty(hidden = true)
    private String updateAccount;

    @ApiModelProperty(hidden = true)
    private String updateTime;

    @ApiModelProperty(hidden = true)
    private String updateName;

    @ApiModelProperty(value = "备注")
    private String remark;

    public BaseBean() {

    }


    public String getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(String createAccount) {
        this.createAccount = createAccount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateAccount() {
        return updateAccount;
    }

    public void setUpdateAccount(String updateAccount) {
        this.updateAccount = updateAccount;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}

