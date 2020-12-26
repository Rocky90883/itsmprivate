//package com.koron.web;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.koron.ebs.mybatis.*;
//
//import java.util.Properties;
//
//public class AppCategoryTest {
//    @Before
//    public void init(){
//        Properties p = new Properties();
//        p.put(SessionFactory.PROPERTY_DRIVER,"jdbc:postgresql://localhost/lc");
//        p.put(SessionFactory.PROPERTY_DRIVER,"org.postgresql.Driver");
//        p.put(SessionFactory.PROPERTY_USER,"postgres");
//        p.put(SessionFactory.PROPERTY_PASSWORD,"123456");
//        SessionFactory.setMapUnderscoreToCamelCase(true);
//        new ADOSessionImpl().registeDBMap(EnvSource.DEFAULT,p);
//    }
//
//    @Test
//    public void addAppCategory(){
//
//    }
//}
