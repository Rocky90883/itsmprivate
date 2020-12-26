package com.koron.web.maintain.spareoutstock.bean;

import com.koron.common.bean.BaseQueryBean;
import com.koron.common.bean.BaseWorkflowBean;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 备件出库
 * @author 
 */
public class SpareOutstockQueryBean extends BaseQueryBean implements Serializable {

    /**
     * 出库单号
     */
    @ApiModelProperty(value = "出库单号")
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
     * 入库单号
     */
    @ApiModelProperty(value = "入库单号")
    private String instockNo;

    /**
     * 备件类型id
     */
    @ApiModelProperty(value = "备件类型id")
    private String assetTypeId;

    /**
     * 备件型号
     */
    @ApiModelProperty(value = "备件型号")
    private String goodsModel;

    /**
     * 领用人名称
     */
    @ApiModelProperty(value = "领用人名称")
    private String staffunName;

    /**
     * 领用人部门名称
     */
    @ApiModelProperty(value = "领用人部门名称")
    private String depatName;

    /**
     * 状态:【/1:启用/2:草稿/3:已停用/4:已驳回/5:废弃/6:转办中】
     */
    @ApiModelProperty(value = "流程状态")
    private Integer status;


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

    public String getInstockNo() {
        return instockNo;
    }

    public void setInstockNo(String instockNo) {
        this.instockNo = instockNo;
    }

    public String getAssetTypeId() {
        return assetTypeId;
    }

    public void setAssetTypeId(String assetTypeId) {
        this.assetTypeId = assetTypeId;
    }

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

    public String getDepatName() {
        return depatName;
    }

    public void setDepatName(String depatName) {
        this.depatName = depatName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}