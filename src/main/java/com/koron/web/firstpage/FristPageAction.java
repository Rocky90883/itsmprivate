package com.koron.web.firstpage;

import com.github.pagehelper.PageHelper;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.firstpage.bean.FirstPageVo;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.workflow.bean.WorkDbDto;
import com.koron.web.workflow.service.WorkflowService;
import com.koron.web.workflowUtils.bean.MessageEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.springframework.web.bind.annotation.*;
import org.swan.bean.MessageBean;

@Api(tags = "首页")
@RestController
@RequestMapping("/firstpage/fristPageAction")
public class FristPageAction {

    Logger log = Logger.getLogger(FristPageAction.class);

    @ApiOperation("--最新信息")
    @PostMapping(value = "/newestmsg.htm")
    public MessageBean<?> newestmsg() {

        @SuppressWarnings("rawtypes")
        MessageBean<FirstPageVo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", FirstPageVo.class);
        try {
            FirstPageVo newestmsg = ADOConnection.runTask(new FristPageService(), "newestmsg", FirstPageVo.class);
            info.setData(newestmsg);
        } catch (Exception e) {
            log.error("首页-最新信息-异常", e);
            info.setCode(Constant.MESSAGE_INT_UNKNOW_ERROR);
            info.setDescription("首页-最新信息-异常");
            return info;
        }
        return info;
    }


    /**
     * 获取工作流列表
     * @return
     */
    @ApiOperation("--获取最新待办")
    @PostMapping("/getWorkflowList.htm")
    @ResponseBody
    public MessageEntity getWorkflowList(){

        if(SessionUtil.getUseerInfoCode()==null){
            MessageEntity res = new MessageEntity();
            res.setCode(Constant.MESSAGE_INT_NOLOGIN);
            res.setDescription("获取待办需登陆");
            return res;
        }

        WorkDbDto dto = new WorkDbDto();
        dto.setPage(1);
        dto.setPageCount(10);
        dto.setType(2);//待办
        StaffunBean user = SessionUtil.getLoginUser();      //获取用户信息

        MessageEntity ret = ADOConnection.runTask(new WorkflowService(), "getWorkflowList", MessageEntity.class, user, dto,0);
        return ret;
    }

}
