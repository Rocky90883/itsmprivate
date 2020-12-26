package com.koron.web.workorder.accountapp.bean;

import com.koron.common.bean.BaseQueryBean;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 用户账号/权限变更
 * @author 
 */
public class AccountappQueryBean extends BaseQueryBean implements Serializable {

    private String id;

    private String orgid;

    /**
     * 服务单号
     */
    @ApiModelProperty(value = "服务单号")
    private String orderNo;

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
     * 申请类型 （1账户新增、2账户注销、3权限变更）
     */
    @ApiModelProperty(value = "申请类型 （1账户新增、2账户注销、3权限变更）")
    private String apptype;

    /**
     * 业务系统名称 跟appsys_id 保持同步
     */
    @ApiModelProperty(value = "业务系统名称")
    private String appsysName;

    /**
     * (关联应用系统清单)
     */
    @ApiModelProperty(value = "业务系统id")
    private String appsysId;

    /**
     * 状态:【/1:启用/2:草稿/3:已停用/4:已驳回/5:废弃/6:转办中】
     */
    @ApiModelProperty(value = "流程状态")
    private Integer status;

    /**
     * 归属部门名称(关联显示)
     */
    @ApiModelProperty(value = "归属部门名称(关联显示)")
    private String depatName;

    /**
     * 所有者名称(关联显示)
     */
    @ApiModelProperty(value = "申请人(关联显示)")
    private String staffunName;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

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

    public String getApptype() {
        return apptype;
    }

    public void setApptype(String apptype) {
        this.apptype = apptype;
    }

    public String getAppsysName() {
        return appsysName;
    }

    public void setAppsysName(String appsysName) {
        this.appsysName = appsysName;
    }

    public String getAppsysId() {
        return appsysId;
    }

    public void setAppsysId(String appsysId) {
        this.appsysId = appsysId;
    }

    public String getDepatName() {
        return depatName;
    }

    public void setDepatName(String depatName) {
        this.depatName = depatName;
    }

    public String getStaffunName() {
        return staffunName;
    }

    public void setStaffunName(String staffunName) {
        this.staffunName = staffunName;
    }
}