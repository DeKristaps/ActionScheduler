package dev.kristaps.actionscheduler;

import org.springframework.stereotype.Component;

@Component
public class Action {
    public void takeAction() {
        System.out.println("Its time to do something");
    }
}
