package dev.kristaps.actionscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ActionSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActionSchedulerApplication.class, args);
    }

}
