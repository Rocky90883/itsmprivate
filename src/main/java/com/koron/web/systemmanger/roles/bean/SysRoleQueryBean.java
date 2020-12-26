package com.koron.web.systemmanger.roles.bean;

import com.koron.common.bean.BaseQueryBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * sys_role
 * @author 
 */
@Data
public class SysRoleQueryBean extends BaseQueryBean {

    @ApiModelProperty(value = "角色id")
    private String id;


    @ApiModelProperty(value = "角色名称")
    private String roleName;


    @ApiModelProperty(value = "排序")
    private Integer sort;


    @ApiModelProperty(value = "描述")
    private String description;


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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}