package com.koron.web.asset.assetportion;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.koron.util.Constant;
import com.koron.web.asset.assetportion.bean.AssetPortionBean;
import com.koron.web.asset.assetportion.bean.AssetPortionQueryBean;
import com.koron.web.asset.assetregist.bean.AssetRegistBean;
import com.koron.web.asset.shift.AssetShiftService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.springframework.web.bind.annotation.*;
import org.swan.bean.MessageBean;

import java.util.List;

@Api(tags = "IT资产-资产分配")
@RequestMapping("/asset/assetsPortionAction")
@RestController
public class AssetsPortionAction {

    private static Logger log = Logger.getLogger(AssetsPortionAction.class);


//    @ApiOperation("--资产分配-分配至部门")
//    @ResponseBody
//    @PostMapping(value = "/addPortion")
//    public MessageBean<?> addPortion(@RequestBody AssetPortionBean bean){
//
//        @SuppressWarnings("rawtypes")
//        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
//        try {
//            if(StringUtils.isBlank(bean.getRegisterId())){
//                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "资产登记id不能为空", void.class);
//            }
//            if(StringUtils.isBlank(bean.getDepatCode())){
//                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "分配部门不能为空", void.class);
//            }
//            if(StringUtils.isBlank(bean.getBillDate())){
//                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "分配日期不能为空", void.class);
//            }
//            if(bean.getPortionQty()<=0){
//                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "分配数量需大于零", void.class);
//            }
//            success = ADOConnection.runTask(new AssetPortionService(), "addPortion",MessageBean.class,bean);
//        } catch (Exception e) {
//            log.error("添加资产分配-异常", e);
//            success.setCode(Constant.MESSAGE_INT_OPERATION);
//            success.setDescription("添加资产分配-异常");
//            return success;
//        }
//        return success;
//    }


    @ApiOperation("--资产分配-列表")
    @ResponseBody
    @PostMapping(value = "/queryList")
    public MessageBean<?> queryList(@RequestBody AssetPortionQueryBean queryBean){

        @SuppressWarnings("rawtypes")
        MessageBean<PageInfo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", PageInfo.class);
        try {
            PageHelper.startPage(queryBean.getPage(), queryBean.getPageCount());
            List<AssetPortionBean> list = ADOConnection.runTask(new AssetPortionService(), "queryList", List.class,queryBean);

            info.setData(new PageInfo<>(list));
        } catch (Exception e) {
            log.error("查询资产分配列表-异常", e);
            info.setCode(Constant.MESSAGE_INT_OPERATION);
            info.setDescription("查询资产分配列表-异常");
            return info;
        }
        return info;
    }

    @ApiOperation("---查询详情")
    @PostMapping(value = "/detail.htm")
    public MessageBean<?> detail(@RequestParam("id") String id) {

        @SuppressWarnings("rawtypes")
        MessageBean<AssetPortionBean> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", AssetPortionBean.class);
        try {

            AssetPortionBean bean = ADOConnection.runTask( new AssetShiftService(), "detail", AssetPortionBean.class,id);

            info.setData(bean);
        } catch (Exception e) {
            log.error("资产分配-查询详情-异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("资产分配-查询详情-异常");
            return info;
        }
        return info;
    }

    @ApiOperation("--资产分配-修改")
    @ResponseBody
    @PostMapping(value = "/updatePortion")
    public MessageBean<?> updatePortion(@RequestBody AssetPortionBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {

            if(StringUtils.isBlank(bean.getId())){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "资产分配id不能为空", void.class);
            }
            if(StringUtils.isBlank(bean.getRegisterId())){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "资产登记id不能为空", void.class);
            }
            if(StringUtils.isBlank(bean.getDepatCode())){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "分配部门不能为空", void.class);
            }
            if(StringUtils.isBlank(bean.getBillDate())){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "分配日期不能为空", void.class);
            }
            if(bean.getPortionQty()<=0){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "分配数量需大于零", void.class);
            }
            success = ADOConnection.runTask(new AssetPortionService(), "updatePortion",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("更新资产分配-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("更新资产分配-异常");
            return success;
        }
        return success;
    }

    @ApiOperation("--资产分配-删除")
    @ResponseBody
    @PostMapping(value = "/deletePortion")
    public MessageBean<?> deletePortion(@RequestBody AssetPortionBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            success = ADOConnection.runTask(new AssetPortionService(), "deletePortion",MessageBean.class,bean.getId());
        } catch (Exception e) {
            log.error("删除资产分配-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("删除资产分配-异常");
            return success;
        }
        return success;
    }

}
