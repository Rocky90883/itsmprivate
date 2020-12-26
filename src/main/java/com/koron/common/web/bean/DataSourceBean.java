package com.koron.common.web.bean;

import com.koron.common.bean.BaseBean;

/**
 * 数据源信息实体类
 * @author rocky
 */
public class DataSourceBean extends BaseBean {
    /**
     * 主键
     */
    private String id;
    /**
     * 标识
     */
    private String mark;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型
     */
    private String driverClassName;
    /**
     * 地址
     */
    private String dbHost;
    /**
     * 数据库名
     */
    private String dbName;
    /**
     * 端口
     */
    private String dbPort;
    /**
     * 用户名
     */
    private String dbUser;
    /**
     * 密码
     */
    private String dbPassword;
    /**
     * 状态
     */
    private Integer status;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getMark() {
        return mark;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
    public String getDriverClassName() {
        return driverClassName;
    }
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
    public String getDbHost() {
        return dbHost;
    }
    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }
    public String getDbName() {
        return dbName;
    }
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    public String getDbPort() {
        return dbPort;
    }
    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }
    public String getDbUser() {
        return dbUser;
    }
    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }
    public String getDbPassword() {
        return dbPassword;
    }
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

}
