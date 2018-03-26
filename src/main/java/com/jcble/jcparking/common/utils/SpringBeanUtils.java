package com.jcble.jcparking.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * spring bean实体获取工具类
 * 
 * @author Administrator
 * 
 */
public abstract class SpringBeanUtils {
    private static final Logger log = LoggerFactory.getLogger(SpringBeanUtils.class);

    // 获取spring上下文对象
    private static WebApplicationContext applicationContext = null;

    /**
     * Title:
     * <p>
     * Description:获取spring WebApplicationContext
     * </p>
     * 
     * @return
     */
    public static WebApplicationContext getWebApplicationContext() {
        if (applicationContext == null) {
            applicationContext = ContextLoader.getCurrentWebApplicationContext();
        }
        return applicationContext;
    }

    /**
     * 获取一个SpringBean服务
     * 
     * @param pBeanId
     *            Spring配置文件名中配置的SpringID号
     * @return Object 返回的SpringBean实例
     */
    public static Object getSpringBean(String pBeanId) {
        Object springBean = null;
        try {
            getWebApplicationContext();
            springBean = applicationContext.getBean(pBeanId);
        } catch (BeansException e) {
            log.error("Spring配置文件中没有匹配到ID号为:[" + pBeanId + "]的SpringBean组件,请检查!", e);
        }
        return springBean;
    }

    /**
     * Title:
     * <p>
     * Description:获取spring bean对象
     * </p>
     * 
     * @param className
     * @return
     */
    public static <T> T getSpringBean(Class<T> className) {
        T springBean = null;
        try {
            getWebApplicationContext();
            springBean = applicationContext.getBean(className);
        } catch (BeansException e) {
            log.error("Spring配置文件中没有匹配到Class名称为号为:[" + className.getName() + "]的SpringBean组件,请检查!", e);
        }
        return springBean;
    }
}
