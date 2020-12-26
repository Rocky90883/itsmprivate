package com.koron.web.workorder.scoremark.bean;


import com.koron.common.bean.BaseBean;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 评分记录
 * @author 
 */
public class ScoremarkBean extends BaseBean implements Serializable {

    private String id;

    /**
     * 固定标识 表名
     */
    @ApiModelProperty(value = "固定标识")
    private String procinsttype;

    /**
     * 单据id
     */
    @ApiModelProperty(value = "单据id")
    private String sourceId;

    /**
     * 评分
     */
    @ApiModelProperty(value = "评分")
    private Double score;

    /**
     * 留言
     */
    @ApiModelProperty(value = "留言")
    private String leamsg;


    private static final long serialVersionUID = 1L;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcinsttype() {
        return procinsttype;
    }

    public void setProcinsttype(String procinsttype) {
        this.procinsttype = procinsttype;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getLeamsg() {
        return leamsg;
    }

    public void setLeamsg(String leamsg) {
        this.leamsg = leamsg;
    }
}