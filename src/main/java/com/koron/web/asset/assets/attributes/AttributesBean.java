package com.koron.web.asset.assets.attributes;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * 资产属性
 * @author 
 */
public class AttributesBean implements Serializable {
    /**
     * 台账id
     */
    private String assetId;

    /**
     * cpu
     */
    @Excel(name = "cpu")
    private String cpu;

    /**
     * 内存
     */
    @Excel(name = "内存")
    private String ram;

    /**
     * 硬盘
     */
    @Excel(name = "硬盘")
    private String disk;

    /**
     * 瓦数
     */
    @Excel(name = "瓦数")
    private String wattage;

    /**
     * 接口数
     */
    @Excel(name = "接口数")
    private String portqty;

    /**
     * 带宽
     */
    @Excel(name = "带宽")
    private String bandwidth;

    /**
     * 规格
     */
    @Excel(name = "规格")
    private String spec;

    /**
     * 版本
     */
    @Excel(name = "版本")
    private String edition;

    /**
     * 保修年限
     */
    @Excel(name = "保修年限")
    private String warranty;

    private static final long serialVersionUID = 1L;

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String getWattage() {
        return wattage;
    }

    public void setWattage(String wattage) {
        this.wattage = wattage;
    }

    public String getPortqty() {
        return portqty;
    }

    public void setPortqty(String portqty) {
        this.portqty = portqty;
    }

    public String getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(String bandwidth) {
        this.bandwidth = bandwidth;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }
}