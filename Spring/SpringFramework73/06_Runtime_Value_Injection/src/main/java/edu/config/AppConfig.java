package edu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(basePackages = "edu.bean")
public class AppConfig {
    public AppConfig() {
        System.out.println("AppConfig constructor");
    }
}
