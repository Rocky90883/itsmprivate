package com.koron.web.workorder.demandapp.bean;

import com.koron.common.bean.BaseQueryBean;
import com.koron.common.bean.BaseWorkflowBean;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 信息系统需求申请
 * @author 
 */
public class DemandappQueryBean extends BaseQueryBean implements Serializable {
    private String id;

    @ApiModelProperty(hidden = true)
    private String orgid;

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
     * 工单号
     */
    @ApiModelProperty(value = "服务单号")
    private String orderNo;

    /**
     * 应用系统名称
     */
    @ApiModelProperty(value = "应用系统名称（非数据字典，可选择） 必填 暂时没有先手给一个")
    private String appsysName;

    /**
     * 申请描述
     */
    @ApiModelProperty(value = "申请描述 必填")
    private String appContent;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAppsysName() {
        return appsysName;
    }

    public void setAppsysName(String appsysName) {
        this.appsysName = appsysName;
    }

    public String getAppContent() {
        return appContent;
    }

    public void setAppContent(String appContent) {
        this.appContent = appContent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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