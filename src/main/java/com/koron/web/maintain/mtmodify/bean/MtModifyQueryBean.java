package com.koron.web.maintain.mtmodify.bean;

import com.koron.common.bean.BaseQueryBean;
import com.koron.common.bean.BaseWorkflowBean;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 变更管理
 * @author 
 */
public class MtModifyQueryBean extends BaseQueryBean implements Serializable {
    /**
     * 服务单号
     */
    @ApiModelProperty(value = "变更单号")
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
     * 申请人名称
     */
    @ApiModelProperty(value = "申请人名称")
    private String staffunName;

    /**
     * 关联工单号
     */
    @ApiModelProperty(value = "关联工单号")
    private String serviceOrderNo;

    /**
     * 状态:【/1:启用/2:草稿/3:已停用/4:已驳回/5:废弃/6:转办中】
     */
    @ApiModelProperty(value = "流程状态")
    private Integer status;

    /**
     * 分配状态 1已分配、0请求中
     */
    @ApiModelProperty(value = "分配状态 1已分配、0请求中")
    private Integer handleStatus;


    private static final long serialVersionUID = 1L;


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

    public String getStaffunName() {
        return staffunName;
    }

    public void setStaffunName(String staffunName) {
        this.staffunName = staffunName;
    }

    public String getServiceOrderNo() {
        return serviceOrderNo;
    }

    public void setServiceOrderNo(String serviceOrderNo) {
        this.serviceOrderNo = serviceOrderNo;
    }

    public Integer getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(Integer handleStatus) {
        this.handleStatus = handleStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}