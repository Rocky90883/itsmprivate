package com.koron.web.asset.assetregist;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.asset.assetportion.AssetPortionService;
import com.koron.web.asset.assetportion.bean.AssetPortionBean;
import com.koron.web.asset.assetregist.bean.AssetRegistBean;
import com.koron.web.asset.assetregist.bean.AssetRegistQueryBean;
import com.koron.web.asset.scrapped.bean.ScrappedBean;
import com.koron.web.asset.shift.AssetShiftService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.springframework.web.bind.annotation.*;
import org.swan.bean.MessageBean;

import java.util.List;

@Api(tags = "IT资产-资产登记")
@RequestMapping("/asset/assetRegistAction")
@RestController
public class AssetRegistAction {

    private static Logger log = Logger.getLogger(AssetRegistAction.class);


    @ApiOperation("--资产登记-添加")
    @ResponseBody
    @PostMapping(value = "/addRegist")
    public MessageBean<?> addRegist(@RequestBody AssetRegistBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<String> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", String.class);
        try {
            if(StringUtils.isBlank(bean.getAssetTypeId())){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "分类id不能为空", void.class);
            }
            if(StringUtils.isBlank(bean.getGoodsModel())){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "资产型号不能为空", void.class);
            }
            if(StringUtils.isBlank(bean.getContractNo())){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "登记合同号不能为空", void.class);
            }
            if(bean.getQty()<=0){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "登记数量需要大于0", void.class);
            }

            success = ADOConnection.runTask(new AssetRegistService(), "addRegist",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("添加资产登记-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("添加资产登记-异常");
            return success;
        }
        return success;
    }


    @ApiOperation("--资产登记-列表")
    @ResponseBody
    @PostMapping(value = "/queryList")
    public MessageBean<?> queryList(@RequestBody AssetRegistQueryBean queryBean){

        @SuppressWarnings("rawtypes")
        MessageBean<PageInfo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", PageInfo.class);
        try {
            PageHelper.startPage(queryBean.getPage(), queryBean.getPageCount());
            List<AssetRegistBean> list = ADOConnection.runTask(new AssetRegistService(), "queryList", List.class,queryBean);

//            System.out.println(SessionUtil.getUseerInfoCode()+"你好.");
            info.setData(new PageInfo<>(list));
        } catch (Exception e) {
            log.error("查询资产登记列表-异常", e);
            info.setCode(Constant.MESSAGE_INT_OPERATION);
            info.setDescription("查询资产登记列表-异常");
            return info;
        }
        return info;
    }

    @ApiOperation("---查询详情")
    @PostMapping(value = "/detail.htm")
    public MessageBean<?> detail(@RequestParam("id") String id) {

        @SuppressWarnings("rawtypes")
        MessageBean<AssetRegistBean> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", AssetRegistBean.class);
        try {

            AssetRegistBean bean = ADOConnection.runTask( new AssetRegistService(), "detail", AssetRegistBean.class,id);

            info.setData(bean);
        } catch (Exception e) {
            log.error("资产登记列-查询详情-异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("资产登记列-查询详情-异常");
            return info;
        }
        return info;
    }


    @ApiOperation("--资产登记-修改")
    @ResponseBody
    @PostMapping(value = "/updateRegist")
    public MessageBean<?> updateRegist(@RequestBody AssetRegistBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {

            success = ADOConnection.runTask(new AssetRegistService(), "updateRegist",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("更新资产登记-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("更新资产登记-异常");
            return success;
        }
        return success;
    }

    @ApiOperation("--资产登记-删除")
    @ResponseBody
    @PostMapping(value = "/deleteRegist")
    public MessageBean<?> deleteRegist(@RequestBody AssetRegistBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            success = ADOConnection.runTask(new AssetRegistService(), "deleteRegist",MessageBean.class,bean.getId());
        } catch (Exception e) {
            log.error("删除资产登记-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("删除资产登记-异常");
            return success;
        }
        return success;
    }

    @ApiOperation("--资产分配-分配至部门")
    @ResponseBody
    @PostMapping(value = "/addPortion")
    public MessageBean<?> addPortion(@RequestBody AssetPortionBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
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
            success = ADOConnection.runTask(new AssetPortionService(), "addPortion",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("添加资产分配-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("添加资产分配-异常");
            return success;
        }
        return success;
    }
}
