package com.koron.web.asset.shift.bean;

import com.koron.common.bean.BaseQueryBean;
import com.koron.common.bean.BaseWorkflowBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 资产转移
 * @author 
 */
public class AssetShiftQueryBean extends BaseQueryBean implements Serializable {

    private String id;

    /**
     * 组织标识
     */
    private String orgid;

    /**
     * 单据编号
     */
    private String billNo;

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


    @ApiModelProperty(value = "原归属人名称")
    private String staffunName;


    @ApiModelProperty(value = "接收人名称")
    private String zystaffunName;


    @ApiModelProperty(value = "资产型号")
    private String goodsModel;

    /**
     * 状态:【0:草稿/1:已提交/2:已启用/3:已停用/4:已驳回】
     */
    private Integer status;


    private static final long serialVersionUID = 1L;

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public String getStaffunName() {
        return staffunName;
    }

    public void setStaffunName(String staffunName) {
        this.staffunName = staffunName;
    }

    public String getZystaffunName() {
        return zystaffunName;
    }

    public void setZystaffunName(String zystaffunName) {
        this.zystaffunName = zystaffunName;
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

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }



}