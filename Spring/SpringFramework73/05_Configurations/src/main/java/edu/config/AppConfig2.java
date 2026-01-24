package edu.config;

import edu.bean.ABean;
import edu.bean.BBean;
import edu.bean.CBean;
import edu.bean.DBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig2 {
    public AppConfig2() {
        System.out.println("AppConfig2 obj created");
    }

    @Bean
    public CBean cBean() {
        return new CBean();
    }

    @Bean
    public DBean dBean() {
        return new DBean();
    }
}
