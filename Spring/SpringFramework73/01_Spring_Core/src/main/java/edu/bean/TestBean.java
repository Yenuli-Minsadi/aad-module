package edu.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("exampleBean")//change testbean's bean id
//@Scope("prototype")
//another way to make this prototype
public class TestBean {
    public TestBean() {
        System.out.println("TestBean created");
    }

    public void printMessage() {
        System.out.println("Print meth");
    }
}

//with @Scope("prototype")
//AppConfig is created
//spring bean is created
//NewTestBean is called
//MyConnection created
//Bean : edu.bean.SpringBean@f5958c9
//Bean : edu.bean.SpringBean@f5958c9
//TestBean created
//Bean1 : edu.bean.TestBean@9d5509a
//TestBean created
//Bean2 : edu.bean.TestBean@179ece50
//Print meth
//Print meth
//NewTestBean : edu.newBeans.NewTestBean@3b0090a4
//MyConnection : edu.bean.MyConnection@3cd3e762


//without @Scope("prototype")
//AppConfig is created
//spring bean is created
//TestBean created
//NewTestBean is called
//MyConnection created
//Bean : edu.bean.SpringBean@6150c3ec
//Bean : edu.bean.SpringBean@6150c3ec
//Bean1 : edu.bean.TestBean@10683d9d
//Bean2 : edu.bean.TestBean@10683d9d
//Print meth
//Print meth
//NewTestBean : edu.newBeans.NewTestBean@3fc2959f
//MyConnection : edu.bean.MyConnection@5aa9e4eb
