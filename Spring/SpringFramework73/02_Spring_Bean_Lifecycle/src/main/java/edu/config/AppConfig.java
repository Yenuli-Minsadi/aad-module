package edu.config;

import edu.bean.SpringBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "edu.bean")
public class AppConfig {
    public AppConfig() {
        System.out.println("AppConfig created");
    }

    @Bean
    SpringBean springBean() {
        return new SpringBean();
    }
}
