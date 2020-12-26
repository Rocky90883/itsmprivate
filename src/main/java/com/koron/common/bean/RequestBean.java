package com.koron.common.bean;

/**
 * 请求体参数
 *
 * @author DaiSy
 *
 */
public class RequestBean<T> {
    /**
     * token
     */
    private String token;
    /**
     * json数组对象，根据具体业务确定内容
     */
    private T data;

//    private String refUrl;

//    private String busicode;

    private String orgId;	//公司ID


    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

//    public String getBusicode() {
//        return busicode;
//    }

//    public void setBusicode(String busicode) {
//        this.busicode = busicode;
//    }

//    public String getRefUrl() {
//        return refUrl;
//    }

//    public void setRefUrl(String refUrl) {
//        this.refUrl = refUrl;
//    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RequestBean [token=" + token + ", data=" + data + ", getToken()=" + getToken() + ", getData()="
                + getData() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }

}
