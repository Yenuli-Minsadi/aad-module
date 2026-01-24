package edu.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


public class SpringBean2 implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, DisposableBean, InitializingBean {
    public SpringBean2() {
        System.out.println("SpringBean2 Obj Created");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("SB2 setBeanFactory");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("SB2 setBeanName");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("SB2 destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("SB2 afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("SB2 setApplicationContext");
    }
}
