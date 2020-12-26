package com.koron.web.systemmanger.user.bean;

import com.koron.common.bean.BaseQueryBean;
import io.swagger.annotations.ApiModelProperty;

public class StaffunQueryBean extends BaseQueryBean {

    @ApiModelProperty(value = "员工名称")
    private String name;

    @ApiModelProperty(value = "组织code")
    private String orgNodeCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgNodeCode() {
        return orgNodeCode;
    }

    public void setOrgNodeCode(String orgNodeCode) {
        this.orgNodeCode = orgNodeCode;
    }
}
