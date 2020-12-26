package com.koron.web.meeting;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.koron.common.bean.BaseBean;

@ExcelTarget("MeetingBean")
public class ImportMeetingErrBean extends BaseBean {

    private String id;

    @Excel(name = "错误信息*")
    private String errMsg;

    @Excel(name = "会议日期*")
    private String  billDate;

    @Excel(name = "会议名称*")
    private String title;

    @Excel(name = "议题号*")
    private Integer sort;

    @Excel(name = "议题*")
    private String content;

    //状态
    private Integer status;

    public ImportMeetingErrBean() {
    }

    public ImportMeetingErrBean(String billDate, String title, Integer sort, String content) {
        this.billDate = billDate;
        this.title = title;
        this.sort = sort;
        this.content = content;
    }

    @Override
    public String toString() {
        return "ImportMeetingErrBean{" +
                "id='" + id + '\'' +
                ", errMsg='" + errMsg + '\'' +
                ", billDate='" + billDate + '\'' +
                ", title='" + title + '\'' +
                ", sort=" + sort +
                ", content='" + content + '\'' +
                ", status=" + status +
                '}';
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
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
}
