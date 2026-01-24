package edu.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SpringBean3 {
//light mode
@Bean// bean method
public SpringBean1 springBean1()
//inter-bean dependency not satisfied- only injcts a plain obj not spring bean
{
    SpringBean2 springBean2_1 = springBean2();//
    SpringBean2 springBean2_2 = springBean2();
    System.out.println("springBean2_1: " + springBean2_1);
    System.out.println("springBean2_2: " + springBean2_2);

    return new SpringBean1();
}

    @Bean
    public SpringBean2 springBean2() {
        return new SpringBean2();
    }
}
