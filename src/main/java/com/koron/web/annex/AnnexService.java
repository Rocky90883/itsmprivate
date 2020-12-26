package com.koron.web.annex;

import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.annex.bean.AnnexBean;
import com.koron.web.asset.assetregist.AssetRegistMapper;
import com.koron.web.asset.assetregist.bean.AssetRegistBean;
import com.koron.web.asset.assetregist.bean.AssetRegistQueryBean;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.swan.bean.MessageBean;

import java.util.List;

@Service
public class AnnexService {

    private static Logger log = Logger.getLogger(AnnexService.class);

    /**
     * 根据id附件
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("annexById")
    public AnnexBean annexById(SessionFactory factory, String id) {
        AnnexBean annex;
        try {
            AnnexMapper mapper = factory.getMapper(AnnexMapper.class);
            annex = mapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return annex;
    }

    /**
     * 附件列表
     * @param factory
     * @param dto
     * @return
     */
    @TaskAnnotation("queryList")
    public List<AnnexBean> queryList(SessionFactory factory, AnnexBean dto) {
        List<AnnexBean> list;
        try {
            AnnexMapper mapper = factory.getMapper(AnnexMapper.class);
            list = mapper.queryList(dto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }

    /**
     * 新增附件
     * @param sessionFactory
     * @param bean
     * @return
     */
    @TaskAnnotation("addAnnex")
    public MessageBean addAnnex(SessionFactory sessionFactory, AnnexBean bean) {

        try {
            AnnexMapper mapper = sessionFactory.getMapper(AnnexMapper.class);
            bean.setId(CommonUtil.get32Key());
            bean.setCreateAccount(SessionUtil.getUseerInfoCode());
            bean.setCreateName(SessionUtil.getUseerInfoName());
            bean.setCreateTime(CommonUtil.getCurrentTime());
            mapper.insertSelective(bean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 删除资产登记
     * @param sessionFactory
     * @param id
     * @return
     */
    @TaskAnnotation("deleteRegist")
    public MessageBean deleteRegist(SessionFactory sessionFactory, String id) {

        try {
            AnnexMapper mapper = sessionFactory.getMapper(AnnexMapper.class);
            mapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }


}
