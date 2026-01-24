package edu.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Test1 {

    //property injection, field injection
    @Autowired
    DI di = new Test2();


    public void chatWithTest2(){
        di.chat();
    }
}
