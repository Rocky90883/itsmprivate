package com.koron.common.bean;

import com.koron.util.SessionUtil;
import io.swagger.annotations.ApiModelProperty;

public class BaseQueryBean {

    @ApiModelProperty(value = "第几页")
    private int page=1;

    @ApiModelProperty(value = "每页条数")
    private int pageCount = 20;

    /**
     * 模糊查询字段
     */
    private String fuzzyQuery;

    //登陆人code
    private String logincode = SessionUtil.getUseerInfoCode();

    //阅览范围
    private String broper = SessionUtil.getUseerBroper();

    public String getFuzzyQuery() {
        return fuzzyQuery;
    }
    public void setFuzzyQuery(String fuzzyQuery) {
        this.fuzzyQuery = fuzzyQuery;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getPageCount() {
        return pageCount;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

}