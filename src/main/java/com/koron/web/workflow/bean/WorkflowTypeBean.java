package com.koron.web.workflow.bean;

/**
 * 流程类类型
 */
public class WorkflowTypeBean {


    private String id;

    private String workflowLogo;

    private String procInstType;

    private String procInstName;

    private int sort;

    private int remove_flag;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkflowLogo() {
        return workflowLogo;
    }

    public void setWorkflowLogo(String workflowLogo) {
        this.workflowLogo = workflowLogo;
    }

    public String getProcInstType() {
        return procInstType;
    }

    public void setProcInstType(String procInstType) {
        this.procInstType = procInstType;
    }

    public String getProcInstName() {
        return procInstName;
    }

    public void setProcInstName(String procInstName) {
        this.procInstName = procInstName;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getRemove_flag() {
        return remove_flag;
    }

    public void setRemove_flag(int remove_flag) {
        this.remove_flag = remove_flag;
    }
}
