package com.koron.web.systemmanger;

import com.google.gson.Gson;
import com.koron.util.PropertiesUtil;
import com.koron.web.systemmanger.dept.DepartunBean;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import lombok.Data;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class UnifiedUserImport {

    Logger logger = Logger.getLogger(UnifiedUserImport.class);

    @Value("${uma.umaOrgNode.url}")
    private String orgnodeurl;

    @Value("${uma.umaStaff.url}")
    public String staffurl;



    /**
     * 获取部门
     * @return
     */
    public static List<DepartunBean> importDepartment() {
        CloseableHttpClient client = HttpClients.createDefault();
//        HttpPost post = new HttpPost("http://10.13.1.43:9999/port/orgNode.htm?org=yhszy&root=yhszy");
        HttpPost post = new HttpPost(PropertiesUtil.getProperty("uma.umaOrgNode.url"));
//        String content = "{\"cmd\":\"syncOrgElements\",\"appname\":\"testa\",\"password\":\"269125\",\"timestamp\":0}";
        String ret = "";
        post.setEntity(new StringEntity("", "utf8"));
        List<DepartunBean> list = new ArrayList<DepartunBean>();
        try {
            CloseableHttpResponse response = client.execute(post);
            ret = EntityUtils.toString(response.getEntity());
            DepartListBean databean = new Gson().fromJson(ret, DepartListBean.class);
            list = databean.getData();
            return list;
        } catch (IOException ex) {
            ex.printStackTrace();
            return list;
        }
    }

    /**
     * 获取人员
     * @return
     */
    public static List<StaffunBean> importStaffun() {
        CloseableHttpClient client = HttpClients.createDefault();
//        HttpPost post = new HttpPost("http://10.13.1.43:9999/port/staff.htm?org=yhszy&root=yhszy");
        HttpPost post = new HttpPost(PropertiesUtil.getProperty("uma.umaStaff.url"));
//        String content = "{\"cmd\":\"syncOrgElements\",\"appname\":\"testa\",\"password\":\"269125\",\"timestamp\":0}";
        String ret = "";
        post.setEntity(new StringEntity("", "utf8"));
        List<StaffunBean> list = new ArrayList<StaffunBean>();
        try {
            CloseableHttpResponse response = client.execute(post);
            ret = EntityUtils.toString(response.getEntity());
            StaffunListBean databean = new Gson().fromJson(ret, StaffunListBean.class);
            list = databean.getData();
            return list;
        } catch (IOException ex) {
            ex.printStackTrace();
            return list;
        }
    }

    public static void main(String[] args) {
        System.out.print(importStaffun());
    }



    public class DepartListBean {
        private ArrayList<DepartunBean> data;
        private String code;
        private String message;

        public ArrayList<DepartunBean> getData() {
            return data;
        }

        public void setData(ArrayList<DepartunBean> data) {
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

    public class StaffunListBean {
        private ArrayList<StaffunBean> data;
        private String code;
        private String message;

        public ArrayList<StaffunBean> getData() {
            return data;
        }

        public void setData(ArrayList<StaffunBean> data) {
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
