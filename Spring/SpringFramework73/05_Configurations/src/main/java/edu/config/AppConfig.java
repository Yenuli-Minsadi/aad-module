package edu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Import({AppConfig1.class, AppConfig2.class})
@Configuration
@ComponentScan(basePackages = "edu.bean")
@ImportResource("classpath:hibernate.cfg.xml")//to import xml files in our project, relative path, inna class eke folder structure eken access krnne
//@ImportResource("file:C:folder name")  - in absolute path/hard drive/os path
public class AppConfig {
    public AppConfig() {
        System.out.println("AppConfig obj created");
    }
}
