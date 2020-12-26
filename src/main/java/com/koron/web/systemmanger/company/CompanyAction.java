package com.koron.web.systemmanger.company;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.koron.util.Constant;
import com.koron.web.systemmanger.company.bean.CompanyBean;
import com.koron.web.systemmanger.company.bean.CompanyDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.springframework.web.bind.annotation.*;
import org.swan.bean.MessageBean;

import java.util.List;

@Api(tags = "系统管理-供应商")
@RequestMapping("/systemmanger/companyAction")
@RestController
public class CompanyAction {

    private static Logger log = Logger.getLogger(CompanyAction.class);


    @ApiOperation("--供应商-添加")
    @ResponseBody
    @PostMapping(value = "/addCompany")
    public MessageBean<?> addCompany(@RequestBody CompanyBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {

            success = ADOConnection.runTask(new CompanyService(), "addCompany",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("添加供应商-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("添加供应商-异常");
            return success;
        }
        return success;
    }


    @ApiOperation("--供应商-列表")
    @ResponseBody
    @PostMapping(value = "/queryList")
    public MessageBean<?> queryList(@RequestBody CompanyDto dto){

        @SuppressWarnings("rawtypes")
        MessageBean<PageInfo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", PageInfo.class);
        try {
            PageHelper.startPage(dto.getPage(), dto.getPageCount());
            List<CompanyBean> list = ADOConnection.runTask(new CompanyService(), "queryList", List.class,dto);

            info.setData(new PageInfo<>(list));
        } catch (Exception e) {
            log.error("查询供应商列表-异常", e);
            info.setCode(Constant.MESSAGE_INT_OPERATION);
            info.setDescription("查询供应商列表-异常");
            return info;
        }
        return info;
    }
    

    @ApiOperation("--供应商-修改")
    @ResponseBody
    @PostMapping(value = "/updateCompany")
    public MessageBean<?> updateCompany(@RequestBody CompanyBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {

            success = ADOConnection.runTask(new CompanyService(), "updateCompany",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("更新供应商-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("更新供应商-异常");
            return success;
        }
        return success;
    }

    @ApiOperation("--供应商-删除")
    @ResponseBody
    @PostMapping(value = "/deleteCompany")
    public MessageBean<?> deleteCompany(@RequestBody CompanyBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            success = ADOConnection.runTask(new CompanyService(), "deleteCompany",MessageBean.class,bean.getId());
        } catch (Exception e) {
            log.error("删除供应商-异常", e);
            success.setCode(Constant.MESSAGE_INT_OPERATION);
            success.setDescription("删除供应商-异常");
            return success;
        }
        return success;
    }

}
