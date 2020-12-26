package com.koron.web.workflow.bean;

import com.koron.common.bean.BaseQueryBean;
import io.swagger.annotations.ApiModelProperty;

public class WorkDbDto extends BaseQueryBean {

    @ApiModelProperty(value = "1我发起的2我待审的（包含未办理和我可认领）3参与的(我发起、我办理、我沟通)")
    private Integer type;

    @ApiModelProperty(value = "服务类型")
    private String procInstType;

    @ApiModelProperty(value = "服务单号")
    private String orderNo;

    @ApiModelProperty(value = "申请内容")
    private String appContent;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getProcInstType() {
        return procInstType;
    }

    public void setProcInstType(String procInstType) {
        this.procInstType = procInstType;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAppContent() {
        return appContent;
    }

    public void setAppContent(String appContent) {
        this.appContent = appContent;
    }
}
