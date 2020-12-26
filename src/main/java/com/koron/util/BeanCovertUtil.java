package com.koron.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class BeanCovertUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(BeanCovertUtil.class);
    /**
     * bean对象之间的转换
     * @param <T> 目标类型
     * @param source 来源
     * @param targetClass 目标类型
     * @return 目标
     * 例子： TollinstQueryBean queryBean = BeanCovertUtil.beanCovert(bean, TollinstQueryBean.class);
     */
    public static <T> T beanCovert(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        T target = null;
        try {
            target = targetClass.newInstance();
        } catch (Exception e) {
            LOGGER.error("对象初始化错误", e);
        }
        BeanUtils.copyProperties(source, target);
        return target;
    }


    /**
     * bean列表之间的转换
     * @param <T> 目标类型
     * @param sourceList 源列表
     * @param targetClass 目标类型
     * @return 目标列表
     * @throws Exception 异常
     */
    public static <T> List<T> listCovert(List<?> sourceList, Class<T> targetClass) {
        List<T> targetList = new ArrayList<T>();
        for (Object source : sourceList) {
            if (source != null) {
                T target = null;
                try {
                    target = targetClass.newInstance();
                } catch (Exception e) {
                    LOGGER.error("对象初始化错误", e);
                }
                BeanUtils.copyProperties(source, target);
                targetList.add(target);
            }
        }
        return targetList;
    }
}
