package com.koron.web.firstpage;

import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.firstpage.bean.FirstPageVo;
import com.koron.web.firstpage.bean.ServiceAndMtBean;
import com.koron.web.firstpage.bean.ServiceAndMtQueryBean;
import com.koron.web.firstpage.bean.SpareFristBean;
import com.koron.web.workflow.bean.BaseWorkflowLogoBean;
import com.koron.web.workflowUtils.mapper.BaseWorkflowMapper;
import com.koron.web.workorder.recordapp.RecordappMapper;
import com.koron.web.workorder.recordapp.bean.RecordQueryapp;
import com.koron.web.workorder.recordapp.bean.Recordapp;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.swan.bean.MessageBean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FristPageService {

    private static Logger log = Logger.getLogger(FristPageService.class);

    /**
     * 首页
     * @param factory
     * @return
     */
    @TaskAnnotation("newestmsg")
    public FirstPageVo newestmsg(SessionFactory factory) {
        FirstPageVo vo = new FirstPageVo();
        try {
            FirstPageMapper mapper = factory.getMapper(FirstPageMapper.class);
            String[] re=new String[] {"mt_release"};
            String[] md=new String[] {"mt_modify"};
            String[] pro=new String[] {"mt_problem"};
            String[] seri=new String[] {"accountapp","msgapp","dataccessapp","repairapp","officeapp","demandapp"};

            List<ServiceAndMtBean> relist = mapper.queryservicesList(re);
            List<ServiceAndMtBean> mdlist = mapper.queryservicesList(md);
            List<ServiceAndMtBean> prolist = mapper.queryservicesList(pro);
            List<ServiceAndMtBean> serlist = mapper.queryservicesList(seri);
            List<SpareFristBean> spalist = mapper.querySpareFristList();

            vo.setRelelist(relist);
            vo.setModilist(mdlist);
            vo.setProlist(prolist);
            vo.setServicelist(serlist);
            vo.setSparelist(spalist);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return vo;
    }

    /**
     * 详情
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("detail")
    public Recordapp detail(SessionFactory factory, String id) {
        try {
            RecordappMapper mapper = factory.getMapper(RecordappMapper.class);
            Recordapp bean = mapper.selectByPrimaryKey(id);

            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 新增服务记录
     * @param factory
     * @param bean
     * @return
     */
    @TaskAnnotation("addRecordapp")
    public MessageBean addRecordapp(SessionFactory factory, Recordapp bean) {
        MessageBean<String> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", String.class);
        try {
            RecordappMapper mapper = factory.getMapper(RecordappMapper.class);
            String id= CommonUtil.get32Key();
            bean.setId(id);

            //设置单据编号
            String maxOrderNo = mapper.maxOrderNo();//获取当天最大的服务单号
            String billNo = CommonUtil.generateStream("FW", "", maxOrderNo);
            bean.setOrderNo(billNo);

            if(StringUtils.isEmpty(bean.getEmpName())){
                bean.setEmpName(SessionUtil.getUseerInfoName());
            }

            bean.setCreateAccount(SessionUtil.getUseerInfoCode());
            bean.setCreateName(SessionUtil.getUseerInfoName());
            bean.setCreateTime(CommonUtil.getCurrentTime());
            mapper.insertSelective(bean);

            success.setData(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return success;
    }

    /**
     * 删除服务记录
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("deleteRecordapp")
    public MessageBean deleteRecordapp(SessionFactory factory, String id) {

        try {
            RecordappMapper mapper = factory.getMapper(RecordappMapper.class);
            mapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 更新服务记录
     * @param sessionFactory
     * @param bean
     * @return
     */
    @TaskAnnotation("updateRecordapp")
    public MessageBean updateRecordapp(SessionFactory sessionFactory, Recordapp bean) {

        MessageBean<String> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", String.class);
        try {
            RecordappMapper mapper = sessionFactory.getMapper(RecordappMapper.class);
            
            bean.setUpdateAccount(SessionUtil.getUseerInfoCode());
            bean.setUpdateName(SessionUtil.getUseerInfoName());
            bean.setUpdateTime(CommonUtil.getCurrentTime());
            mapper.updateByPrimaryKeySelective(bean);                   //写库
            success.setData(bean.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return success;
    }

}
