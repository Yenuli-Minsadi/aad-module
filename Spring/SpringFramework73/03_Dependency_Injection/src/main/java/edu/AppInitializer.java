package edu;

import edu.bean.Boy;
import edu.bean.SpringBean;
import edu.config.AppConfig;
import edu.di.Test1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();//config app context ek hdgnna new keyword eken obj ekk hdnne
        context.register(AppConfig.class);
        context.refresh();

        Test1 t1 = context.getBean(Test1.class);
        t1.chatWithTest2();

        context.registerShutdownHook();//if this is gone the beans will stay without destroying
    }
}