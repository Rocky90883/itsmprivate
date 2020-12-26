package com.koron.web.systemmanger.user.bean;

import com.koron.web.systemmanger.model.bean.SysModelVo;
import com.koron.web.systemmanger.roles.bean.FpModelVo;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class StaffunBean {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "员工id")
    private Integer id;


    @ApiModelProperty(value = "员工code")
    private String code;


    @ApiModelProperty(value = "流程平台code")
    private String workflowCode;


    @ApiModelProperty(value = "部门code")
    private String orgNodeCode;


    @ApiModelProperty(value = "部门名称")
    private String orgNodeName;


    @ApiModelProperty(value = "员工名称")
    private String name;


    @ApiModelProperty(value = "简称")
    private String shortName;


    @ApiModelProperty(value = "状态")
    private Integer status;


    @ApiModelProperty(value = "电话")
    private String phone;


    @ApiModelProperty(value = "手机")
    private String mobile;


    @ApiModelProperty(value = "邮箱")
    private String email;


    @ApiModelProperty(value = "职位")
    private String title;


    @ApiModelProperty(value = "性别")
    private Integer sex;


    @ApiModelProperty(value = "接口权限")
    private List<SysModelVo> interfaces;

    public StaffunBean() {
    }

    public List<SysModelVo> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<SysModelVo> interfaces) {
        this.interfaces = interfaces;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getWorkflowCode() {
        return workflowCode;
    }

    public void setWorkflowCode(String workflowCode) {
        this.workflowCode = workflowCode;
    }

    public String getOrgNodeCode() {
        return orgNodeCode;
    }

    public void setOrgNodeCode(String orgNodeCode) {
        this.orgNodeCode = orgNodeCode;
    }

    public String getOrgNodeName() {
        return orgNodeName;
    }

    public void setOrgNodeName(String orgNodeName) {
        this.orgNodeName = orgNodeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }


}
