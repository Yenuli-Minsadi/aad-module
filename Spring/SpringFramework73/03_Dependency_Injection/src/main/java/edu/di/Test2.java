package edu.di;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("Prototype")
public class Test2 implements DI {

    @Override
    public void chat() {
        System.out.println("Test2 chat");
    }
}
