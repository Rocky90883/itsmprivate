package com.koron.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletResponse;

public class HttpUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static String getMessageBean(String param,String url){

        StringBuilder sb = new StringBuilder();
        InputStream is = null;
        BufferedReader br = null;
        PrintWriter out = null;
        try {
            //接口地址
            URL    uri = new URL(url+"?"+param);
            HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(10000);
            connection.setRequestProperty("accept", "*/*");
//	        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:39.0) Gecko/20100101 Firefox/39.0");
            //发送参数
            connection.setDoOutput(true);
            connection.setDoInput(true);
            out = new PrintWriter(connection.getOutputStream());
            out.flush();
            //接收结果
            is = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            //缓冲逐行读取
            while ( ( line = br.readLine() ) != null ) {
                sb.append(line);
            }
//	        System.out.println(sb.toString());
        } catch ( Exception ignored ) {

            ignored.printStackTrace();

        } finally {
            //关闭流
            try {
                if(is!=null){
                    is.close();
                }
                if(br!=null){
                    br.close();
                }
                if (out!=null){
                    out.close();
                }
            } catch ( Exception ignored ) {}
        }
        return sb.toString();
    }
    /**
     * httpClient get 请求
     */
    public static String getData(Map<String, String> params, String url){
        //构造HttpClient的实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String entityStr = null;
        CloseableHttpResponse response = null;
        try{
            URIBuilder uriBuilder = new URIBuilder(url);
            for(String key:params.keySet()){
                uriBuilder.addParameter(key, params.get(key));
            }
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            //设置头信息：如果不设置User-Agent可能会报405，导致取不到数据
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:39.0) Gecko/20100101 Firefox/39.0");
            //使用系统提供的默认的恢复策略
            httpGet.addHeader("Content-Type", "text/html");
            //开始执行getMethod
            response = httpClient.execute(httpGet);
            // 获得响应的实体对象
            HttpEntity entity = response.getEntity();
            // 使用Apache提供的工具类进行转换成字符串
            entityStr = EntityUtils.toString(entity, "UTF-8");
//	        logger.debug(entityStr);
        }catch (HttpException e){
            //发生异常，可能是协议不对或者返回的内容有问题
            entityStr = "lose";
        }catch(IOException e){
            //发生网络异常
            entityStr = "lose";
        } catch (URISyntaxException e) {
            entityStr = "lose";
        }finally{
            //释放连接
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
    /**
     * httpClient get 请求(当存在数组参数时)
     */
    public static String getData2(HashMap<String, String> params,String url,String[] codes,String name){
        //构造HttpClient的实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String entityStr = null;
        CloseableHttpResponse response = null;
        try{
            URIBuilder uriBuilder = new URIBuilder(url);
            for(String key:params.keySet()){
                uriBuilder.addParameter(key, params.get(key));
            }
            if(codes!=null&&codes.length>0){
                for(int i=0;i<codes.length;i++){
                    uriBuilder.addParameter(name,codes[i]);
                }
            }
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            //设置头信息：如果不设置User-Agent可能会报405，导致取不到数据
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:39.0) Gecko/20100101 Firefox/39.0");
            //使用系统提供的默认的恢复策略
            httpGet.addHeader("Content-Type", "text/html");
            //开始执行getMethod
            response = httpClient.execute(httpGet);
            // 获得响应的实体对象
            HttpEntity entity = response.getEntity();
            // 使用Apache提供的工具类进行转换成字符串
            entityStr = EntityUtils.toString(entity, "UTF-8");
//			logger.debug(entityStr);
        }catch (HttpException e){
            //发生异常，可能是协议不对或者返回的内容有问题
            entityStr = "lose";
        }catch(IOException e){
            //发生网络异常
            entityStr = "lose";
        } catch (URISyntaxException e) {
            entityStr = "lose";
        }finally{
            //释放连接
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
    public static String sendPost(String url, String param, String description) {
        // TODO Auto-generated method stub
        return null;
    }


    /**
     * 下载
     * @param response
     * @param file
     * @throws Exception
     */
    public static void downLoadCommon(HttpServletResponse response, File file, String filename) throws Exception {
        filename = new String(filename.getBytes("utf-8"),"ISO8859-1");
        response.setContentType("text/html;charset=utf-8");
        // response设置Content-Disposition
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        FileInputStream fis = new FileInputStream(file);
        byte[] buff = new byte[2048];
        int len = 0;
        OutputStream os = response.getOutputStream();
        while ((len = fis.read(buff)) > 0) {
            os.write(buff, 0, len);
        }
        os.close();
        fis.close();
    }

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将json结果集转化为对象
     *
     * @param jsonData
     *            json数据
     * @param beanType
     *            对象中的object类型
     * @return
     * @throws Exception
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) throws Exception {
        T t = MAPPER.readValue(jsonData, beanType);
        return t;
    }

}

