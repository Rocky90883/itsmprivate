package com.koron.common.web.service;

import com.koron.common.web.bean.DataSourceBean;
import com.koron.common.web.mapper.DataSourceMapper;
import com.koron.util.Constant;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOSessionImpl;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.swan.bean.MessageBean;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class DataSourceService {

    private static final Logger log = Logger.getLogger(DataSourceService.class);

    /**
     * 初始化所有动态数据源（当系统重启时）
     * @param sessionFactory
     * @return
     */
    @SuppressWarnings("rawtypes")
    @TaskAnnotation("initDataSources")
    private MessageBean<?> initDataSources(SessionFactory sessionFactory, Integer status) {
        MessageBean<HashMap> msg = null;
        HashMap<String, Object> map = new HashMap<>();
        DataSourceMapper mapper = sessionFactory.getMapper(DataSourceMapper.class);
        //查询动态数据库
        List<DataSourceBean> beans = mapper.getDataSourceByStatus(status);
        if(beans!=null){
            int i=0,j=0;
            for(DataSourceBean bean:beans){
                map = startUp(sessionFactory,bean);
                Boolean result =(Boolean)map.get("result");
                String info =(String)map.get("info");
                if(result){
                    log.info(info);
                    i++;
                }else{
                    log.info(info);
                    j++;
                }
            }
            String info ="[" +i+ "]个数据源启动成功;[" +j+ "]个数据源启动失败";
            map.put("info", info);
            msg =MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "数据源信息", HashMap.class);
            msg.setData(map);
        }
        return msg;
    }

    /**
     * 启动数据源信息
     * @date 2018年10月15日
     * @param bean
     * @return
     */
    public static HashMap<String, Object> startUp(SessionFactory factory,DataSourceBean bean){
        String url =null;
        //校验信息
        HashMap<String, Object> map = checkDataSource(bean);
        Boolean result =(Boolean)map.get("result");
        if(bean.getDriverClassName().equalsIgnoreCase("oracle")){
            bean.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            url = "jdbc:oracle:thin:@"+bean.getDbHost()+":"+bean.getDbPort()+":"+bean.getDbName();
        }else{
            //默认设置为mysql数据库
            bean.setDriverClassName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://"+bean.getDbHost()+":"+bean.getDbPort()+"/"+bean.getDbName()+"?useSSL=false&allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8";
        }
        if(result){
            //配置新的数据源
            Properties p = new Properties();
            p.setProperty(SessionFactory.PROPERTY_DRIVER, bean.getDriverClassName());
            p.setProperty(SessionFactory.PROPERTY_URL, url);
            p.setProperty(SessionFactory.PROPERTY_USER, bean.getDbUser());
            p.setProperty(SessionFactory.PROPERTY_PASSWORD, bean.getDbPassword());
            p.setProperty(SessionFactory.PROPERTY_MAXIDLE, "10");

            //注册新的数据源
            new ADOSessionImpl().registeDBMap(bean.getId(), p);

            //校验数据源是否启动成功
            Boolean connect = checkDataSource(factory,bean.getId());
            if(connect){
                map.put("info", "数据库["+bean.getName()+"("+bean.getId()+")"+"]启动成功");
            }else{
                map.put("info", "数据库["+bean.getName()+"("+bean.getId()+")"+"]启动失败");
                map.put("result", connect);
            }

        }
        return map;
    }


    /**
     * 校验数据源是否启动成功(仅仅校验了Mysql数据库)
     * @param factory
     * @param DBname
     * @return
     */
    private static Boolean checkDataSource(SessionFactory factory, String DBname){
        DataSourceMapper mapper = factory.getMapper(DataSourceMapper.class,DBname);
        try {
            String dateTime = mapper.checkDataSource();
            factory.close();
            if(dateTime!=null){
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            factory.close(false);
            return false;
        }
    }

    /**
     * 数据源信息校验
     * @param bean
     * @return
     */
    private static HashMap<String, Object> checkDataSource(DataSourceBean bean){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("result", false);
        if(StringUtils.isBlank(bean.getDriverClassName())){
            map.put("info", "数据库驱动不能为空");
        }else if(StringUtils.isBlank(bean.getDbName())){
            map.put("info", "数据库名不能为空");
        }else if(StringUtils.isBlank(bean.getDbPort())){
            map.put("info", "数据库端口不能为空");
        }else if(StringUtils.isBlank(bean.getDbUser())){
            map.put("info", "数据库账户不能为空");
        }else if(StringUtils.isBlank(bean.getDbPassword())){
            map.put("info", "数据库密码不能为空");
        }else if(StringUtils.isBlank(bean.getMark())){
            map.put("info", "数据库标识不能为空");
        }else {
            map.put("result", true);
            map.put("info", "验证通过");
        }
        return map;
    }

}
