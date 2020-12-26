package com.koron.web.asset.scrapped;

import com.koron.web.asset.scrapped.bean.ScrappedBean;
import com.koron.web.asset.scrapped.bean.ScrappedQueryBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ScrappedMapper {

    List<ScrappedBean> queryList(ScrappedQueryBean queryBean);

    int deleteByPrimaryKey(String id);

    int insertSelective(ScrappedBean record);

    ScrappedBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScrappedBean record);

//    /**
//     * 根据用户名获取code
//     * @param codes
//     * @return
//     */
//    public List<String> getUserCode(String[] codes);

    @Select(" select case when max(RIGHT(bill_no,4)) is null then '0000' else max(RIGHT(bill_no,4)) end " +
            " from scrapped where left(CREATE_TIME,10)=left(now(),10) ")
    String maxBillNo();

    @Update(" update assets set assets_status=-1, regis_status=-1 , scrapped_id=#{scrappedId} where id =#{assetsId} ")
    int updateAssetsStatus(@Param("assetsId") String assetsId, @Param("scrappedId")String scrappedId);
}