package com.koron.web.workflow.service;

import com.koron.util.Constant;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.workflow.bean.WorkflowResultBean;
import com.koron.web.workflow.mapper.WorkflowBusinessMapper;
import com.koron.web.workflow.mapper.WorkflowMapper;
import org.apache.commons.lang3.StringUtils;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.swan.bean.MessageBean;

import java.util.List;

/**
 * 审核流状态的设置与查询
 *
 */
public class WorkflowBusinessService {
    /**
     * 更新单据审核流信息
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("updateWorkflowInfo")
    public MessageBean<?> updateWorkflowInfo(SessionFactory factory, WorkflowResultBean bean){
        WorkflowBusinessMapper mapper = factory.getMapper(WorkflowBusinessMapper.class);
        String auditorName = "";
        if(!StringUtils.isEmpty(bean.getNextcandidateUsers())){
            WorkflowMapper workflowMapper = factory.getMapper(WorkflowMapper.class);
            String[] workflowids = bean.getNextcandidateUsers().split(",");
            List<String> namelist = workflowMapper.quetlistByworkflowcode(workflowids);
            auditorName = String.join(",",namelist);
            bean.setAuditorName(auditorName);
        }

        Integer ret = mapper.updateWorkflowInfo(bean);

        if(bean.getNextactivitiName()!=null && bean.getNextactivitiName().equals("结束")) {
            mapper.updateend(bean);
        }

        MessageBean<Integer> msg = MessageBean.create(Constant.MESSAGE_INT_SUCCESS,"更新单据审核流信息", Integer.class);
        msg.setData(ret);
        return msg;
    }

    /**
     * 获取审核流平台审核流ID
     * @param factory
     * @param tableName
     * @param billId
     * @return
     */
    @TaskAnnotation("getWorkflowId")
    public MessageBean<?> getWorkflowId(SessionFactory factory,String tableName,String billId){
        WorkflowBusinessMapper mapper = factory.getMapper(WorkflowBusinessMapper.class);
        String ret = mapper.getWorkflowId(tableName, billId);
        MessageBean<String> msg = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "获取审核流平台审核流ID", String.class);
        msg.setData(ret);
        return msg;
    }
}
