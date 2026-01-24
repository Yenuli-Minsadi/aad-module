package edu;

import edu.config.AppConfig;
import edu.config.AppConfig1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();//config app context ek hdgnna new keyword eken obj ekk hdnne
        context.register(AppConfig.class);
        context.register(AppConfig1.class);
        context.refresh();

        context.registerShutdownHook();
    }
}