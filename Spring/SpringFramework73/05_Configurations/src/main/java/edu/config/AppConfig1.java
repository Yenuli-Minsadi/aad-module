package edu.config;

import edu.bean.ABean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig1 {
    public AppConfig1() {
        System.out.println("AppConfig1 obj created");
    }

    @Bean
    public ABean aBean() {
        return new ABean();
    }
}
