package com.koron.web.workorder.repairapp.bean;

import com.koron.common.bean.BaseQueryBean;
import com.koron.common.bean.BaseWorkflowBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 报障报修
 * @author 
 */
@Data
public class RepairappQueryBean extends BaseQueryBean implements Serializable {
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
     * 归属部门名称(关联显示)
     */
    @ApiModelProperty(value = "归属部门名称(关联显示)")
    private String depatName;

    /**
     * 所有者名称(关联显示)
     */
    @ApiModelProperty(value = "申请人(关联显示)")
    private String staffunName;


    /**
     * 服务编号
     */
    @ApiModelProperty(value = "服务编号")
    private String orderNo;

    /**
     * 硬件类 来源数据字典
     */
    @ApiModelProperty(value = "硬件类 来源数据字典")
    private Integer fixedtype;

    /**
     * 办公软件类 来源数据字典
     */
    @ApiModelProperty(value = "办公软件类 来源数据字典")
    private Integer softtype;

    /**
     * 业务系统类 来源数据字典
     */
    @ApiModelProperty(value = "业务系统类 来源数据字典")
    private Integer apptype;

    /**
     * 应用系统 名称
     */
    @ApiModelProperty(value = "应用系统 名称")
    private String appsysName;

    /**
     * 流程id
     */
    @ApiModelProperty(value = "流程id")
    private String workflowid;

    /**
     * 状态    0停用、1启用、2草稿、3审核中、4驳回、5废弃、6转办
     */
    @ApiModelProperty(value = "流程节点 来源数据字典")
    private Integer status;


    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getFixedtype() {
        return fixedtype;
    }

    public void setFixedtype(Integer fixedtype) {
        this.fixedtype = fixedtype;
    }

    public Integer getSofttype() {
        return softtype;
    }

    public void setSofttype(Integer softtype) {
        this.softtype = softtype;
    }

    public Integer getApptype() {
        return apptype;
    }

    public void setApptype(Integer apptype) {
        this.apptype = apptype;
    }

    public String getAppsysName() {
        return appsysName;
    }

    public void setAppsysName(String appsysName) {
        this.appsysName = appsysName;
    }

    public String getWorkflowid() {
        return workflowid;
    }

    public void setWorkflowid(String workflowid) {
        this.workflowid = workflowid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}