package com.koron.web.systemmanger.dept;

import com.koron.common.bean.BaseQueryBean;
import io.swagger.annotations.ApiModelProperty;

public class DepartunQueyBean extends BaseQueryBean {

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "员工code")
    private String parentCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
