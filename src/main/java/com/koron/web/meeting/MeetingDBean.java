package com.koron.web.meeting;

public class MeetingDBean {

    private Integer sort;

    private String content;

    @Override
    public String toString() {
        return "MeetingDBean{" +
                "sort=" + sort +
                ", content='" + content + '\'' +
                '}';
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
