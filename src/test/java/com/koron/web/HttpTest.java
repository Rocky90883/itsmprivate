package com.koron.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpTest {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Gson gson = new Gson();

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {

        try {
            String string = MAPPER.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json结果集转化为对象
     *
     * @param jsonData json数据
     * @param beanType 对象中的object类型
     * @return
     * @throws Exception
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) throws Exception {
        T t = MAPPER.readValue(jsonData, beanType);
        return t;
    }

    /**
     * 将json结果集转化为对象
     *
     * @param jsonData json数据
     * @param beanType 对象中的object类型
     * @return
     * @throws Exception
     */
    public static <T> T objectToPojo(Object jsonData, Class<T> beanType) throws Exception {
        T t = MAPPER.readValue(MAPPER.writeValueAsString(jsonData), beanType);
        return t;
    }


    /**
     * httpclient Post请求
     */
    public static String postData(String json,String url){
        //构造HttpClient的实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String entityStr = null;
        CloseableHttpResponse response = null;
        // 创建POST请求对象
        HttpPost httpPost = new HttpPost(url);
        //添加参数
        try {
            StringEntity se = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(se);
            // 浏览器表示
            httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
            // 传输的类型
            httpPost.addHeader("Content-Type", "application/json");
            // 执行请求
            response = httpClient.execute(httpPost);
            // 获得响应的实体对象
            HttpEntity entity = response.getEntity();
            // 使用Apache提供的工具类进行转换成字符串
            entityStr = EntityUtils.toString(entity, "UTF-8");
//			logger.debug(entityStr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            entityStr = "lose";
            System.err.println("Http协议出现问题");
            e.printStackTrace();
        } catch (IOException e) {
            entityStr = "lose";
            System.err.println("IO异常");
            e.printStackTrace();
        }finally {
            // 释放连接
            if (null != response) {
                try {
                    response.close();
                    httpClient.close();
                } catch (IOException e) {
                    System.err.println("释放连接出错");
                    e.printStackTrace();
                }
            }
        }
        return entityStr;
    }


    public void httpTest(){

        String url = "http://192.168.4.183:9998/send.htm";
        Map map = new HashMap();
        map.put("channel","G");
        map.put("content","测试");
        List users = new ArrayList<>();
        Map user = new HashMap();
        user.put("mobile","13189164130");
        users.add(user);
        map.put("users",users);
        String json = objectToJson(map);
        System.out.println(json);
//        String s = postData(json, url);
//        String s = sendPostJson(url, json);
        String s = sendPost2(url, json);        //可以
//        String s = sendPostJson(url, json);   //可以
        DxResultBean dxResultBean = null;
        try {
            dxResultBean = jsonToPojo(s, DxResultBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(dxResultBean.toString());
        System.out.println(s);


    }


    public void httpTest2(){

        String url = "https://caigou.gdhwater.com/api/todo/todoItem/getTodoNum";
        Map map = new HashMap();
        map.put("accountName","zouzh");
        String json = objectToJson(map);
        System.out.println(json);
//        String s = postData(json, url);
//        String s = sendPostJson(url, json);
        String s = sendPost2(url, json);
        System.out.println(s);
    }


    public void httpTest3(){

        String url = "https://caigou.gdhwater.com/api/todo/todoItem/getTodoNum";
        Map map = new HashMap();
        map.put("accountName","zouzh");
        String json = objectToJson(map);
        System.out.println(json);
//        String s = postData(json, url);
//        String s = sendPostJson(url, json);
//        String s = sendPost2(url, json);
//        JSONObject jsonObject = doPost(url, map, "UTF-8");
//        System.out.println(jsonObject);
    }

    public static String sendPostJson(String url, String json) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            try(CloseableHttpResponse response = httpClient.execute(httpPost)){
                return EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (Exception e) {
            System.out.println("发送post请求失败");
            throw new RuntimeException("发送post请求失败", e);
        }
    }

//    public static void main(String[] args){
//
//        httpTest();
//
//    }

    public static String sendPost2(String url, String data) {
        String response = null;

        try {
            CloseableHttpClient httpclient = null;
            CloseableHttpResponse httpresponse = null;
            try {
                httpclient = HttpClients.createDefault();
                HttpPost httppost = new HttpPost(url);
                StringEntity stringentity = new StringEntity(data,
                        ContentType.create("text/json", "UTF-8"));
                httppost.setEntity(stringentity);
                httpresponse = httpclient.execute(httppost);
                response = EntityUtils
                        .toString(httpresponse.getEntity());

            } finally {
                if (httpclient != null) {
                    httpclient.close();
                }
                if (httpresponse != null) {
                    httpresponse.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


//    public JSONObject doPost(String url, Map<String,Object> map, String charset){
//        HttpClient httpClient = null;
//        HttpPost httpPost = null;
//        String result = null;
//        try{
//            httpClient = new SSLClient();
//            httpPost = new HttpPost(url);
//            //设置参数
//            List<NameValuePair> list = new ArrayList<NameValuePair>();
//            Iterator iterator = map.entrySet().iterator();
//            while(iterator.hasNext()){
//                Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
//                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
//            }
//            if(list.size() > 0){
//                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
//                httpPost.setEntity(entity);
//            }
//            HttpResponse response = httpClient.execute(httpPost);
//            if(response != null){
//                HttpEntity resEntity = response.getEntity();
//                if(resEntity != null){
//                    result = EntityUtils.toString(resEntity,charset);
//                }
//            }
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return (JSONObject) JSONObject.parse(result);
//    }

}
