package edu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "edu.bean")
public class AppConfig {
    public AppConfig() {
        System.out.println("AppConfig obj created");
    }
}
