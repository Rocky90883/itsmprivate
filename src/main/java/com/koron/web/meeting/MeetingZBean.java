package com.koron.web.meeting;

import java.util.List;

public class MeetingZBean {

    private String id;

    private String billDate;

    private String title;

    private List<MeetingDBean> listDet;

    private List<String> contentList;

    @Override
    public String toString() {
        return "MeetingZBean{" +
                "id='" + id + '\'' +
                ", billDate='" + billDate + '\'' +
                ", title='" + title + '\'' +
                ", listDet=" + listDet +
                ", contentList=" + contentList +
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MeetingDBean> getListDet() {
        return listDet;
    }

    public void setListDet(List<MeetingDBean> listDet) {
        this.listDet = listDet;
    }

    public List<String> getContentList() {
        return contentList;
    }

    public void setContentList(List<String> contentList) {
        this.contentList = contentList;
    }
}
