package com.koron.web.asset.assets.assetsrepdet.bean;

import com.koron.common.bean.BaseBean;

import java.io.Serializable;
import java.util.Date;

/**
 * assets_repdet
 * @author 
 */
public class AssetsRepdetBean extends BaseBean implements Serializable {
    private String id;

    private String fRef;

    /**
     * 单据
     */
    private String billDate;

    /**
     * 服务单id
     */
    private String serviceId;

    /**
     * 服务单号
     */
    private String serviceOrderNo;

    /**
     * 服务类型
     */
    private String serviceType;

    /**
     * 维护人员id
     */
    private String employeeId;

    /**
     * 维护人员名称
     */
    private String staffunName;

    /**
     * 维护内容
     */
    private String appContent;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfRef() {
        return fRef;
    }

    public void setfRef(String fRef) {
        this.fRef = fRef;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceOrderNo() {
        return serviceOrderNo;
    }

    public void setServiceOrderNo(String serviceOrderNo) {
        this.serviceOrderNo = serviceOrderNo;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getStaffunName() {
        return staffunName;
    }

    public void setStaffunName(String staffunName) {
        this.staffunName = staffunName;
    }

    public String getAppContent() {
        return appContent;
    }

    public void setAppContent(String appContent) {
        this.appContent = appContent;
    }
}