package com.koron.common.bean;

public class BaseOracleQueryBean extends BaseBean{

    public static final Integer DEFAULT_PAGE_SIZE = Integer.valueOf(10);
    public static final int STARTPAGE = 1;
    public static final int STARTOFFSET = 1;
    private int page;
    private int pageSize;
    private int offset;
    private int totalPage;
    private int totalNumber;
    private int begin;
    private int end;

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    public BaseOracleQueryBean() {
        this.pageSize = DEFAULT_PAGE_SIZE.intValue();
    }

    public int getPage() {
        return this.page < 1 ? 1 : this.page;
    }

    public BaseOracleQueryBean setPage(int page) {
        this.page = page;
        this.calculateOffset();
        return this;
    }

    public int getPageSize() {
        return this.pageSize == 0 ? DEFAULT_PAGE_SIZE.intValue() : this.pageSize;
    }

    public BaseOracleQueryBean setPageSize(int pageSize) {
        this.pageSize = pageSize;
        if (this.pageSize == 0) {
            this.pageSize = DEFAULT_PAGE_SIZE.intValue();
        }

        this.calculateOffset();
        return this;
    }

    private void calculateOffset() {
        this.setOffset((this.getPage() - 1) * this.getPageSize() + 1);
    }

    public int getOffset() {
        return this.offset;
    }

    public BaseOracleQueryBean setOffset(int offset) {
        this.offset = offset;
        this.begin = offset;
        this.end = this.page==1 ? this.pageSize : this.begin+this.getPageSize()-1;
        return this;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public BaseOracleQueryBean setTotalPage(int totalPage) {
        this.totalPage = totalPage;
        return this;
    }

    public int getTotalNumber() {
        return this.totalNumber;
    }

    public BaseOracleQueryBean setTotalNumber(int totalNumber) {
        this.setTotalPage((totalNumber - 1) / this.getPageSize() + 1);
        this.totalNumber = totalNumber;
        return this;
    }



}
