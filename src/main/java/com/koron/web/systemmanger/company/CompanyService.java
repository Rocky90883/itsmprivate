package com.koron.web.systemmanger.company;

import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.systemmanger.company.bean.CompanyBean;
import com.koron.web.systemmanger.company.bean.CompanyDto;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.swan.bean.MessageBean;

import java.util.List;

@Service
public class CompanyService {

    private static Logger log = Logger.getLogger(CompanyService.class);

    /**
     * 供应商列表
     * @param factory
     * @param queryBean
     * @return
     */
    @TaskAnnotation("queryList")
    public List<CompanyBean> queryList(SessionFactory factory, CompanyDto queryBean) {
        List<CompanyBean> comist;
        try {
            CompanyMapper mapper = factory.getMapper(CompanyMapper.class);
            comist = mapper.queryList(queryBean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return comist;
    }

    /**
     * 新增供应商
     * @param sessionFactory
     * @param bean
     * @return
     */
    @TaskAnnotation("addCompany")
    public MessageBean addCompany(SessionFactory sessionFactory, CompanyBean bean) {

        try {
            CompanyMapper mapper = sessionFactory.getMapper(CompanyMapper.class);

            //设置单据编号
            CompanyDto queryBean = new CompanyDto();
            String maxComNumber = mapper.maxComNumber();//获取当天最大的单据编号
            String ComNumber = CommonUtil.generateStream("GY", "", maxComNumber);
            bean.setComNumber(ComNumber);

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
     * 删除供应商
     * @param sessionFactory
     * @param id
     * @return
     */
    @TaskAnnotation("deleteCompany")
    public MessageBean deleteCompany(SessionFactory sessionFactory, String id) {

        try {
            CompanyMapper mapper = sessionFactory.getMapper(CompanyMapper.class);

            mapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 更新供应商
     * @param sessionFactory
     * @param bean
     * @return
     */
    @TaskAnnotation("updateCompany")
    public MessageBean updateCompany(SessionFactory sessionFactory, CompanyBean bean) {

        try {
            CompanyMapper mapper = sessionFactory.getMapper(CompanyMapper.class);

            mapper.updateByPrimaryKeySelective(bean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

}
