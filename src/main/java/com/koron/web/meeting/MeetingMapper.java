package com.koron.web.meeting;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MeetingMapper {

    public List<MeetingBean> list(MeetingQueryBean queryBean);

    public Integer add(MeetingBean bean);

    @Select("SELECT count(*) FROM METTING.MEETING WHERE BILL_DATE =#{billDate,jdbcType=VARCHAR} AND TITLE=#{title,jdbcType=VARCHAR} AND SORT =#{sort,jdbcType=INTEGER} and id!=#{id,jdbcType=VARCHAR}")
    public int countbyBillDateTitletoSort(@Param("billDate")String billdate,@Param("title")String title,@Param("sort")Integer sort,@Param("id")String id);

    @Select("SELECT * FROM METTING.MEETING WHERE BILL_DATE =#{billDate,jdbcType=VARCHAR} order by BILL_DATE ,TITLE ,sort")
    public List<MeetingBean> meetingdet(@Param("billDate")String billdate);

    @Delete("delete from METTING.MEETING WHERE id=#{id,jdbcType=VARCHAR}")
    public int delete(@Param("id")String id);

    @Delete("DELETE FROM METTING.MEETING m WHERE BILL_DATE = #{billDate,jdbcType=VARCHAR} AND TITLE = #{title,jdbcType=VARCHAR}")
    public int deletebybillDateOnTitle(@Param("billDate")String billDate,@Param("title")String title);

    public Integer update(MeetingBean bean);

}
