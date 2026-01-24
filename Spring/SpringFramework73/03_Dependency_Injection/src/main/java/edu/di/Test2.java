package edu.di;

import org.springframework.stereotype.Component;

@Component
public class Test2 implements DI {


    @Override
    public void chat() {
        System.out.println("Test2 chat");
    }
}
