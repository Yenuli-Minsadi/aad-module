package edu.config;


import edu.bean.SpringBean1;
import edu.bean.SpringBean2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = "edu.bean")
public class AppConfig {
    public AppConfig() {
        System.out.println("AppConfig created");
    }
//full mode
//    @Bean// bean method
//    public SpringBean1 springBean1()
    //inter- bean dependency satisfied - only injects a bean obj
//    {
//        SpringBean2 springBean2_1 = springBean2();//SB2 hdl eyge ref ek argttata passe SB1 ek hdnw
//        SpringBean2 springBean2_2 = springBean2();
//        System.out.println("springBean2_1: " + springBean2_1);
//        System.out.println("springBean2_2: " + springBean2_2);
//
//        return new SpringBean1();
//    }
//
//    @Bean
//    public SpringBean2 springBean2() {
//        return new SpringBean2();
//    }

}
