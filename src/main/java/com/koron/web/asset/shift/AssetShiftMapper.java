package com.koron.web.asset.shift;

import com.koron.web.asset.scrapped.bean.ScrappedBean;
import com.koron.web.asset.scrapped.bean.ScrappedQueryBean;
import com.koron.web.asset.shift.bean.AssetShiftBean;
import com.koron.web.asset.shift.bean.AssetShiftQueryBean;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AssetShiftMapper {

    int deleteByPrimaryKey(String id);

    List<AssetShiftBean> queryList(AssetShiftQueryBean queryBean);

    int insertSelective(AssetShiftBean record);

    AssetShiftBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AssetShiftBean record);

    @Select(" select case when max(RIGHT(bill_no,4)) is null then '0000' else max(RIGHT(bill_no,4)) end " +
            " from scrapped where left(CREATE_TIME,10)=left(now(),10) ")
    String maxBillNo();

    @Update(" update assets set emp_id=#{empId} , depat_code=#{depatcode}, ass_shift_id=#{id}  where id = #{sourceId} " )
    int updateAssetsEmpId(AssetShiftBean bean);




}