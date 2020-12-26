package com.koron.web.maintain.appsys.bean;

import com.koron.common.bean.BaseBean;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class AppSysBean extends BaseBean implements Serializable {
    private String id;

    @ApiModelProperty(hidden = true)
    private String orgid;

    /**
     * 关联台账id
     */
    @ApiModelProperty(value = "关联台账id")
    private String sourceid;

    /**
     * 回填台账编号
     */
    @ApiModelProperty(value = "台账编号")
    private String assetsNumber;

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
     * 上面时间
     */
    @ApiModelProperty(value = "预计完工日期")
    private String onlineDate;

    /**
     * 开发商
     */
    @ApiModelProperty(value = "开发商")
    private String companyId;


    @ApiModelProperty(value = "供应商名称（管理显示）")
    private String comName;

    /**
     * 开发商联系号码
     */
    @ApiModelProperty(value = "开发商联系号码")
    private String phone;

    /**
     * 主办人 字符串输入
     */
    @ApiModelProperty(value = "主办人 字符串输入")
    private String empName;

    @ApiModelProperty(hidden = true)
    private String annex;

    private static final long serialVersionUID = 1L;


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

    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    public String getAssetsNumber() {
        return assetsNumber;
    }

    public void setAssetsNumber(String assetsNumber) {
        this.assetsNumber = assetsNumber;
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

    public String getOnlineDate() {
        return onlineDate;
    }

    public void setOnlineDate(String onlineDate) {
        this.onlineDate = onlineDate;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getAnnex() {
        return annex;
    }

    public void setAnnex(String annex) {
        this.annex = annex;
    }
}