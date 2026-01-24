package edu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({AppConfig1.class, AppConfig2.class})
@Configuration
@ComponentScan(basePackages = "edu.bean")
public class AppConfig {
    public AppConfig() {
        System.out.println("AppConfig obj created");
    }
}
