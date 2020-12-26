package com.koron.web.workorder.recordapp.bean;

import java.io.Serializable;

import com.koron.common.bean.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * recordapp
 * @author 
 */
@Data
public class Recordapp extends BaseBean implements Serializable {

    @ApiModelProperty(hidden = true)
    private String id;

    @ApiModelProperty(hidden = true)
    private String orgid;

    /**
     * 服务单号
     */
    @ApiModelProperty(value = "服务单号")
    private String orderNo;

    /**
     * 申请日期
     */
    @ApiModelProperty(value = "申请日期")
    private String billDate;

    /**
     * 申请人
     */
    @ApiModelProperty(value = "申请人code")
    private String employeeId;

    /**
     * 来访电话
     */
    @ApiModelProperty(value = "来访电话")
    private String phone;

    /**
     * 服务记录类型 1投诉、2咨询、3建议、4其他
     */
    @ApiModelProperty(value = "服务记录类型 1投诉、2咨询、3建议、4其他")
    private Integer apptype;

    /**
     * 申请描述
     */
    @ApiModelProperty(value = "申请描述")
    private String appContent;

    /**
     * 记录人名称
     */
    @ApiModelProperty(value = "记录人名称")
    private String empName;


    @ApiModelProperty(hidden = true)
    private String depatName;


    @ApiModelProperty(hidden = true)
    private String staffunName;


    private static final long serialVersionUID = 1L;

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

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getApptype() {
        return apptype;
    }

    public void setApptype(Integer apptype) {
        this.apptype = apptype;
    }

    public String getAppContent() {
        return appContent;
    }

    public void setAppContent(String appContent) {
        this.appContent = appContent;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}