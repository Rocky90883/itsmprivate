package com.koron.web.meeting;

import com.koron.common.bean.BaseOracleQueryBean;

public class MeetingQueryBean extends BaseOracleQueryBean {

    private String id;

    //日期
    private String billDate;

    //排序
    private Integer sort;

    //标题
    private String title;

    //内容
    private String content;

    //状态
    private Integer status;

//    //组织id
//    private String orgId;
//
//    public String getOrgId() {
//        return orgId;
//    }
//
//    public void setOrgId(String orgId) {
//        this.orgId = orgId;
//    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MeetingQueryBean{" +
                "id='" + id + '\'' +
                ", billDate='" + billDate + '\'' +
                ", sort=" + sort +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
