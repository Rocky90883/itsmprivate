package com.koron.web.systemmanger.user.bean;

import com.koron.common.bean.StaffBean;

public class UserInfoVo extends StaffBean{

    /**
     * 短名
     */
    private String deparshortname;
    /**
     * 组织编码
     */
    private String deparcode;

    public String getDeparshortname() {
        return deparshortname;
    }

    public void setDeparshortname(String deparshortname) {
        this.deparshortname = deparshortname;
    }

    public String getDeparcode() {
        return deparcode;
    }

    public void setDeparcode(String deparcode) {
        this.deparcode = deparcode;
    }
}
