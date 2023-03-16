package com.example.red_package_project.pojo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Date 16/3/2023 0016 上午 10:48
 * @Description Aware接口
 * @Version 1.0.0
 * @Author liwenbo
 */
public class MyUser implements BeanFactoryAware, ApplicationContextAware, InitializingBean, BeanPostProcessor {

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    private BeanFactory beanFactory;

    private ApplicationContext applicationContext;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
