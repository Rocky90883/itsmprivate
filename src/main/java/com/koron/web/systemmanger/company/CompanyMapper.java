package com.koron.web.systemmanger.company;

import com.koron.web.systemmanger.company.bean.CompanyBean;
import com.koron.web.systemmanger.company.bean.CompanyDto;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CompanyMapper {

    List<CompanyBean> queryList(CompanyDto dto);

    int deleteByPrimaryKey(String id);

    int insertSelective(CompanyBean bean);

    CompanyBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CompanyBean bean);

    @Select(" select case when max(RIGHT(com_number,4)) is null then '0000' else max(RIGHT(com_number,4)) end " +
            " from tblcompany where left(CREATE_TIME,10)=left(now(),10) ")
    String maxComNumber();

    List<CompanyBean> queryAllList(CompanyDto dto);
}