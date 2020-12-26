package com.koron.web.asset.assettype;

import com.koron.util.*;
import com.koron.web.asset.assettype.bean.AssetTypeBean;
import com.koron.web.asset.assettype.bean.AssetTypeQueryBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.springframework.web.bind.annotation.*;
import org.swan.bean.MessageBean;

import java.util.List;

@Api(tags = "IT资产-资产分类")
@RequestMapping("/asset/assetTypeAction")
@RestController
public class AssetTypeAction {

    private static Logger log = Logger.getLogger(AssetTypeAction.class);


    @ApiOperation("--添加分类")
    @ResponseBody
    @PostMapping(value = "/addassettype")
    public MessageBean<?> addassettype(@RequestBody AssetTypeBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {

            success = ADOConnection.runTask(new AssetTypeService(), "addassettype",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("添加分类异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("添加分类异常");
            return success;
        }
        return success;
    }

    @ApiOperation("--资产分类树")
    @ResponseBody
    @GetMapping(value = "/assetTypeTree")
    public MessageBean<?> assetTypeTree(){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            success = ADOConnection.runTask(new AssetTypeService(), "assetTypeTree",MessageBean.class);
        } catch (Exception e) {
            log.error("查询资产分类树-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("查询资产分类树-异常");
            return success;
        }
        return success;
    }


    @ApiOperation("--资产分类列表")
    @ResponseBody
    @PostMapping(value = "/queryList")
    public MessageBean<?> queryList(@RequestBody AssetTypeQueryBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            if(StringUtils.isEmpty(bean.getParentid())){
                bean.setParentid("");
            }

            success = ADOConnection.runTask(new AssetTypeService(), "queryList",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("查询资产分类列表-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("查询资产分类列表-异常");
            return success;
        }
        return success;
    }

    @ApiOperation("--查询资产分类上级")
    @ResponseBody
    @PostMapping(value = "/assetTypeSuperList")
    public MessageBean<?> assetTypeSuperList(@RequestBody AssetTypeQueryBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            success = ADOConnection.runTask(new AssetTypeService(), "assetTypeSuperList",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("查询资产分类上级-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("查询资产分类上级-异常");
            return success;
        }
        return success;
    }

    @ApiOperation("--更新资产分类")
    @ResponseBody
    @PostMapping(value = "/updateAssetType")
    public MessageBean<?> updateAssetType(@RequestBody AssetTypeBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            if (StringUtils.isBlank(bean.getId())) {
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "非法参数", void.class);
            }

            success = ADOConnection.runTask(new AssetTypeService(), "updateAssetType",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("更新资产分类-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("更新资产分类-异常");
            return success;
        }
        return success;
    }

    @ApiOperation("--删除资产分类")
    @ResponseBody
    @PostMapping(value = "/deleteAssetType")
    public MessageBean<?> deleteAssetType(@RequestBody AssetTypeBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            success = ADOConnection.runTask(new AssetTypeService(), "deleteAssetType",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("删除资产分类-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("删除资产分类-异常");
            return success;
        }
        return success;
    }


    @ApiOperation("--分类选择下拉框")
    @ResponseBody
    @PostMapping(value = "/selectAssetTypeList")
    public MessageBean<?> selectAssetTypeList(@RequestParam("type") Integer type){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = null;
        try {
            success = ADOConnection.runTask(new AssetTypeService(), "selectAssetTypeList",MessageBean.class,type);
        } catch (Exception e) {
            log.error("分类选择下拉框-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("分类选择下拉框-异常");
            return success;
        }
        return success;
    }

}
