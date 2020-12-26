package com.koron.web.asset.assets.assetsrepdet.bean;

import com.koron.common.bean.BaseQueryBean;
import io.swagger.annotations.ApiModelProperty;

public class AssetsRepdetDto extends BaseQueryBean {

    @ApiModelProperty(value = "台账id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
