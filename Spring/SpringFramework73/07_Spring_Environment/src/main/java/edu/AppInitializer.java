package edu;

import edu.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.Properties;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();//config app context ek hdgnna new keyword eken obj ekk hdnne
        context.register(AppConfig.class);
        context.refresh();

        //Types of env files;
        //System Variable -OS Related
            Map<String,String> systemVariables = System.getenv();//returns map of string values
            for(String name : systemVariables.keySet()){
                System.out.println(name+"="+systemVariables.get(name));
            }

        //Java properties - Java lang related
        Properties properties = System.getProperties();
            for(Object key : properties.keySet()){
                System.out.println(key+"="+properties.getProperty(key.toString()));
            }
        //Resource bundles(.env, .properties, .yml, .yaml)
        context.registerShutdownHook();
    }
}