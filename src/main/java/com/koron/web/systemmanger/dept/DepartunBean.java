package com.koron.web.systemmanger.dept;

import java.io.Serializable;

public class DepartunBean {

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
     * tree_type
     */
    private Integer treeType;

    /**
     * 全称
     */
    private String name;

    /**
     * 简称
     */
    private String shortname;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 描述
     */
    private String description;

    /**
     * phone
     */
    private String phone;

    /**
     * 组织类型 1集团2片区4水司8水厂16部门
     */
    private Integer orgKind;

    /**
     * 业务类型 1原水2自来水4污水8工程
     */
    private Integer businessKind;

    /**
     * sync_flag
     */
    private Integer syncFlag;

    /**
     * standard_code
     */
    private String standardCode;

    /**
     * mask
     */
    private Integer mask;

    /**
     * parentmask
     */
    private Integer parentmask;

    /**
     * childmask
     */
    private Integer childmask;

    /**
     * seq
     */
    private String seq;

    /**
     * link
     */
    private String link;

    /**
     * create_time
     */
    private String createTime;

    /**
     * creator
     */
    private String creator;

    /**
     * last_modification_time
     */
    private String lastModificationTime;

    /**
     * last_modifier
     */
    private String lastModifier;

    /**
     * 是否目录
     */
    private int isCatalog;

    public int getIsCatalog() {
        return isCatalog;
    }

    public void setIsCatalog(int isCatalog) {
        this.isCatalog = isCatalog;
    }

    public DepartunBean() {
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

    public Integer getTreeType() {
        return treeType;
    }

    public void setTreeType(Integer treeType) {
        this.treeType = treeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getOrgKind() {
        return orgKind;
    }

    public void setOrgKind(Integer orgKind) {
        this.orgKind = orgKind;
    }

    public Integer getBusinessKind() {
        return businessKind;
    }

    public void setBusinessKind(Integer businessKind) {
        this.businessKind = businessKind;
    }

    public Integer getSyncFlag() {
        return syncFlag;
    }

    public void setSyncFlag(Integer syncFlag) {
        this.syncFlag = syncFlag;
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }

    public Integer getMask() {
        return mask;
    }

    public void setMask(Integer mask) {
        this.mask = mask;
    }

    public Integer getParentmask() {
        return parentmask;
    }

    public void setParentmask(Integer parentmask) {
        this.parentmask = parentmask;
    }

    public Integer getChildmask() {
        return childmask;
    }

    public void setChildmask(Integer childmask) {
        this.childmask = childmask;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getLastModificationTime() {
        return lastModificationTime;
    }

    public void setLastModificationTime(String lastModificationTime) {
        this.lastModificationTime = lastModificationTime;
    }

    public String getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(String lastModifier) {
        this.lastModifier = lastModifier;
    }
}