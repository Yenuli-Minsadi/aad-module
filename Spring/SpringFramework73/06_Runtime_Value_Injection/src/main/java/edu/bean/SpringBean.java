package edu.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringBean {
   
    public SpringBean(@Value("Yen")String name) {
        System.out.println(name);//obj ek create weddi value set wenne na acc to lifecycle
    }


//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println(name);
//    }
}
