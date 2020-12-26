package com.koron.web.systemmanger;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;

public class WanhuDataImport {

    Logger logger = Logger.getLogger(WanhuDataImport.class);

    public static List<DataBean> importDepartment() {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://oa.ds.com:8080/UUMS/apiservice.do");
        String content = "{\"cmd\":\"syncOrgElements\",\"appname\":\"testa\",\"password\":\"269125\",\"timestamp\":0}";
        String ret = "";
        post.setEntity(new StringEntity(content, "utf8"));
        List<DataBean> list = new ArrayList<DataBean>();
        try {
            CloseableHttpResponse response = client.execute(post);
            ret = EntityUtils.toString(response.getEntity());
            DataListBean databean = new Gson().fromJson(ret, DataListBean.class);
            list = databean.getData();
            return list;
        } catch (IOException ex) {
            ex.printStackTrace();
            return list;
        }

    }

    public static void main(String[] args) {
        System.out.print(importDepartment());
    }


    public class DataBean {
        /**
         * 机构信息
         */
        private Integer type;
        private String name;
        private String code;
        private String codeName;
        private String superOrgName;
        private String superOrgCode;
        private String level;
        private String location;
        private String nameString;
        private String codeString;
        private String description;
        private String orgOrderCode;
        private String orgTel;

        /* 职员信息 */
        private String userId;
        private String userName;
        private String account;
        private String email;
        private String phone;
        private String telPhone;
        private String shortPhone;
        private String orgNameString;
        private String orgId;
        private String orgName;
        private String orgCode;
        private Integer sex;
        private Integer status;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCodeName() {
            return codeName;
        }

        public void setCodeName(String codeName) {
            this.codeName = codeName;
        }

        public String getSuperOrgName() {
            return superOrgName;
        }

        public void setSuperOrgName(String superOrgName) {
            this.superOrgName = superOrgName;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getNameString() {
            return nameString;
        }

        public void setNameString(String nameString) {
            this.nameString = nameString;
        }

        public String getCodeString() {
            return codeString;
        }

        public void setCodeString(String codeString) {
            this.codeString = codeString;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getOrgOrderCode() {
            return orgOrderCode;
        }

        public void setOrgOrderCode(String orgOrderCode) {
            this.orgOrderCode = orgOrderCode;
        }

        public String getOrgTel() {
            return orgTel;
        }

        public void setOrgTel(String orgTel) {
            this.orgTel = orgTel;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getTelPhone() {
            return telPhone;
        }

        public void setTelPhone(String telPhone) {
            this.telPhone = telPhone;
        }

        public String getShortPhone() {
            return shortPhone;
        }

        public void setShortPhone(String shortPhone) {
            this.shortPhone = shortPhone;
        }

        public String getOrgNameString() {
            return orgNameString;
        }

        public void setOrgNameString(String orgNameString) {
            this.orgNameString = orgNameString;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getOrgCode() {
            return orgCode;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getSuperOrgCode() {
            return superOrgCode;
        }

        public void setSuperOrgCode(String superOrgCode) {
            this.superOrgCode = superOrgCode;
        }

        public Integer getSex() {
            return sex;
        }

        public void setSex(Integer sex) {
            this.sex = sex;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

    }

    public class DataListBean {
        private ArrayList<DataBean> data;
        private String code;
        private String message;

        public ArrayList<DataBean> getData() {
            return data;
        }

        public void setData(ArrayList<DataBean> data) {
            this.data = data;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
