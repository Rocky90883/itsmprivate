//package com.koron.web.annex;
//
//import com.koron.common.ResourceComponent;
//import org.apache.tomcat.util.http.fileupload.IOUtils;
//import org.jodconverter.core.DocumentConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.net.URLEncoder;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @Author:
// * @Date: 2020/3/27 11:13
// * motto: Saying and doing are two different things.
// */
//@Controller
//public class PreviewController {
//
//    @Autowired
//    private DocumentConverter converter; // 转换器
//    @Autowired
//    private HttpServletResponse response;
//
//    private static List<String> suffixList = Arrays.asList(".pdf",".png",".xlsx",".docx",".ppt");
//
//
//    @RequestMapping("/home")
//    public String homePage(){
//        return "index";
//    }
//
//    @RequestMapping("/test")
//    public String test(){
//        return "test";
//    }
//
//    @RequestMapping("/toPdfFile")
//    public String toPdfFile(@RequestParam("typeName") String typeName, @RequestParam("fileName") String fileName, HttpServletRequest request, HttpServletResponse response){
//        try {
//            String filePath = ResourceComponent.getLocationPath(typeName,fileName);
//            if(filePath.lastIndexOf(".")==-1){
//                response.sendRedirect(request.getContextPath()+"/err.htm?msg="+ URLEncoder.encode("该文件无法阅览","UTF-8"));
//                return "";
//            }
//            String fileExists = filePath.replace(filePath.substring(filePath.lastIndexOf(".")),".pdf");
//            if(!suffixList.contains(fileExists)){
//                response.sendRedirect(request.getContextPath()+"/err.htm?msg="+ URLEncoder.encode("该文件无法阅览","UTF-8"));
//                return "";
//            }
//            //文件已经过转换直接展示
//            if(ResourceComponent.checkFileExists(fileExists)){
//                ServletOutputStream outputStream = response.getOutputStream();
//                InputStream in = new FileInputStream(new File(fileExists));// 读取文件
//                // copy文件
//                int i = IOUtils.copy(in, outputStream);
//                System.out.println(i);
//                in.close();
//                outputStream.close();
//                return "This is to pdf";
//            }
////            File file = new File("D:/transfer/assetRegist/1604993774693成都炉火神扶阳中医(单锡伟).docx");//需要转换的文件
//            File file = new File(filePath);//需要转换的文件
//
//            //文件转化
////            converter.convert(file).to(new File("D:/transfer/assetRegis/1604993774693成都炉火神扶阳中医(单锡伟).pdf")).execute();
//            converter.convert(file).to(new File(fileExists)).execute();
//            //使用response,将pdf文件以流的方式发送的前段
//            ServletOutputStream outputStream = response.getOutputStream();
////            InputStream in = new FileInputStream(new File("D:/transfer/assetRegis/1604993774693成都炉火神扶阳中医(单锡伟).pdf"));// 读取文件
//            InputStream in = new FileInputStream(new File(fileExists));// 读取文件
//            // copy文件
//            int i = IOUtils.copy(in, outputStream);
//            System.out.println(i);
//            in.close();
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "This is to pdf";
//    }
//
//}
