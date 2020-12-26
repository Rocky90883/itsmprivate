package com.koron.web.meeting;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.koron.common.bean.BaseBean;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.util.List;

@ExcelTarget("MeetingBean")
public class MeetingBean extends BaseBean {

    private String id;

    @Excel(name = "会议日期*")
    private String  billDate;

    @Excel(name = "会议名称*")
    private String title;

    @Excel(name = "议题号*")
    private Integer sort;

    @Excel(name = "议题*")
    private String content;

    private List<String> contentList;

    //状态
    private Integer status;

    public MeetingBean() {
    }

    public MeetingBean(String id, String billDate, String title, Integer sort, String content, List<String> contentList, Integer status) {
        this.id = id;
        this.billDate = billDate;
        this.title = title;
        this.sort = sort;
        this.content = content;
        this.contentList = contentList;
        this.status = status;
    }

    public MeetingBean(String billDate, String title, Integer sort, String content) {
        this.billDate = billDate;
        this.title = title;
        this.sort = sort;
        this.content = content;
    }

    @Override
    public String toString() {
        return "MeetingBean{" +
                "id='" + id + '\'' +
                ", billDate='" + billDate + '\'' +
                ", title='" + title + '\'' +
                ", sort=" + sort +
                ", content='" + content + '\'' +
                ", contentList=" + contentList +
                ", status=" + status +
                '}';
    }

    public String getId() {
        return id;
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

    public List<String> getContentList() {
        return contentList;
    }

    public void setContentList(List<String> contentList) {
        this.contentList = contentList;
    }
}
