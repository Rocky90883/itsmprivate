package com.koron.web.systemmanger.dept;

import java.util.List;

public class DepartunTreeBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 上级部门
     */
    private String parentCode;

    /**
     * code
     */
    private String code;


    /**
     * 全称
     */
    private String name;

    /**
     * 组织类型 1集团2片区4水司8水厂16部门
     */
    private Integer orgKind;


    private List<DepartunTreeBean> children;


    public DepartunTreeBean() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrgKind() {
        return orgKind;
    }

    public void setOrgKind(Integer orgKind) {
        this.orgKind = orgKind;
    }

    public List<DepartunTreeBean> getChildren() {
        return children;
    }

    public void setChildren(List<DepartunTreeBean> children) {
        this.children = children;
    }
}