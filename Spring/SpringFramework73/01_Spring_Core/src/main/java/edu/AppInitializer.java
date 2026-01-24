package edu;

import edu.bean.MyConnection;
import edu.bean.SpringBean;
import edu.bean.TestBean;
import edu.config.AppConfig;
import edu.newBeans.NewTestBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();//config app context ek hdgnna new keyword eken obj ekk hdnne
        context.register(AppConfig.class);
        context.refresh();

        //Bean ID
        SpringBean springBean = (SpringBean) context.getBean("springBean");//bean id is made by SpringBean cls eke 1st letter ek simple wela
        System.out.println("Bean : "+springBean);

        //by class name
        SpringBean springBean2 = context.getBean(SpringBean.class);//bean id is made by SpringBean cls eke 1st letter ek simple wela
        System.out.println("Bean : "+springBean2);

        //by changing bean id
        TestBean testBean = (TestBean) context.getBean("exampleBean");
        System.out.println("Bean1 : "+testBean);

        //by bean id and class name
        TestBean testBean1 = context.getBean("exampleBean",TestBean.class);
        System.out.println("Bean2 : "+testBean1);

        testBean.printMessage();
        testBean1.printMessage();

        NewTestBean newTestBean = context.getBean(NewTestBean.class);
        System.out.println("NewTestBean : "+newTestBean);

        MyConnection myConnection = (MyConnection) context.getBean("connection");
        System.out.println("MyConnection : "+myConnection);



//        SpringBean springBean1 = new SpringBean();// not spring bean obj, these a POJOS objs
//        SpringBean springBean2 = new SpringBean();// new keyword dl hdna objs springbean ekk neme
//        System.out.println("POJO: "+springBean1);
//        System.out.println("POJO: "+springBean2);

//        SpringBean springBean3 = context.getBean(SpringBean.class);// componenet annotation ek dl spring context eke hdpu spring bean ek request kre not manually new keyword dl hdpu ek in order o check sif spring bean singleton
//        SpringBean springBean4 = context.getBean(SpringBean.class);// methana context means the reference
//        System.out.println("Bean: "+springBean3);//reference dekkt dammata spring bean wl ids wenas wenne na, proves spring bean is a singleton
//        System.out.println("Bean: "+springBean4);


        context.registerShutdownHook();
    }
}