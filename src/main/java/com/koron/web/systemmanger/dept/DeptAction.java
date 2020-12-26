package com.koron.web.systemmanger.dept;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.koron.util.Constant;
import com.koron.web.systemmanger.model.bean.ModelTreeBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.swan.bean.MessageBean;

import java.util.List;
import java.util.Map;

@Api(tags = "系统管理-部门管理")
@Controller
@RequestMapping("/systemmanger/deptAction")
public class DeptAction {

    Logger log = Logger.getLogger(DeptAction.class);

    @ApiOperation("---同步组织架构部门表")
    @GetMapping(value = "/newSyncDep.htm")
    @ResponseBody
    public String syncDep() {
        MessageBean<?> runTask = ADOConnection.runTask(new DeptService(), "syncDep", MessageBean.class);
        return runTask.toJson();
    }

    /**
     * 根据flag字段和supercode字段获取部门树,不是一次性获取组织树，层层获取
     * @param code
     * @return
     */
    @ApiOperation("---部门按级查询")
    @PostMapping("/newDepartmentTree.htm")
    @ResponseBody
    public MessageBean newGetDepartmentTree(@RequestParam("code") String code) {
        MessageBean<?> runTask=ADOConnection.runTask(new DeptService(), "newGetDepartmentTree", MessageBean.class,code);
        return runTask;
    }


    /**
     * 部门列表查询
     * @param condition
     * @return
     */
    @ApiOperation("---部门列表查询")
    @PostMapping("/newDepartmentQuery.htm")
    @ResponseBody
    public MessageBean newDepartmentQuery(@RequestBody DepartunQueyBean condition) {
        MessageBean<PageInfo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", PageInfo.class);
        try {
            PageHelper.startPage(condition.getPage(), condition.getPageCount());
            List<DepartunBean> list = ADOConnection.runTask(new DeptService(), "newDepartmentQuery", List.class, condition);
            info.setData(new PageInfo<>(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    @ApiOperation("---部门树")
    @ResponseBody
    @PostMapping(value = "/findDepartTree")
    public MessageBean<?> findDepartTree(){

        @SuppressWarnings("rawtypes")
        MessageBean info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", Map.class);
        try {
            List<ModelTreeBean> list = ADOConnection.runTask(new DeptService(), "findDepartTree", List.class);

            info.setData(list);
        } catch (Exception e) {
            log.error("查询部门树-异常", e);
            info.setCode(Constant.MESSAGE_INT_OPERATION);
            info.setDescription("查询部门树-异常");
            return info;
        }
        return info;
    }
//    @Autowired
//    private DeptService service;
//
//    @ResponseBody
//    @PostMapping("/add.htm")
//    public String add(@RequestBody DeptBean bean){
//
//        return ADOConnection.runTask(factory -> service.add(factory,bean), MessageBean.class).toJson();
//    }
//
//    @ResponseBody
//    @GetMapping("/delete.htm")
//    public String delete(@RequestParam(value = "deptId",required = true) String deptId ){
//
//        return ADOConnection.runTask(factory -> service.delete(factory,deptId), MessageBean.class).toJson();
//    }
//
//    @ResponseBody
//    @PostMapping("/update.htm")
//    public String update(@RequestBody DeptBean bean ){
//
//        return ADOConnection.runTask(factory -> service.update(factory,bean), MessageBean.class).toJson();
//    }
//
//    @ResponseBody
//    @GetMapping("/list.htm")
//    public String list(@RequestParam(value = "page" ,required = true)Integer page,
//                       @RequestParam(value = "pageSize",required = true)Integer pageSize,
//                       @RequestParam(value = "deptName",defaultValue = "",required = false)String deptName){
//
//        DeptQueryBean queryBean = new DeptQueryBean();
//        queryBean.setPage(page);
//        queryBean.setPageSize(pageSize);
//        queryBean.setDeptName(deptName);
//        return ADOConnection.runTask(factory -> service.list(factory,queryBean), MessageBean.class).toJson();
//    }
//
//
//    @NoAuth
//    @ApiOperation("查询所有部门")
//    @ResponseBody
//    @GetMapping("/alllist.htm")
//    public String alllist(){
//        return ADOConnection.runTask(factory -> service.alllist(factory), MessageBean.class).toJson();
//    }
//
//
//    @NoAuth
//    @ApiOperation("查用用户所属部门")
//    @ResponseBody
//    @GetMapping("/listByLoginId.htm")
//    public String listByLoginId(@RequestParam(value = "loginId",required = true)Integer loginId){
//        MessageBean success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS,"success",Map.class);
//
//        List<String> modelList = ADOConnection.runTask(factory -> service.listByLoginId(factory, loginId), List.class);
//        success.setData(modelList);
//
//        return success.toJson();
//    }
//
//    @ApiOperation("修改用户部门")
//    @ResponseBody
//    @GetMapping("/setUpDeptByUser.htm")
//    public String setUpDeptByUser(@RequestParam(value = "loginId",required = true)Integer loginId,@RequestParam(value = "deptlist",required = false)List<String> list){
//
//        ADOConnection.runTask(factory -> service.setUpDeptByUser(factory,loginId,list), MessageBean.class);
//        MessageBean success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS,"success",Map.class);
//
//        return success.toJson();
//    }
}
