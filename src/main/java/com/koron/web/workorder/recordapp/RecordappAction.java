package com.koron.web.workorder.recordapp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.koron.util.Constant;
import com.koron.web.workorder.recordapp.bean.RecordQueryapp;
import com.koron.web.workorder.recordapp.bean.Recordapp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.springframework.web.bind.annotation.*;
import org.swan.bean.MessageBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "it服务-服务记录")
@RequestMapping("/workorder/RecordappAction")
@RestController
public class RecordappAction {

    Logger log = Logger.getLogger(RecordappAction.class);

    @ApiOperation("---新增服务记录")
    @PostMapping(value = "/addRecordapp.htm")
    public MessageBean<?> addRecordapp(@RequestBody Recordapp bean) {

        @SuppressWarnings("rawtypes")
        MessageBean info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", String.class);
        try {

            if(StringUtils.isEmpty(bean.getEmployeeId())){
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "申请人", void.class);
            }
            info = ADOConnection.runTask(new RecordappService(), "addRecordapp", MessageBean.class,bean);

        } catch (Exception e) {
            log.error("提交服务记录异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("提交服务记录异常");
            return info;
        }
        return info;
    }

    @ApiOperation("---修改保存")
    @PostMapping(value = "/updateRecordapp.htm")
    public MessageBean<?> updateRecordapp(@RequestBody Recordapp bean, HttpServletRequest req) {

        MessageBean info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", String.class);
        try {
            info = ADOConnection.runTask(new RecordappService(), "updateRecordapp", MessageBean.class,bean);
        } catch (Exception e) {
            log.error("服务记录-修改保存-异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("服务记录-修改保存-异常");
            return info;
        }
        return info;
    }

    @ApiOperation("---删除服务记录")
    @PostMapping(value = "/delete.htm")
    public MessageBean<?> delete(@RequestParam("id") String id, HttpServletRequest req) {

        MessageBean info = null;
        try {

            info = ADOConnection.runTask(new RecordappService(), "deleteRecordapp", MessageBean.class,id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("服务记录-删除异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("服务记录-删除异常");
            return info;
        }
        return info;
    }

    @ApiOperation("---查询服务记录列表")
    @PostMapping(value = "/queryList.htm")
    public MessageBean<?> list(@RequestBody RecordQueryapp queryBean, HttpServletRequest req) {

        @SuppressWarnings("rawtypes")
        MessageBean<PageInfo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", PageInfo.class);
        try {

            PageHelper.startPage(queryBean.getPage(), queryBean.getPageCount());
            List<Recordapp> list = ADOConnection.runTask( new RecordappService(), "queryList", List.class,queryBean);

            info.setData(new PageInfo<>(list));
        } catch (Exception e) {
            log.error("服务记录-列表异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("服务记录-列表异常");
            return info;
        }
        return info;
    }

    @ApiOperation("---查询详情")
    @PostMapping(value = "/detail.htm")
    public MessageBean<?> detail(@RequestParam("id") String id) {

        @SuppressWarnings("rawtypes")
        MessageBean<Recordapp> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", Recordapp.class);
        try {

            Recordapp bean = ADOConnection.runTask( new RecordappService(), "detail", Recordapp.class,id);

            info.setData(bean);
        } catch (Exception e) {
            log.error("服务记录-查询详情", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("服务记录-查询详情");
            return info;
        }
        return info;
    }

}
