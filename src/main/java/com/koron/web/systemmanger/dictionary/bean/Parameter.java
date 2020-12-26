package com.koron.web.systemmanger.dictionary.bean;

import io.swagger.annotations.ApiModelProperty;

public class Parameter {
    private String id;

    @ApiModelProperty(value = "组id")
    private String dictId;

    @ApiModelProperty(value = "字典名称")
    private String parameterName;

    @ApiModelProperty(value = "字典值")
    private String parameterValue;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDictId() {
        return dictId;
    }
    public void setDictId(String dictId) {
        this.dictId = dictId;
    }
    public String getParameterName() {
        return parameterName;
    }
    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }
    public String getParameterValue() {
        return parameterValue;
    }
    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

}