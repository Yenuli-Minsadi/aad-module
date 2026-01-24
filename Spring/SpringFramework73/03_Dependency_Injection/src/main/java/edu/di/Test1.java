package edu.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Test1 implements DiInterface{

    //property injection, field injection
    DI di;

//    @Autowired
//    public Test1(DI di) {
//        this.di = di;
//
//    }

    //setter method thru injection
//    @Autowired
//    public void setDi(DI di) {
//        this.di = di;
//    }

    public void chatWithTest2(){
        di.chat();
    }

    //interface thru DI
    @Autowired
    @Override
    public void inject(DI di) {
        this.di = di;
    }
}
