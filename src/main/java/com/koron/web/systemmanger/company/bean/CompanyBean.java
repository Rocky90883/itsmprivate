package com.koron.web.systemmanger.company.bean;

import com.koron.common.bean.BaseBean;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * tblcompany
 * @author 
 */
public class CompanyBean extends BaseBean implements Serializable{
    private String id;

    @ApiModelProperty(value = "编号")
    private String comNumber;

    @ApiModelProperty(value = "供应商名称")
    private String comFullName;

    @ApiModelProperty(value = "供应商地址")
    private String comAddress;

    @ApiModelProperty(value = "座机号")
    private String comTel;

    @ApiModelProperty(value = "供应商联系人")
    private String comPerson;

    @ApiModelProperty(value = "联系人手机")
    private String personMobile;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "备注")
    private String remark;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComNumber() {
        return comNumber;
    }

    public void setComNumber(String comNumber) {
        this.comNumber = comNumber;
    }

    public String getComFullName() {
        return comFullName;
    }

    public void setComFullName(String comFullName) {
        this.comFullName = comFullName;
    }

    public String getComAddress() {
        return comAddress;
    }

    public void setComAddress(String comAddress) {
        this.comAddress = comAddress;
    }

    public String getComTel() {
        return comTel;
    }

    public void setComTel(String comTel) {
        this.comTel = comTel;
    }

    public String getComPerson() {
        return comPerson;
    }

    public void setComPerson(String comPerson) {
        this.comPerson = comPerson;
    }

    public String getPersonMobile() {
        return personMobile;
    }

    public void setPersonMobile(String personMobile) {
        this.personMobile = personMobile;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}