package com.koron.web.workorder.scoremark;

import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.asset.assets.AssetsMapper;
import com.koron.web.asset.assets.bean.AssetsBean;
import com.koron.web.workorder.scoremark.bean.ScoremarkBean;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.swan.bean.MessageBean;

import java.util.List;


@Service
public class ScoremarkService {

    private static Logger log = Logger.getLogger(ScoremarkService.class);

//    /**
//     * 评分记录列表
//     * @param factory
//     * @param queryBean
//     * @return
//     */
//    @TaskAnnotation("queryList")
//    public List<ScoremarkBean> queryList(SessionFactory factory, ScoremarkQueryBean queryBean) {
//        List<ScoremarkBean> list;
//        try {
//            ScoremarkMapper mapper = factory.getMapper(ScoremarkMapper.class);
//            list = mapper.queryList(queryBean);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e.getMessage());
//        }
//        return list;
//    }

    /**
     * 新增评分记录
     * @param sessionFactory
     * @param bean
     * @return
     */
    @TaskAnnotation("addScoremark")
    public MessageBean addScoremark(SessionFactory sessionFactory, ScoremarkBean bean) {
        MessageBean<String> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", String.class);
        try {
            ScoremarkMapper mapper = sessionFactory.getMapper(ScoremarkMapper.class);
            
            bean.setId(CommonUtil.get32Key());
            bean.setCreateAccount(SessionUtil.getUseerInfoCode());
            bean.setCreateName(SessionUtil.getUseerInfoName());
            bean.setCreateTime(CommonUtil.getCurrentTime());
            mapper.insertSelective(bean);
            success.setData(bean.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return success;
    }

    /**
     * 详情
     * @param factory
     * @param sourceId
     * @return
     */
    @TaskAnnotation("detail")
    public List<ScoremarkBean> detail(SessionFactory factory, String sourceId) {
        try {
            ScoremarkMapper mapper = factory.getMapper(ScoremarkMapper.class);
            List<ScoremarkBean> list = mapper.selectBysourceId(sourceId);

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 删除评分记录
     * @param sessionFactory
     * @param id
     * @return
     */
    @TaskAnnotation("deleteScoremark")
    public MessageBean deleteScoremark(SessionFactory sessionFactory, String id) {

        try {
            ScoremarkMapper mapper = sessionFactory.getMapper(ScoremarkMapper.class);
            AssetsMapper assetmapper = sessionFactory.getMapper(AssetsMapper.class);

            ScoremarkBean ScoremarkBean = mapper.selectByPrimaryKey(id);
            mapper.deleteByPrimaryKey(id);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 更新评分记录
     * @param sessionFactory
     * @param bean
     * @return
     */
    @TaskAnnotation("updateScoremark")
    public MessageBean updateScoremark(SessionFactory sessionFactory, ScoremarkBean bean) {

        try {
            ScoremarkMapper mapper = sessionFactory.getMapper(ScoremarkMapper.class);

            bean.setUpdateAccount(SessionUtil.getUseerInfoCode());
            bean.setUpdateName(SessionUtil.getUseerInfoName());
            bean.setUpdateTime(CommonUtil.getCurrentTime());
            mapper.updateByPrimaryKeySelective(bean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }




}
