package com.koron.common.bean;

import com.koron.common.type.ErrorCode;
import com.koron.util.CommonUtil;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

/**
 * 通用返回体
 * @author ky01
 *
 */

//@AllArgsConstructor
//@NoArgsConstructor
public class Result {
    private Integer code;

    private String description;

    private Object data;

    public static Result success(){
        Result result = new Result();
        result.code = ErrorCode.SUCCESS.getCode();
        result.description = ErrorCode.SUCCESS.getName();

        return result;
    }

    public static Result success(Object data){
        Result result = new Result();
        result.code = ErrorCode.SUCCESS.getCode();
        result.description = ErrorCode.SUCCESS.getName();
        result.data = data;

        return result;
    }

    public static Result error(ErrorCode e){
        Result result = new Result();
        result.code = e.getCode();
        result.description = e.getName();

        return result;
    }

    public static Result error(Integer code, String msg){
        Result result = new Result();
        result.code = code;
        result.description = msg;

        return result;
    }

    public String toJson() {
        return CommonUtil.toJson(this);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
