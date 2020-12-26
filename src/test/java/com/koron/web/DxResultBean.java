package com.koron.web;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DxResultBean {


    private int code;
    private String description;
    private String token;
    private Integer status;
    private Object data;

    @Override
    public String toString() {
        return "DxResultBean{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", token='" + token + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}
