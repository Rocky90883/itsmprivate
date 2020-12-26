package com.koron.main;

import javax.sql.DataSource;

import org.koron.ebs.mybatis.ADOSessionImpl;
import org.koron.ebs.mybatis.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DataSourceConfig {
	@Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.default")
    public DataSource primaryDataSource() {
        DataSource ds =  DataSourceBuilder.create().build();
		ADOSessionImpl session = new ADOSessionImpl();
		//开启/关闭-自动转换驼峰标识
		SessionFactory.setMapUnderscoreToCamelCase(true);
        session.addInterceptor("_default", "com.koron.common.authentication.PageInterceptor");
//        session.addInterceptor("_default", "com.github.pagehelper.PageInterceptor");
		session.registeDataSourceMap("_default", ds);
        return ds;
    }

    @Bean(name = "itsmcommonDataSource")
    @Qualifier("itsmcommonDataSource")
    @ConfigurationProperties(prefix="spring.datasource.common")
    public DataSource secodnaryDataSource() {
        DataSource ds =  DataSourceBuilder.create().build();
        ADOSessionImpl session = new ADOSessionImpl();
        //开启/关闭-自动转换驼峰标识
        SessionFactory.setMapUnderscoreToCamelCase(true);
//        session.addInterceptor("_common", "com.koron.common.authentication.PageInterceptor");
        session.registeDataSourceMap("_common", ds);
        return ds;
    }

}