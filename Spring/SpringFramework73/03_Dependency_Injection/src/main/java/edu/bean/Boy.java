package edu.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Boy {
    @Autowired// able to satisfy dependency injection, not ideal to field inject rather constructor thru inject
    @Qualifier("girl1")
    Agreement agreement;
//    Girl girl;

    public Boy(){
        System.out.println("Boy obj created");
    }
    public void chatWithGirl() {
        agreement.chat();
    }
}
