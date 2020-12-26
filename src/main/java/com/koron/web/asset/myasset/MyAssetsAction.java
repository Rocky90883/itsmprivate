package com.koron.web.asset.myasset;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.asset.assets.AssetsService;
import com.koron.web.asset.assets.bean.AssetsBean;
import com.koron.web.asset.assets.bean.AssetsQueryBean;
import com.koron.web.asset.myasset.bean.MyAssetDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.springframework.web.bind.annotation.*;
import org.swan.bean.MessageBean;

import java.util.List;

@Api(tags = "IT资产-我的资产")
@RequestMapping("/asset/myAssetsAction")
@RestController
public class MyAssetsAction {

    private static Logger log = Logger.getLogger(MyAssetsAction.class);

    @ApiOperation("--我的资产-列表")
    @ResponseBody
    @PostMapping(value = "/queryList")
    public MessageBean<?> queryList(@RequestBody AssetsQueryBean queryBean){

        @SuppressWarnings("rawtypes")
        MessageBean<PageInfo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", PageInfo.class);
        try {
            if(SessionUtil.getUseerInfoCode()==null){
                return MessageBean.create(Constant.MESSAGE_INT_NOLOGIN, "未登录", List.class);
            }

            PageHelper.startPage(queryBean.getPage(), queryBean.getPageCount());
            List<AssetsBean> list = ADOConnection.runTask(new AssetsService(), "queryMyAssetList", List.class,queryBean);

            info.setData(new PageInfo<>(list));
        } catch (Exception e) {
            log.error("查询我的资产列表-异常", e);
            info.setCode(Constant.MESSAGE_INT_OPERATION);
            info.setDescription("查询我的资产列表-异常");
            return info;
        }
        return info;
    }


    @ApiOperation("--我的资产-资产-确认、接受、驳回")
    @ResponseBody
    @PostMapping(value = "/confirm")
    public MessageBean<?> addAssets(@RequestBody MyAssetDto dto){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            if(StringUtils.isBlank(dto.getAssetId())){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "资产标识不能为空", void.class);
            }

            success = ADOConnection.runTask(new AssetsService(), "assetOpt",MessageBean.class,dto);

        } catch (Exception e) {
            log.error("操作资产状态-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("操作资产状态-异常");
            return success;
        }
        return success;
    }


    @ApiOperation("--我的资产-根据人员查资产")
    @ResponseBody
    @PostMapping(value = "/queryListByEmpCode.htm")
    public MessageBean<?> queryListByEmpCode(@RequestBody AssetsQueryBean queryBean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {

//            PageHelper.startPage(queryBean.getPage(), queryBean.getPageCount());
            List<AssetsBean> list = ADOConnection.runTask(new AssetsService(), "queryList", List.class,queryBean);

            info.setData(list);
        } catch (Exception e) {
            log.error("根据人员查资产-异常", e);
            info.setCode(Constant.MESSAGE_INT_OPERATION);
            info.setDescription("根据人员查资产-异常");
            return info;
        }
        return info;
    }
}
