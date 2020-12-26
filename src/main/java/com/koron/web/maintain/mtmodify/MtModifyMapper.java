package com.koron.web.maintain.mtmodify;

import com.koron.web.maintain.mtmodify.bean.MtModifyBean;
import com.koron.web.maintain.mtmodify.bean.MtModifyQueryBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MtModifyMapper {

    int deleteByPrimaryKey(String id);

    List<MtModifyBean> queryList(MtModifyQueryBean queryBean);

    int insertSelective(MtModifyBean record);

    MtModifyBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MtModifyBean record);


    @Select(" select case when max(RIGHT(order_no,4)) is null then '0000' else max(RIGHT(order_no,4)) end " +
            " from mt_modify where left(CREATE_TIME,10)=left(now(),10) ")
    String maxOrderNo();

    @Update( "update mt_modify set assign_empid=#{assignEmpid},assign_name=#{assignName},handle_status=1 where id=#{id}" )
    void assignper(@Param("id") String id,@Param("assignEmpid") String assignEmpid,@Param("assignName")String assignName);
}