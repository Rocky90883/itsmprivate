package com.koron.web.systemmanger.sysConfig;

import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;

@Service
public class SysConfigService {

    /**
     * 数据库配置项
     * 根据key查value
     * @param sessionFactory
     * @param key
     * @return
     */
    @TaskAnnotation("selectValue")
    public String selectValue(SessionFactory sessionFactory, String key) {
        String value="";
        try {
            SysConfigMapper mapper = sessionFactory.getMapper(SysConfigMapper.class);
            value = mapper.selectValue(key);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return value;
    }

}
