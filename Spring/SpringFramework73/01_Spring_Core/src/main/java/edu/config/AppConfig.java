package edu.config;

import edu.bean.MyConnection;
import edu.newBeans.NewTestBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration//component
@ComponentScan(basePackages= {"edu.bean", "edu.newBeans"})// scan and link all beans in the application with bean path, spring bean/components okkoma meken aran awilla
//@ComponentScan(basePackageClasses = NewTestBean.class)
public class AppConfig {
    public AppConfig() {
        System.out.println("AppConfig is created");
    }

    @Bean("connection")//change bean id, default bean id is bean method
    MyConnection yConnection() {
        return new MyConnection();
    }//bean id is bean method name
}
