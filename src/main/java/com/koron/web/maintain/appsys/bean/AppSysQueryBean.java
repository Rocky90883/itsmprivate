package com.koron.web.maintain.appsys.bean;

import com.koron.common.bean.BaseQueryBean;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class AppSysQueryBean extends BaseQueryBean implements Serializable {
    private String id;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始申请日期")
    private String beginDate;

    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束申请日期")
    private String endDate;

    /**
     * 系统名称 字符串输入
     */
    @ApiModelProperty(value = "系统名称")
    private String sysName;

    /**
     * 建设日期
     */
    @ApiModelProperty(value = "建设日期")
    private String buildDate;

    /**
     * 预计完工日期
     */
    @ApiModelProperty(value = "预计完工日期")
    private String planDate;

    /**
     * 主办人 字符串输入
     */
    @ApiModelProperty(value = "主办人 字符串输入")
    private String empName;


    private static final long serialVersionUID = 1L;

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(String buildDate) {
        this.buildDate = buildDate;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}