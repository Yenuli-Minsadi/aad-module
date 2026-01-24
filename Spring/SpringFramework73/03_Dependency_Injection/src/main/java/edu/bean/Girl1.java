package edu.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class Girl1 implements Agreement, BeanNameAware, BeanFactoryAware, ApplicationContextAware, DisposableBean, InitializingBean {

    public Girl1() {
        System.out.println("Girl1 obj created");
    }
    @Override
    public void chat(){
        System.out.println("Girl chat");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("Girl setBeanFactory");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Girl setBeanName");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Girl destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Girl afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Girl setApplicationContextAware");
    }
}
