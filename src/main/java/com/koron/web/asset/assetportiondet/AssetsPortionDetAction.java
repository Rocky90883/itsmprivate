package com.koron.web.asset.assetportiondet;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.asset.assetportiondet.bean.AssetPortionDetBean;
import com.koron.web.asset.assetportiondet.bean.AssetPortionDetQueryBean;
import com.koron.web.systemmanger.user.bean.StaffunQueryBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.springframework.web.bind.annotation.*;
import org.swan.bean.MessageBean;

import java.util.List;

@Api(tags = "IT资产-资产分配明细")
@RequestMapping("/asset/assetsPortionDetAction")
@RestController
public class AssetsPortionDetAction {

    private static Logger log = Logger.getLogger(AssetsPortionDetAction.class);


    @ApiOperation("--资产分配明细-添加")
    @ResponseBody
    @PostMapping(value = "/addPortionDet")
    public MessageBean<?> addPortionDet(@RequestBody AssetPortionDetBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            if(StringUtils.isEmpty(bean.getfRef())){
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "fRef主单据标识不能为空", void.class);
            }
            if(StringUtils.isEmpty(bean.getEmpId())){
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "被分配人员不能为空", void.class);
            }

            success = ADOConnection.runTask(new AssetPortionDetService(), "addPortionDet",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("添加资产台账-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("添加资产台账-异常");
            return success;
        }
        return success;
    }


    @ApiOperation("--资产分配明细-列表")
    @ResponseBody
    @PostMapping(value = "/queryList")
    public MessageBean<?> queryList(@RequestBody AssetPortionDetQueryBean queryBean){

        @SuppressWarnings("rawtypes")
        MessageBean<PageInfo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", PageInfo.class);
        try {
            if(StringUtils.isEmpty(queryBean.getfRef())){
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "fRef主单据标识不能为空", void.class);
            }
            PageHelper.startPage(queryBean.getPage(), queryBean.getPageCount());
            List<AssetPortionDetBean> list = ADOConnection.runTask(new AssetPortionDetService(), "queryList", List.class,queryBean);

            info.setData(new PageInfo<>(list));
        } catch (Exception e) {
            log.error("查询资产台账列表-异常", e);
            info.setCode(Constant.MESSAGE_INT_OPERATION);
            info.setDescription("查询资产台账列表-异常");
            return info;
        }
        return info;
    }

//    @ApiOperation("--资产分配明细-选人")
//    @ResponseBody
//    @PostMapping(value = "/queryStaffunbydeptCode")
//    public MessageBean<?> queryStaffunbydeptCode(@RequestBody StaffunQueryBean queryBean){
//
//        @SuppressWarnings("rawtypes")
//        MessageBean<PageInfo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", PageInfo.class);
//        try {
//            if(SessionUtil.getUseerInfoCode()==null){
//                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "用户未登录", PageInfo.class);
//            }
//            PageHelper.startPage(queryBean.getPage(), queryBean.getPageCount());
//            List<AssetPortionDetBean> list = ADOConnection.runTask(new AssetPortionDetService(), "queryStaffunbydeptCode", List.class,queryBean);
//
//            info.setData(new PageInfo<>(list));
//        } catch (Exception e) {
//            log.error("查询资产台明细选人-异常", e);
//            info.setCode(Constant.MESSAGE_INT_OPERATION);
//            info.setDescription("查询资产台明细选人-异常");
//            return info;
//        }
//        return info;
//    }


    @ApiOperation("--资产分配明细-修改")
    @ResponseBody
    @PostMapping(value = "/updatePortionDet")
    public MessageBean<?> updatePortionDet(@RequestBody AssetPortionDetBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {

            success = ADOConnection.runTask(new AssetPortionDetService(), "updatePortionDet",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("更新资产台账-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("更新资产台账-异常");
            return success;
        }
        return success;
    }

    @ApiOperation("--资产分配明细-删除")
    @ResponseBody
    @PostMapping(value = "/deletePortionDet")
    public MessageBean<?> deletePortionDet(@RequestBody AssetPortionDetBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            if (StringUtils.isEmpty(bean.getId())) {
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "明细分配标识不能为空", List.class);
            }

            success = ADOConnection.runTask(new AssetPortionDetService(), "deletePortionDet",MessageBean.class,bean.getId());
        } catch (Exception e) {
            log.error("删除资产台账-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("删除资产台账-异常");
            return success;
        }
        return success;
    }






}
