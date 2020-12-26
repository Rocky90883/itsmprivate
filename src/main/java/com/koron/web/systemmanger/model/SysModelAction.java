package com.koron.web.systemmanger.model;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.koron.util.Constant;
import com.koron.web.systemmanger.model.bean.ModelTreeBean;
import com.koron.web.systemmanger.model.bean.SysModelBean;
import com.koron.web.systemmanger.model.bean.SysModelQueryBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.springframework.web.bind.annotation.*;
import org.swan.bean.MessageBean;

import java.util.List;

@Api(tags = "系统管理-模块菜单")
@RequestMapping("/systemmanger/sysModelAction")
@RestController
public class SysModelAction {

    private static Logger log = Logger.getLogger(SysModelAction.class);


    @ApiOperation("--模块菜单-添加")
    @ResponseBody
    @PostMapping(value = "/addSysModel.htm")
    public MessageBean<?> addSysModel(@RequestBody SysModelBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            if(StringUtils.isBlank(bean.getModelName())){
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "模块菜单名不能空", List.class);
            }
//            if(StringUtils.isBlank(bean.getUrl())){
//                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "请求地址不能空", List.class);
//            }
            success = ADOConnection.runTask(new SysModelService(), "addSysModel",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("添加模块菜单-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("添加模块菜单-异常");
            return success;
        }
        return success;
    }


    @ApiOperation("--模块菜单-列表")
    @ResponseBody
    @PostMapping(value = "/queryList.htm")
    public MessageBean<?> queryList(@RequestBody SysModelQueryBean queryBean){

        @SuppressWarnings("rawtypes")
        MessageBean<PageInfo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", PageInfo.class);
        try {
            PageHelper.startPage(queryBean.getPage(), queryBean.getPageCount());
            List<SysModelBean> list = ADOConnection.runTask(new SysModelService(), "queryList", List.class,queryBean);

            info.setData(new PageInfo<>(list));
        } catch (Exception e) {
            log.error("查询模块菜单列表-异常", e);
            info.setCode(Constant.MESSAGE_INT_OPERATION);
            info.setDescription("查询模块菜单列表-异常");
            return info;
        }
        return info;
    }
    

    @ApiOperation("--模块菜单-修改")
    @ResponseBody
    @PostMapping(value = "/updateSysModel.htm")
    public MessageBean<?> updateSysModel(@RequestBody SysModelBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {

            if (StringUtils.isBlank(bean.getId())) {
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "非法参数", void.class);
            }
            if(StringUtils.isBlank(bean.getModelName())){
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "模块菜单名不能空", List.class);
            }

            success = ADOConnection.runTask(new SysModelService(), "updateSysModel",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("更新模块菜单-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("更新模块菜单-异常");
            return success;
        }
        return success;
    }

    @ApiOperation("--模块菜单-删除")
    @ResponseBody
    @PostMapping(value = "/deleteSysModel.htm")
    public MessageBean<?> deleteSysModel(@RequestBody SysModelBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            success = ADOConnection.runTask(new SysModelService(), "deleteSysModel",MessageBean.class,bean.getId());
        } catch (Exception e) {
            log.error("删除模块菜单-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("删除模块菜单-异常");
            return success;
        }
        return success;
    }


    @ApiOperation("--模块菜单-模块树")
    @ResponseBody
    @PostMapping(value = "/findModelTree.htm")
    public MessageBean<?> findModelTree(){

        @SuppressWarnings("rawtypes")
        MessageBean info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", MessageBean.class);
        try {
            List<ModelTreeBean> list = ADOConnection.runTask(new SysModelService(), "findModelTree", List.class);

            info.setData(list);
        } catch (Exception e) {
            log.error("查询模块树-异常", e);
            info.setCode(Constant.MESSAGE_INT_OPERATION);
            info.setDescription("查询模块树-异常");
            return info;
        }
        return info;
    }
}
