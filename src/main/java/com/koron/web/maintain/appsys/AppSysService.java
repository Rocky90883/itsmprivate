package com.koron.web.maintain.appsys;

import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.asset.assets.AssetsMapper;
import com.koron.web.asset.assets.bean.AssetsBean;
import com.koron.web.maintain.appsys.bean.AppSysBean;
import com.koron.web.maintain.appsys.bean.AppSysQueryBean;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.swan.bean.MessageBean;

import java.util.List;

@Service
public class AppSysService {

    private static Logger log = Logger.getLogger(AppSysService.class);

    /**
     * 应用系统列表
     * @param factory
     * @param queryBean
     * @return
     */
    @TaskAnnotation("queryList")
    public List<AppSysBean> queryList(SessionFactory factory, AppSysQueryBean queryBean) {
        List<AppSysBean> list;
        try {
            AppSysMapper mapper = factory.getMapper(AppSysMapper.class);
            list = mapper.queryList(queryBean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }

    /**
     * 新增应用系统
     * @param sessionFactory
     * @param bean
     * @return
     */
    @TaskAnnotation("addAppSys")
    public MessageBean addAppSys(SessionFactory sessionFactory, AppSysBean bean) {
        MessageBean<String> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", String.class);
        try {
            AppSysMapper mapper = sessionFactory.getMapper(AppSysMapper.class);
            AssetsMapper assetmapper = sessionFactory.getMapper(AssetsMapper.class);

            //设置单据编号
//            AppSysQueryBean queryBean = new AppSysQueryBean();
//            String maxComNumber = mapper.maxComNumber();//获取当天最大的单据编号
//            String ComNumber = CommonUtil.generateStream("GY", "", maxComNumber);
//            bean.setComNumber(ComNumber);
            AssetsBean assetsBean = assetmapper.selectByPrimaryKey(bean.getSourceid());
            bean.setAssetsNumber(assetsBean.getAssetsNumber()); //回填台站编号

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
     * @param id
     * @return
     */
    @TaskAnnotation("detail")
    public AppSysBean detail(SessionFactory factory, String id) {
        try {
            AppSysMapper mapper = factory.getMapper(AppSysMapper.class);
            AppSysBean bean = mapper.selectByPrimaryKey(id);

            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 删除应用系统
     * @param sessionFactory
     * @param id
     * @return
     */
    @TaskAnnotation("deleteAppSys")
    public MessageBean deleteAppSys(SessionFactory sessionFactory, String id) {

        try {
            AppSysMapper mapper = sessionFactory.getMapper(AppSysMapper.class);
            AssetsMapper assetmapper = sessionFactory.getMapper(AssetsMapper.class);

            AppSysBean appSysBean = mapper.selectByPrimaryKey(id);
            assetmapper.deleteByPrimaryKey(appSysBean.getSourceid());//删除台账记录
            mapper.deleteByPrimaryKey(id);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 更新应用系统
     * @param sessionFactory
     * @param bean
     * @return
     */
    @TaskAnnotation("updateAppSys")
    public MessageBean updateAppSys(SessionFactory sessionFactory, AppSysBean bean) {

        try {
            AppSysMapper mapper = sessionFactory.getMapper(AppSysMapper.class);

            mapper.updateByPrimaryKeySelective(bean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }




}
