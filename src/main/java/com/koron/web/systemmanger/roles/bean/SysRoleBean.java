package com.koron.web.systemmanger.roles.bean;

import java.io.Serializable;
import java.util.Date;

import com.koron.common.bean.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色表
 * @author 
 */
public class SysRoleBean extends BaseBean implements Serializable {
    private String id;


    @ApiModelProperty(value = "角色名称")
    private String roleName;


    @ApiModelProperty(value = "排序")
    private Integer sort;


    @ApiModelProperty(value = "角色描述")
    private String description;

    //阅览权限 all全部、my自己
    @ApiModelProperty(hidden = true)
    private String broper;

    @ApiModelProperty(value = "1系统角色不允许删除、0常规角色")
    private Integer isSys;

    @ApiModelProperty(value = "状态(-1禁用、0启用)")
    private Integer status;


    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBroper() {
        return broper;
    }

    public void setBroper(String broper) {
        this.broper = broper;
    }

    public Integer getIsSys() {
        return isSys;
    }

    public void setIsSys(Integer isSys) {
        this.isSys = isSys;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}