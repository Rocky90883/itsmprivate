package com.koron.web.maintain.release;

import com.koron.web.maintain.release.bean.MtReleaseBean;
import com.koron.web.maintain.release.bean.MtReleaseQueryBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MtReleaseMapper {

    int deleteByPrimaryKey(String id);

    List<MtReleaseBean> queryList(MtReleaseQueryBean queryBean);

    int insertSelective(MtReleaseBean record);

    MtReleaseBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MtReleaseBean record);


    @Select(" select case when max(RIGHT(order_no,4)) is null then '0000' else max(RIGHT(order_no,4)) end " +
            " from mt_release where left(CREATE_TIME,10)=left(now(),10) ")
    String maxOrderNo();

    @Update( "update mt_release set assign_empid=#{assignEmpid},assign_name=#{assignName},handle_status=1 where id=#{id}" )
    void assignper(@Param("id") String id, @Param("assignEmpid") String assignEmpid, @Param("assignName")String assignName);

}