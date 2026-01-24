package edu.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringBean {

    @Autowired(required = false)
    public SpringBean(@Value("Yen")String nameList[], @Value("12")int number) {
        System.out.println(nameList.length);//obj ek create weddi value set wenne na acc to lifecycle
        System.out.println(number);
//        System.out.println(address);
    }

    @Autowired(required = false)
    public SpringBean(@Value("Yen")String name, @Value("22") int age) {
        System.out.println(name);//obj ek create weddi value set wenne na acc to lifecycle
        System.out.println(age);
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println(name);
//    }
}
