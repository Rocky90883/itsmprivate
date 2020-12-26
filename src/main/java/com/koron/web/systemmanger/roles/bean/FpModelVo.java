package com.koron.web.systemmanger.roles.bean;

import com.koron.web.systemmanger.model.bean.ModelTreeBean;
import com.koron.web.systemmanger.model.bean.SysModelBean;

import java.util.List;

public class FpModelVo {


    private List<ModelTreeBean> modelTree;
    private List<SysModelBean> model;
    private List<String> modelId;

    public List<ModelTreeBean> getModelTree() {
        return modelTree;
    }

    public void setModelTree(List<ModelTreeBean> modelTree) {
        this.modelTree = modelTree;
    }

    public List<SysModelBean> getModel() {
        return model;
    }

    public void setModel(List<SysModelBean> model) {
        this.model = model;
    }

    public List<String> getModelId() {
        return modelId;
    }

    public void setModelId(List<String> modelId) {
        this.modelId = modelId;
    }
}
