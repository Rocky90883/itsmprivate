package com.koron.web.maintain.appsys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.koron.util.Constant;
import com.koron.web.asset.assets.AssetsService;
import com.koron.web.asset.assets.bean.AssetsBean;
import com.koron.web.maintain.appsys.bean.AppSysBean;
import com.koron.web.maintain.appsys.bean.AppSysQueryBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.springframework.web.bind.annotation.*;
import org.swan.bean.MessageBean;

import java.util.List;

@Api(tags = "IT运维-应用系统清单")
@RequestMapping("/maintain/AppSysAction")
@RestController
public class AppSysAction {

    private static Logger log = Logger.getLogger(AppSysAction.class);


    @ApiOperation("--应用系统清单-添加")
    @ResponseBody
    @PostMapping(value = "/addAppSys.htm")
    public MessageBean<?> addAppSys(@RequestBody AppSysBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {

            AssetsBean assetsbean = new AssetsBean();
            assetsbean.setAssetTypeId("db66a544045f4d47821891586967cd8e");//默认资产类型为 应用系统
            assetsbean.setGoodsModel(bean.getSysName());
            assetsbean.setCompanyId(bean.getCompanyId());
            assetsbean.setEmpId("admin");
            MessageBean assmmsg = ADOConnection.runTask(new AssetsService(), "addAssets", MessageBean.class, assetsbean);
            bean.setSourceid(assmmsg.getData().toString());     //回填台账id

            success = ADOConnection.runTask(new AppSysService(), "addAppSys",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("添加应用系统清单-异常", e);
            success.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            success.setDescription("添加应用系统清单-异常");
            return success;
        }
        return success;
    }


    @ApiOperation("--应用系统清单-列表")
    @ResponseBody
    @PostMapping(value = "/queryList.htm")
    public MessageBean<?> queryList(@RequestBody AppSysQueryBean dto){

        @SuppressWarnings("rawtypes")
        MessageBean<PageInfo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", PageInfo.class);
        try {
            PageHelper.startPage(dto.getPage(), dto.getPageCount());
            List<AppSysBean> list = ADOConnection.runTask(new AppSysService(), "queryList", List.class,dto);

            info.setData(new PageInfo<>(list));
        } catch (Exception e) {
            log.error("查询应用系统清单列表-异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("查询应用系统清单列表-异常");
            return info;
        }
        return info;
    }

    @ApiOperation("---查询详情")
    @PostMapping(value = "/detail.htm")
    public MessageBean<?> detail(@RequestParam("id") String id) {

        @SuppressWarnings("rawtypes")
        MessageBean<AppSysBean> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", AppSysBean.class);
        try {
            AppSysBean bean = ADOConnection.runTask( new AppSysService(), "detail", AppSysBean.class,id);

            info.setData(bean);
        } catch (Exception e) {
            log.error("应用系统清单-查询详情-异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("应用系统清单-查询详情-异常");
            return info;
        }
        return info;
    }

    @ApiOperation("--应用系统清单-修改")
    @ResponseBody
    @PostMapping(value = "/updateAppSys.htm")
    public MessageBean<?> updateAppSys(@RequestBody AppSysBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {

            success = ADOConnection.runTask(new AppSysService(), "updateAppSys",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("更新应用系统清单-异常", e);
            success.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            success.setDescription("更新应用系统清单-异常");
            return success;
        }
        return success;
    }

    @ApiOperation("--应用系统清单-删除")
    @ResponseBody
    @PostMapping(value = "/deleteAppSys.htm")
    public MessageBean<?> deleteAppSys(@RequestBody AppSysBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            success = ADOConnection.runTask(new AppSysService(), "deleteAppSys",MessageBean.class,bean.getId());
        } catch (Exception e) {
            log.error("删除应用系统清单-异常", e);
            success.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            success.setDescription("删除应用系统清单-异常");
            return success;
        }
        return success;
    }


    @ApiOperation("--应用系统清单下拉框")
    @ResponseBody
    @PostMapping(value = "/selectAppSysList.htm")
    public MessageBean<?> selectAppSysList(){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);;
        try {
            AppSysQueryBean queryBean = new AppSysQueryBean();
            List queryList = ADOConnection.runTask(new AppSysService(), "queryList", List.class, queryBean);
            success.setData(queryList);
        } catch (Exception e) {
            log.error("应用系统清单下拉框-异常", e);
            success.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            success.setDescription("应用系统清单下拉框-异常");
            return success;
        }
        return success;
    }
}
