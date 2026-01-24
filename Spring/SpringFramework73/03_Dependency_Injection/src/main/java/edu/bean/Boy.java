package edu.bean;

import org.springframework.stereotype.Component;

@Component
public class Boy {
    Girl girl = new Girl();
    public void chatWithGirl() {
        Girl girl = new Girl();
        girl.chat();
    }
}
