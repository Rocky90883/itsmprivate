package com.koron.web.workorder.scoremark;

import com.koron.util.Constant;
import com.koron.web.workorder.scoremark.bean.ScoremarkBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.springframework.web.bind.annotation.*;
import org.swan.bean.MessageBean;

import java.util.List;

@Api(tags = "it服务-评分记录")
@RequestMapping("/workorder/ScoremarkAction")
@RestController
public class ScoremarkAction {

    private static Logger log = Logger.getLogger(ScoremarkAction.class);


    @ApiOperation("--评分记录-添加")
    @ResponseBody
    @PostMapping(value = "/addScoremark.htm")
    public MessageBean<?> addScoremark(@RequestBody ScoremarkBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {

            success = ADOConnection.runTask(new ScoremarkService(), "addScoremark",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("添加评分记录-异常", e);
            success.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            success.setDescription("添加评分记录-异常");
            return success;
        }
        return success;
    }


//    @ApiOperation("--评分记录-列表")
//    @ResponseBody
//    @PostMapping(value = "/queryList.htm")
//    public MessageBean<?> queryList(@RequestBody ScoremarkQueryBean dto){
//
//        @SuppressWarnings("rawtypes")
//        MessageBean<PageInfo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", PageInfo.class);
//        try {
//            PageHelper.startPage(dto.getPage(), dto.getPageCount());
//            List<ScoremarkBean> list = ADOConnection.runTask(new ScoremarkService(), "queryList", List.class,dto);
//
//            info.setData(new PageInfo<>(list));
//        } catch (Exception e) {
//            log.error("查询评分记录列表-异常", e);
//            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
//            info.setDescription("查询评分记录列表-异常");
//            return info;
//        }
//        return info;
//    }

    @ApiOperation("---查询详情")
    @PostMapping(value = "/detail.htm")
    public MessageBean<?> detail(@RequestParam("sourceId") String sourceId) {

        @SuppressWarnings("rawtypes")
        MessageBean<List> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            if(StringUtils.isEmpty(sourceId)){
                MessageBean.create(Constant.MESSAGE_INT_PARAMS, "单据id不能为空", List.class);
            }
            List<ScoremarkBean> detail = ADOConnection.runTask(new ScoremarkService(), "detail", List.class, sourceId);

            info.setData(detail);
        } catch (Exception e) {
            log.error("评分记录-查询详情", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("评分记录-查询详情");
            return info;
        }
        return info;
    }

    @ApiOperation("--评分记录-修改")
    @ResponseBody
    @PostMapping(value = "/updateScoremark.htm")
    public MessageBean<?> updateScoremark(@RequestBody ScoremarkBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {

            success = ADOConnection.runTask(new ScoremarkService(), "updateScoremark",MessageBean.class,bean);
        } catch (Exception e) {
            log.error("更新评分记录-异常", e);
            success.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            success.setDescription("更新评分记录-异常");
            return success;
        }
        return success;
    }

    @ApiOperation("--评分记录-删除")
    @ResponseBody
    @PostMapping(value = "/deleteScoremark.htm")
    public MessageBean<?> deleteScoremark(@RequestBody ScoremarkBean bean){

        @SuppressWarnings("rawtypes")
        MessageBean<List> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
            success = ADOConnection.runTask(new ScoremarkService(), "deleteScoremark",MessageBean.class,bean.getId());
        } catch (Exception e) {
            log.error("删除评分记录-异常", e);
            success.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            success.setDescription("删除评分记录-异常");
            return success;
        }
        return success;
    }



}
