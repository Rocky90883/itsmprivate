package com.koron.util;

import com.koron.web.asset.assetregist.bean.AssetRegistBean;
import com.koron.web.asset.assets.attributes.AttributesBean;
import com.koron.web.asset.assets.bean.ImportAssetBean;

public class ConversionUtil {


    /**
     * 登记转属性
     * @param registBean
     * @param atbBean
     * @return
     */
    public static AttributesBean registCoattributes(AssetRegistBean registBean, AttributesBean atbBean){

        atbBean.setCpu(registBean.getCpu());            //cpu
        atbBean.setRam(registBean.getRam());            //内存
        atbBean.setDisk(registBean.getDisk());          //硬盘
        atbBean.setWattage(registBean.getWattage());    //瓦数
        atbBean.setPortqty(registBean.getPortqty());    //接口数
        atbBean.setBandwidth(registBean.getBandwidth());//带宽
        atbBean.setSpec(registBean.getSpec());          //规格
        atbBean.setEdition(registBean.getEdition());    //版本
        atbBean.setWarranty(registBean.getWarranty());  //保修年限

        return atbBean;
    }

    /**
     * 资产导入模型 转 资产属性模型
     * @param bean
     * @param atbBean
     * @return
     */
    public static AttributesBean impCoattributes(ImportAssetBean bean, AttributesBean atbBean){

        atbBean.setCpu(bean.getCpu());            //cpu
        atbBean.setRam(bean.getRam());            //内存
        atbBean.setDisk(bean.getDisk());          //硬盘
        atbBean.setWattage(bean.getWattage());    //瓦数
        atbBean.setPortqty(bean.getPortqty());    //接口数
        atbBean.setBandwidth(bean.getBandwidth());//带宽
        atbBean.setSpec(bean.getSpec());          //规格
        atbBean.setEdition(bean.getEdition());    //版本
        atbBean.setWarranty(bean.getWarranty());  //保修年限

        return atbBean;
    }


}
