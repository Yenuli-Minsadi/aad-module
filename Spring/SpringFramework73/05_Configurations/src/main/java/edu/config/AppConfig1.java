package edu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "edu.bean")
public class AppConfig1 {
    public AppConfig1() {
        System.out.println("AppConfig1 obj created");
    }
}
