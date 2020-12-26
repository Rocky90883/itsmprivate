package com.koron.web.asset.assetregist;

import com.koron.common.bean.StaffBean;
import com.koron.common.web.mapper.StaffMapper;
import com.koron.util.CommonUtil;
import com.koron.util.Constant;
import com.koron.util.SessionUtil;
import com.koron.web.asset.assetregist.bean.AssetRegistBean;
import com.koron.web.asset.assetregist.bean.AssetRegistQueryBean;
import com.koron.web.asset.assettype.AssetTypeMapper;
import com.koron.web.asset.assettype.bean.AssetTypeBean;
import com.koron.web.asset.assettype.bean.AssetTypeQueryBean;
import com.koron.web.asset.scrapped.ScrappedMapper;
import com.koron.web.asset.scrapped.bean.ScrappedBean;
import com.koron.web.systemmanger.UnifiedUserImport;
import com.koron.web.systemmanger.user.UserMapper;
import com.koron.web.systemmanger.user.bean.StaffunBean;
import com.koron.web.systemmanger.user.bean.StaffunQueryBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.koron.ebs.mybatis.SessionFactory;
import org.koron.ebs.mybatis.TaskAnnotation;
import org.springframework.stereotype.Service;
import org.swan.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssetRegistService {

    private static Logger log = Logger.getLogger(AssetRegistService.class);

    /**
     * 资产登记列表
     * @param factory
     * @param queryBean
     * @return
     */
    @TaskAnnotation("queryList")
    public List<AssetRegistBean> queryList(SessionFactory factory, AssetRegistQueryBean queryBean) {
        List<AssetRegistBean> regislist;
        try {
            AssetRegistMapper mapper = factory.getMapper(AssetRegistMapper.class);
            regislist = mapper.queryList(queryBean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return regislist;
    }

    /**
     * 详情
     * @param factory
     * @param id
     * @return
     */
    @TaskAnnotation("detail")
    public AssetRegistBean detail(SessionFactory factory, String id) {
        try {
            AssetRegistMapper mapper = factory.getMapper(AssetRegistMapper.class);
            AssetRegistBean bean = mapper.selectByPrimaryKey(id);

            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 新增资产登记
     * @param sessionFactory
     * @param bean
     * @return
     */
    @TaskAnnotation("addRegist")
    public MessageBean addRegist(SessionFactory sessionFactory, AssetRegistBean bean) {

        MessageBean<String> success = MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", String.class);
        try {
            AssetRegistMapper mapper = sessionFactory.getMapper(AssetRegistMapper.class);
            bean.setId(CommonUtil.get32Key());
            bean.setOverQty(Double.valueOf("0"));

            //设置单据编号
            AssetRegistQueryBean queryBean = new AssetRegistQueryBean();
            String maxBillNo = mapper.maxBillNo();//获取当天最大的单据编号
            String billNo = CommonUtil.generateStream("DJ", "", maxBillNo);
            bean.setBillNo(billNo);

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
     * 删除资产登记
     * @param sessionFactory
     * @param id
     * @return
     */
    @TaskAnnotation("deleteRegist")
    public MessageBean deleteRegist(SessionFactory sessionFactory, String id) {

        try {
            AssetRegistMapper mapper = sessionFactory.getMapper(AssetRegistMapper.class);
            AssetRegistBean delbean = mapper.selectByPrimaryKey(id);
            if (delbean.getOverQty()>0) {
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "存在已分配记录不允许删除", void.class);
            }
            mapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 更新资产登记
     * @param sessionFactory
     * @param bean
     * @return
     */
    @TaskAnnotation("updateRegist")
    public MessageBean updateRegist(SessionFactory sessionFactory, AssetRegistBean bean) {

        try {
            AssetRegistMapper mapper = sessionFactory.getMapper(AssetRegistMapper.class);
            AssetRegistBean oldbean = mapper.selectByPrimaryKey(bean.getId());

            if(oldbean.getOverQty()>0){
                if(!oldbean.getGoodsModel().equals(bean.getGoodsModel()) ||
                        !oldbean.getAssetTypeId().equals(bean.getAssetTypeId())){
                    return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "资产存在分配记录不允许修改类型、型号", void.class);
                }
            }

            //如果修个的登记数比 未分配数要小不允许修改
            if (bean.getQty()<oldbean.getOverQty()) {
                return MessageBean.create(Constant.MESSAGE_INT_OPERATION, "登记数小于未分配数不允许修改", void.class);
            }
            mapper.updateByPrimaryKeySelective(bean);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return MessageBean.create(Constant.MESSAGE_INT_SUCCESS, "success", void.class);
    }

}
