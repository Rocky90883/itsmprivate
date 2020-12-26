package com.koron.web.firstpage.bean;

import java.util.List;

public class FirstPageVo {

    private List<ServiceAndMtBean> servicelist;

    private List<ServiceAndMtBean> prolist;

    private List<ServiceAndMtBean> modilist;

    private List<ServiceAndMtBean> relelist;

    private List<SpareFristBean> sparelist;


    public List<ServiceAndMtBean> getServicelist() {
        return servicelist;
    }

    public void setServicelist(List<ServiceAndMtBean> servicelist) {
        this.servicelist = servicelist;
    }

    public List<ServiceAndMtBean> getProlist() {
        return prolist;
    }

    public void setProlist(List<ServiceAndMtBean> prolist) {
        this.prolist = prolist;
    }

    public List<ServiceAndMtBean> getModilist() {
        return modilist;
    }

    public void setModilist(List<ServiceAndMtBean> modilist) {
        this.modilist = modilist;
    }

    public List<ServiceAndMtBean> getRelelist() {
        return relelist;
    }

    public void setRelelist(List<ServiceAndMtBean> relelist) {
        this.relelist = relelist;
    }

    public List<SpareFristBean> getSparelist() {
        return sparelist;
    }

    public void setSparelist(List<SpareFristBean> sparelist) {
        this.sparelist = sparelist;
    }
}
