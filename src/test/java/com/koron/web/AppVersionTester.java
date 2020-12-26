//package com.koron.web;
//
//import com.koron.web.app.AppBean;
//import com.koron.web.app.AppQueryBean;
//import com.koron.web.app.AppService;
//import com.koron.web.app.AppServiceImpl;
//import com.koron.web.app.version.AppVersionBean;
//import com.koron.web.app.version.AppVersionQueryBean;
//import com.koron.web.app.version.AppVersionService;
//import com.koron.web.app.version.AppVersionServiceImpl;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runners.MethodSorters;
//import org.koron.ebs.mybatis.ADOConnection;
//import org.koron.ebs.mybatis.ADOSessionImpl;
//import org.koron.ebs.mybatis.EnvSource;
//import org.koron.ebs.mybatis.SessionFactory;
//import org.swan.bean.MessageBean;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class AppVersionTester {
//    private AppBean appBean;
//
//    @Before
//    public void init() {
//        Properties p = new Properties();
//        p.put(SessionFactory.PROPERTY_URL, "jdbc:postgresql://localhost/lc");
//        p.put(SessionFactory.PROPERTY_DRIVER, "org.postgresql.Driver");
//        p.put(SessionFactory.PROPERTY_USER, "postgres");
//        p.put(SessionFactory.PROPERTY_PASSWORD, "123456");
//        SessionFactory.setMapUnderscoreToCamelCase(true);
//        new ADOSessionImpl().registeDBMap(EnvSource.DEFAULT, p);
//        AppServiceImpl service = new AppServiceImpl();
//        AppQueryBean queryb = new AppQueryBean();
//        MessageBean<Map> msg = ADOConnection.runTask(sessionFactory -> service.list(sessionFactory, queryb), MessageBean.class);
//        if (msg.getCode() == 0)
//            this.appBean = (AppBean) ((List) msg.getData().get("list")).get(0);
//        System.out.println(appBean);
//    }
//
//    @Test
//    public void a_testAdd() {
//        AppVersionServiceImpl service = new AppVersionServiceImpl();
//        MessageBean v1 = ADOConnection.runTask(sessionFactory -> service.add(sessionFactory, appBean.getCode(), "1.0.0"), MessageBean.class);
//        MessageBean v2 = ADOConnection.runTask(sessionFactory -> service.add(sessionFactory, appBean.getCode(), "1.0.1"), MessageBean.class);
//        MessageBean v3 = ADOConnection.runTask(sessionFactory -> service.add(sessionFactory, appBean.getCode(), "1.0.2"), MessageBean.class);
//        MessageBean v4 = ADOConnection.runTask(sessionFactory -> service.add(sessionFactory, appBean.getCode(), "1.0.0"), MessageBean.class);
//        MessageBean v5 = ADOConnection.runTask(sessionFactory -> service.add(sessionFactory, appBean.getCode() + "2233", "1.0.3"), MessageBean.class);
//        System.out.println(v1);
//        System.out.println(v2);
//        System.out.println(v3);
//        System.out.println(v4);
//        System.out.println(v5);
//        Assert.assertEquals("添加1.0.0", 0, v1.getCode());
//        Assert.assertEquals("添加1.0.1", 0, v2.getCode());
//        Assert.assertEquals("添加1.0.2", 0, v3.getCode());
//        Assert.assertEquals("重复添加1.0.0", AppVersionService.MSG_VERSION_EXISTS.getCode(), v4.getCode());
//        Assert.assertEquals("应用不存在", AppService.MSG_APP_NOT_EXIST.getCode(), v5.getCode());
//    }
//
//    @Test
//    public void b_test() {
//        AppVersionServiceImpl service = new AppVersionServiceImpl();
//        AppVersionQueryBean queryBean = new AppVersionQueryBean().setApp(appBean.getCode());
//        MessageBean list1 = ADOConnection.runTask(sessionFactory -> service.list(sessionFactory, queryBean), MessageBean.class);
//        queryBean.setVersion("1.0.0");
//        MessageBean list2 = ADOConnection.runTask(sessionFactory -> service.list(sessionFactory, queryBean), MessageBean.class);
//        queryBean.setApp(appBean.getCode() + "222");
//        MessageBean list3 = ADOConnection.runTask(sessionFactory -> service.list(sessionFactory, queryBean), MessageBean.class);
//        System.out.println(list1);
//        System.out.println(list2);
//        System.out.println(list3);
//        Assert.assertEquals("查询所以版本", 0, list1.getCode());
//        Assert.assertEquals("查询1.0.0", 0, list2.getCode());
//        Assert.assertEquals("查询不存在的app Code下的版本", AppService.MSG_APP_NOT_EXIST.getCode(), list3.getCode());
//        List<AppVersionBean> versions = ((List) ((Map) list2.getData()).get("list"));
//        MessageBean v1 = ADOConnection.runTask(sessionFactory -> service.update(sessionFactory, versions.get(0).getCode(), "1.0.1"), MessageBean.class);
//        MessageBean v2 = ADOConnection.runTask(sessionFactory -> service.update(sessionFactory, versions.get(0).getCode() + "dd", "1.0.4"), MessageBean.class);
//        MessageBean v3 = ADOConnection.runTask(sessionFactory -> service.update(sessionFactory, versions.get(0).getCode(), "1.0.5"), MessageBean.class);
//        System.out.println(v1);
//        System.out.println(v2);
//        System.out.println(v3);
//        Assert.assertEquals("修改成1.0.1", AppVersionService.MSG_VERSION_EXISTS.getCode(), v1.getCode());
//        Assert.assertEquals("不存在的版本code", AppVersionService.MSG_VERSION_NOT_EXISTS.getCode(), v2.getCode());
//        Assert.assertEquals("修改成1.0.5", 0, v3.getCode());
//
//        queryBean.setApp(null);
//        MessageBean list4 = ADOConnection.runTask(sessionFactory -> service.list(sessionFactory, queryBean), MessageBean.class);
//        List<AppVersionBean> list = (List) ((Map) list4.getData()).get("list");
//        for (AppVersionBean versionBean : list) {
//            MessageBean d1 = ADOConnection.runTask(sessionFactory -> service.delete(sessionFactory, versionBean.getCode()), MessageBean.class);
//            System.out.println(d1);
//            Assert.assertEquals("删除"+versionBean.getVersion(), 0, d1.getCode());
//            MessageBean d2 = ADOConnection.runTask(sessionFactory -> service.delete(sessionFactory, versionBean.getCode()+"ddds"), MessageBean.class);
//            System.out.println(d2);
//            Assert.assertEquals("删除不存在的版本", AppVersionService.MSG_VERSION_NOT_EXISTS.getCode(), d2.getCode());
//        }
//    }
//}
