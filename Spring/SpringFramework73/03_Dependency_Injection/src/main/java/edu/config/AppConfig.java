package edu.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "edu.di")
public class AppConfig {
    public AppConfig() {
        System.out.println("AppConfig created");
    }

}
