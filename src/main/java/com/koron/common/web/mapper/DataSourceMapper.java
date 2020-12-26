package com.koron.common.web.mapper;

import com.koron.common.web.bean.DataSourceBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DataSourceMapper {

    /**
     * 根据状态查询数据源信息
     * @param status 1启用，0停用
     * @return
     */
    @Select( " SELECT * FROM datasource a WHERE `status`=#{status} " )
    public List<DataSourceBean> getDataSourceByStatus(@Param("status") Integer status);

    /**
     * 校验数据库是否连接成功
     * @date 2018年11月6日
     * @return
     */
    @Select( "SELECT NOW()" )
    public String checkDataSource();
}
