package edu.bean;

import org.springframework.stereotype.Component;

@Component
public class Boy {
    Girl girl;

    public Boy(){
        girl=new Girl();
    }
    public void chatWithGirl() {
        girl.chat();
    }
}
