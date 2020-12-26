package com.koron.common.bean;

import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.koron.util.Constant;
import org.apache.log4j.Logger;

/**
 * 响应
 *
 * @author 许海宜 2019年10月9日
 */
public class ResponseBean {

    private static Logger log = Logger.getLogger(ResponseBean.class);

    private int code = Constant.MESSAGE_INT_SUCCESS;

    private String description = "success";

    private int status = 0;

    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * 创建自定义ResponseBean
     *
     * @param code
     * @param description
     * @return
     */
    public static ResponseBean create(int code, String description, Object data) {
        ResponseBean bean = new ResponseBean();
        bean.setCode(code);
        bean.setDescription(description);
        bean.setData(data);
        log.debug("MGBusinessCharge===========================MGBusinessCharge:"+bean.toJson());
        return bean;
    }
    public static ResponseBean create(Object data) {
        ResponseBean bean = new ResponseBean();
        bean.setData(data);
        log.debug("MGBusinessCharge===========================MGBusinessCharge:"+bean.toJson());
        return bean;
    }
    public static ResponseBean error(int code, String description) {
        ResponseBean bean = new ResponseBean();
        bean.setCode(code);
        bean.setDescription(description);
        log.debug("MGBusinessCharge===========================MGBusinessCharge:"+bean.toJson());
        return bean;
    }

    /**
     * 获取JSON字符串
     *
     * @return
     */
    public String toJson() {
        return new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING)
                .setDateFormat("yyyy-MM-dd HH:mm:ss SSS").create().toJson(this);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
