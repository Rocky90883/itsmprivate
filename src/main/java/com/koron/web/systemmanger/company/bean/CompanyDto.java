package com.koron.web.systemmanger.company.bean;

import com.koron.common.bean.BaseQueryBean;

import java.io.Serializable;

/**
 * 供应商
 * @author 
 */
public class CompanyDto extends BaseQueryBean {

    private String id;

    /**
     * 编号
     */
    private String comNumber;

    /**
     * 供应商名称
     */
    private String comFullName;


    /**
     * 供应商联系人
     */
    private String comPerson;


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

    public String getComPerson() {
        return comPerson;
    }

    public void setComPerson(String comPerson) {
        this.comPerson = comPerson;
    }



}