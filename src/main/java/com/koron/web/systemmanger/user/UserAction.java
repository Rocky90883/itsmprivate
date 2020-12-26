package com.koron.web.systemmanger.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.koron.common.bean.StaffBean;
import com.koron.util.Constant;
import com.koron.util.JsonUtils;
import com.koron.web.systemmanger.user.bean.StaffunQueryBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.ADOConnection;
import org.springframework.web.bind.annotation.*;
import org.swan.bean.MessageBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Api(tags = "系统管理-用户管理")
@RequestMapping("/systemmanger/userAction")
@RestController
public class UserAction {

    private Logger log = Logger.getLogger(UserAction.class);

//    @Autowired
//    private UserService userService;

    @ApiOperation("---查询用户列表")
    @PostMapping(value = "/staffList.htm")
    public MessageBean<?> staffList(@RequestBody StaffunQueryBean queryBean, HttpServletRequest req) {

        @SuppressWarnings("rawtypes")
//        MessageBean<PageInfo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", PageInfo.class);
        MessageBean<List> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", List.class);
        try {
//            StaffunQueryBean queryBean = JsonUtils.objectToPojo(requestBean.getData(), StaffunQueryBean.class);
//            PageHelper.startPage(queryBean.getPage(), queryBean.getPageCount());
            List<StaffBean> list = ADOConnection.runTask("_default", new UserService(), "staffList", List.class,queryBean);

//            info.setData(new PageInfo<>(list));
            info.setData(list);
        } catch (Exception e) {
            log.error("查询用户列表失败", e);
            info.setCode(Constant.MESSAGE_INT_OPERATION);
            info.setDescription("fail " + e.getMessage());
            return info;
        }
        return info;
    }

    /**
     * @desc 同步组织架构人员表
     * @author lrli
     * @date 2019/10/29
     * @return
     */
    @ApiOperation("---同步组织架构人员表")
    @GetMapping(value = "/newSyncUser.htm")
    @ResponseBody
    public String syncUser() {
        MessageBean<?> runTask = ADOConnection.runTask(new UserService(), "syncUser", MessageBean.class);
        return runTask.toJson();
    }



    @ApiOperation("---有分页的 -- 查询用户列表")
    @PostMapping(value = "/staffListPage.htm")
    public MessageBean<PageInfo> staffListPage(@RequestBody StaffunQueryBean queryBean, HttpServletRequest req) {

        @SuppressWarnings("rawtypes")
        MessageBean<PageInfo> info = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", PageInfo.class);
        try {
//            StaffunQueryBean queryBean = JsonUtils.objectToPojo(requestBean.getData(), StaffunQueryBean.class);
            PageHelper.startPage(queryBean.getPage(), queryBean.getPageCount());
            List<StaffBean> list = ADOConnection.runTask("_default", new UserService(), "staffList", List.class,queryBean);

            info.setData(new PageInfo<>(list));
        } catch (Exception e) {
            log.error("---有分页的-- 查询用户列表 异常", e);
            info.setCode(Constant.MESSAGE_INT_OPERATION);
            info.setDescription("fail " + e.getMessage());
            return info;
        }
        return info;
    }
}
