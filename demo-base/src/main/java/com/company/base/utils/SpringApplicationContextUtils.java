package com.company.base.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * 获取Spring Bean容器-ApplicationContext
 */
@Component
public class SpringApplicationContextUtils implements ApplicationContextAware{
    private static ApplicationContext ap ;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ap = applicationContext;
    }

    /**
     * 根据注解类从容器获取对象
     * @param annotationType 注解类
     * @return
     */
    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType){
        return ap.getBeansWithAnnotation(annotationType);
    }

    /**
     * 根据java class类型从容器中获取对象
     * @param type 类型class
     * @param <T> 类型
     * @return
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> type){
        return ap.getBeansOfType(type);
    }

}
