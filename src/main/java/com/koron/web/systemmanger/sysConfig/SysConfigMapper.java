package com.koron.web.systemmanger.sysConfig;

import org.apache.ibatis.annotations.Select;

public interface SysConfigMapper {

    @Select("select `value` from sys_config where `key`=#{key}")
    String selectValue(String key);

}
