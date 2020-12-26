package com.koron.web.systemmanger.roles;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.koron.util.Constant;
import com.koron.web.systemmanger.roles.bean.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.springframework.web.bind.annotation.*;
import org.swan.bean.MessageBean;

import java.util.List;

@Api(tags = "系统管理-角色管理")
@RequestMapping("/systemmanger/roleAction")
@RestController
public class RoleAction {

    private static Logger log = Logger.getLogger(RoleAction.class);


    @ApiOperation("--角色-添加")
    @ResponseBody
    @PostMapping(value = "/addSysRole.htm")
    public MessageBean<?> addSysRole(@RequestBody SysRoleBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            if(StringUtils.isBlank(bean.getRoleName())){
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "角色名不能空", List.class);
            }
            success = ADOConnection.runTask(new RolesService(), "addSysRole",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("添加角色-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("添加角色-异常");
            return success;
        }
        return success;
    }


    @ApiOperation("--角色-列表")
    @ResponseBody
    @PostMapping(value = "/queryList.htm")
    public MessageBean<?> queryList(@RequestBody SysRoleQueryBean queryBean){

        @SuppressWarnings("rawtypes")
        MessageBean<PageInfo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", PageInfo.class);
        try {
            PageHelper.startPage(queryBean.getPage(), queryBean.getPageCount());
            List<SysRoleBean> list = ADOConnection.runTask(new RolesService(), "queryList", List.class,queryBean);

            info.setData(new PageInfo<>(list));
        } catch (Exception e) {
            log.error("查询角色列表-异常", e);
            info.setCode(Constant.MESSAGE_INT_OPERATION);
            info.setDescription("查询角色列表-异常");
            return info;
        }
        return info;
    }
    

    @ApiOperation("--角色-修改")
    @ResponseBody
    @PostMapping(value = "/updateSysRole.htm")
    public MessageBean<?> updateSysRole(@RequestBody SysRoleBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            if (StringUtils.isBlank(bean.getId())) {
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "角色标识不能为空", void.class);
            }

            success = ADOConnection.runTask(new RolesService(), "updateSysRole",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("更新角色-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("更新角色-异常");
            return success;
        }
        return success;
    }

    @ApiOperation("--角色-删除")
    @ResponseBody
    @PostMapping(value = "/deleteSysRole.htm")
    public MessageBean<?> deleteSysRole(@RequestParam("id") String id){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            if (StringUtils.isBlank(id)) {
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "角色标识不能为空", void.class);
            }

            success = ADOConnection.runTask(new RolesService(), "deleteSysRole",MessageBean.class,id);
        } catch (Exception e) {
            log.error("删除角色-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("删除角色-异常");
            return success;
        }
        return success;
    }

    @ApiOperation("--分配人员")
    @ResponseBody
    @PostMapping(value = "/portionStaffuntoRole.htm")
    public MessageBean<?> portionStaffuntoRole(@RequestBody RoleStaffunDto dto){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            if(CollectionUtils.isEmpty(dto.getStaffunCodes())){
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "人员不能为空", List.class);
            }
            if(StringUtils.isEmpty(dto.getRoleId())){
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "角色不能为空", List.class);
            }
            success = ADOConnection.runTask(new RolesService(), "portionStaffuntoRole",MessageBean.class,dto);
        } catch (Exception e) {
            log.error("角色-分配人员-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("角色-分配人员-异常");
            return success;
        }
        return success;
    }



    @ApiOperation("--角色-查角色下人员")
    @ResponseBody
    @PostMapping(value = "/staffunListByroleId.htm")
    public MessageBean<?> staffunListByroleId(@RequestBody SysRoleQueryBean queryBean){

        @SuppressWarnings("rawtypes")
        MessageBean<PageInfo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", PageInfo.class);
        try {
            PageHelper.startPage(queryBean.getPage(), queryBean.getPageCount());
            List<SysRoleBean> list = ADOConnection.runTask(new RolesService(), "staffunListByroleId", List.class,queryBean);

            info.setData(new PageInfo<>(list));
        } catch (Exception e) {
            log.error("角色-查角色下人员-异常", e);
            info.setCode(Constant.MESSAGE_INT_OPERATION);
            info.setDescription("角色-查角色下人员-异常");
            return info;
        }
        return info;
    }

    @ApiOperation("--角色-移除人员(单个)")
    @ResponseBody
    @PostMapping(value = "/removePersonRole.htm")
    public MessageBean<?> removePersonRole(@RequestBody RoleStaffunDto dto){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            if (StringUtils.isBlank(dto.getRoleId())) {
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "角色标识不能为空", void.class);
            }
            if (StringUtils.isBlank(dto.getStaffunCode())) {
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "用户code不能为空", void.class);
            }

            success = ADOConnection.runTask(new RolesService(), "removePersonRole",MessageBean.class,dto);
        } catch (Exception e) {
            log.error("角色-移除人员-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("角色-移除人员-异常");
            return success;
        }
        return success;
    }

    // ==============================================================================================================

    @ApiOperation("--角色-查角色权限")
    @ResponseBody
    @PostMapping(value = "/queryModelByroleId.htm")
    public MessageBean<?> queryModelByroleId(@RequestParam("id") String id){

        @SuppressWarnings("rawtypes")
        MessageBean<FpModelVo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", FpModelVo.class);
        try {
//            PageHelper.startPage(queryBean.getPage(), queryBean.getPageCount());
            FpModelVo list = ADOConnection.runTask(new RolesService(), "queryModelByroleId", FpModelVo.class,id);

            info.setData(list);
        } catch (Exception e) {
            log.error("角色-查角色权限-异常", e);
            info.setCode(Constant.MESSAGE_INT_OPERATION);
            info.setDescription("角色-查角色权限-异常");
            return info;
        }
        return info;
    }


    @ApiOperation("--角色-保存修改权限")
    @ResponseBody
    @PostMapping(value = "/portionModeltoRole.htm")
    public MessageBean<?> portionModeltoRole(@RequestBody RoleModelDto dto){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            if(StringUtils.isEmpty(dto.getRoleId())){
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "角色不能为空", List.class);
            }

            success = ADOConnection.runTask(new RolesService(), "portionModeltoRole",MessageBean.class,dto);
        } catch (Exception e) {
            log.error("角色-分配模块菜单給角色-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("角色-分配模块菜单給角色-异常");
            return success;
        }
        return success;
    }

}
