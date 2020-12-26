package com.koron.web.workflow.bean;

public class WorkflowResultBean {

    /**
     * 本地业务id
     */
    private String id;


    /**
     * 工作流需要处理的表名
     */
    private String tableName;

    /**
     * 需要处理的实验室id
     */
    private String laboratoryId;

    /**
     *启动工作流返回的流程id号
     */
    private String processinsId;


    /**
     * 工作流当前的任务id
     */

    private String nowtaskId;


    /**
     * 当前节点id
     */
    private String nowactivitiId;


    /**
     * 当前节点名
     */

    private String nowactivitiName;


    /**
     * 当前节点操作(32提交审核下一步、)
     */
    private String nowOpt;


    /**
     * 当前节点结束时间
     */
    private String nowEndTime;


    /**
     * 当前节点办理人
     */
    private String	nowAssignee;


    /**
     * 下级任务id
     */
    private String nexttaskId;


    /**
     * 下级节点id
     */
    private String nextactivitiId;


    /**
     * 下级节点名
     */
    private String nextactivitiName;


    /**
     * 下级节点候选人
     */
    private String nextcandidateUsers;

    /**
     * 下级节点候选人名称
     */
    private String auditorName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getLaboratoryId() {
        return laboratoryId;
    }

    public void setLaboratoryId(String laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public String getProcessinsId() {
        return processinsId;
    }

    public void setProcessinsId(String processinsId) {
        this.processinsId = processinsId;
    }

    public String getNowtaskId() {
        return nowtaskId;
    }

    public void setNowtaskId(String nowtaskId) {
        this.nowtaskId = nowtaskId;
    }

    public String getNowactivitiId() {
        return nowactivitiId;
    }

    public void setNowactivitiId(String nowactivitiId) {
        this.nowactivitiId = nowactivitiId;
    }

    public String getNowactivitiName() {
        return nowactivitiName;
    }

    public void setNowactivitiName(String nowactivitiName) {
        this.nowactivitiName = nowactivitiName;
    }

    public String getNowOpt() {
        return nowOpt;
    }

    public void setNowOpt(String nowOpt) {
        this.nowOpt = nowOpt;
    }

    public String getNowEndTime() {
        return nowEndTime;
    }

    public void setNowEndTime(String nowEndTime) {
        this.nowEndTime = nowEndTime;
    }

    public String getNowAssignee() {
        return nowAssignee;
    }

    public void setNowAssignee(String nowAssignee) {
        this.nowAssignee = nowAssignee;
    }

    public String getNexttaskId() {
        return nexttaskId;
    }

    public void setNexttaskId(String nexttaskId) {
        this.nexttaskId = nexttaskId;
    }

    public String getNextactivitiId() {
        return nextactivitiId;
    }

    public void setNextactivitiId(String nextactivitiId) {
        this.nextactivitiId = nextactivitiId;
    }

    public String getNextactivitiName() {
        return nextactivitiName;
    }

    public void setNextactivitiName(String nextactivitiName) {
        this.nextactivitiName = nextactivitiName;
    }

    public String getNextcandidateUsers() {
        return nextcandidateUsers;
    }

    public void setNextcandidateUsers(String nextcandidateUsers) {
        this.nextcandidateUsers = nextcandidateUsers;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }
}
