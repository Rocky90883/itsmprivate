package com.koron.web.meeting;

import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.swan.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MeetingService {

    private static Logger log = Logger.getLogger(MeetingService.class);


    /**
     * 会议列表
     *
     * @param sessionFactory
     * @param queryBean
     * @return
     */
    @TaskAnnotation("mettingList")
    public List<MeetingBean> mettingList(SessionFactory sessionFactory, MeetingQueryBean queryBean) {
        List<MeetingBean> meetingBean;
        try {

            MeetingMapper mapper = sessionFactory.getMapper(MeetingMapper.class);
            meetingBean = mapper.list(queryBean);
        } catch (Exception e) {

            throw new RuntimeException(e.getMessage());
        }
        return meetingBean;
    }

    /**
     * 根据日期查会议列表及明细
     *
     * @param sessionFactory
     * @param billDate
     * @return
     */
    @TaskAnnotation("mettingDet")
    public List<MeetingZBean> mettingDet(SessionFactory sessionFactory, String billDate) {
        List<MeetingZBean> resultList = new ArrayList<>();
        List<MeetingBean> meetingBean;
        try {
            MeetingMapper mapper = sessionFactory.getMapper(MeetingMapper.class);

            meetingBean = mapper.meetingdet(billDate);

            Map<String, List<MeetingBean>> titlegroup = meetingBean.stream().collect(Collectors.groupingBy(MeetingBean::getTitle));
            for (Map.Entry<String, List<MeetingBean>> enTitle : titlegroup.entrySet()) {
                MeetingZBean zbean = new MeetingZBean();
                zbean.setBillDate(billDate);
                zbean.setTitle(enTitle.getKey());
                List<MeetingDBean> dbeanList = new ArrayList<>();
                List<String> contentList = new ArrayList<>();
                for (MeetingBean enCon : enTitle.getValue()) {
                    MeetingDBean dbean = new MeetingDBean();
                    dbean.setContent(enCon.getContent());
                    dbean.setSort(enCon.getSort());
                    contentList.add(enCon.getContent());
                    dbeanList.add(dbean);
                }

                zbean.setListDet(dbeanList);
                zbean.setContentList(contentList);
                resultList.add(zbean);
            }

        } catch (Exception e) {
            log.error("根据日期查会议列表及明细异常.");
            throw new RuntimeException(e.getMessage());
        }
        return resultList;
    }


    /**
     * 添加会议
     *
     * @param sessionFactory
     * @param bean
     */
    @TaskAnnotation("mettingAdd")
    public MessageBean mettingAdd(SessionFactory sessionFactory, MeetingBean bean) {
        MessageBean success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
        try {
            MeetingMapper mapper = sessionFactory.getMapper(MeetingMapper.class);

            if (StringUtils.isEmpty(bean.getBillDate())) {
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "会议日期不能为空", void.class);
            }
            if (StringUtils.isEmpty(bean.getTitle())) {
                return MessageBean.create(Constant.MESSAGE_INT_PARAMS, "会议名称不能为空", void.class);
            }

            bean.setId(new ObjectId().toHexString());
//            bean.setCreateTime(CommonUtil.getCurrentTime());

            //后插入
            mapper.add(bean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return success;
    }


    /**
     * 删除会议
     *
     * @param sessionFactory
     * @param id
     * @return
     */
    @TaskAnnotation("mettingDel")
    public MessageBean mettingDel(SessionFactory sessionFactory, String id) {
        MessageBean success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
        try {
            MeetingMapper mapper = sessionFactory.getMapper(MeetingMapper.class);
            mapper.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return success;
    }

    /**
     * 根据日期会议名称 删除会议
     *
     * @param sessionFactory
     * @return
     */
    @TaskAnnotation("deletebybillDateOnTitle")
    public MessageBean deletebybillDateOnTitle(SessionFactory sessionFactory, String billDate, String title) {
        MessageBean success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
        if (StringUtils.isEmpty(billDate) || StringUtils.isEmpty(title)) {
            success.setCode(Constant.MESSAGE_INT_PARAMS);
            success.setDescription("会议日期与会议名称不能为空");
            return success;
        }
        try {
            MeetingMapper mapper = sessionFactory.getMapper(MeetingMapper.class);
            mapper.deletebybillDateOnTitle(billDate, title);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return success;
    }

    /**
     * 修改会议
     *
     * @param sessionFactory
     * @param bean
     * @return
     */
    @TaskAnnotation("mettingUpdate")
    public MessageBean mettingUpdate(SessionFactory sessionFactory, MeetingBean bean) {
        MessageBean success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
        try {
            MeetingMapper mapper = sessionFactory.getMapper(MeetingMapper.class);

            int i = mapper.countbyBillDateTitletoSort(bean.getBillDate(), bean.getTitle(), bean.getSort(), bean.getId());
            if (i > 0) {
                success.setCode(Constant.MESSAGE_INT_PARAMS);
                success.setDescription(bean.getBillDate() + "天的" + bean.getTitle() + "会议中" + bean.getSort() + "已存在");
                return success;
            }
//            bean.setModifyTime(CommonUtil.getCurrentTime());
            mapper.update(bean);
        } catch (Exception e) {

            throw new RuntimeException(e.getMessage());
        }
        return success;
    }

}
