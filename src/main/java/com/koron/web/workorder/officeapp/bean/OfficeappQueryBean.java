package com.koron.web.workorder.officeapp.bean;

import com.koron.common.bean.BaseQueryBean;
import com.koron.common.bean.BaseWorkflowBean;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 正版软件申请
 * @author 
 */
public class OfficeappQueryBean extends BaseQueryBean implements Serializable {
    private String id;

    @ApiModelProperty(hidden=true)
    private String orgid;

    /**
     * 服务编号
     */
    @ApiModelProperty(value = "服务编号")
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
     * 软件操作类型1安装、2卸载
     */
    @ApiModelProperty(value = "软件操作类型1安装、2卸载 来源数据字典")
    private Integer apptype;

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
     * 软件资产型号 字符串录入
     */
    @ApiModelProperty(value = "软件资产型号 字符串录入")
    private String goodsModel;

    /**
     * 状态:【/1:启用/2:草稿/3:已停用/4:已驳回/5:废弃/6:转办中】
     */
    @ApiModelProperty(value = "流程状态")
    private Integer status;


    private static final long serialVersionUID = 1L;

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getApptype() {
        return apptype;
    }

    public void setApptype(Integer apptype) {
        this.apptype = apptype;
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