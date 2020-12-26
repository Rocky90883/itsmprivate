package com.koron.web.workflow.mapper;

import com.koron.web.workflow.bean.ViewEnableBean;
import com.koron.web.workflow.bean.ViewEnableQueryBean;
import com.koron.web.workflowUtils.bean.TaskListVo;

import java.util.List;

public interface ViewEnableMapper {

    List<ViewEnableBean> queryList(ViewEnableQueryBean bean);


    List<TaskListVo> getviewdraft(ViewEnableQueryBean bean);
}