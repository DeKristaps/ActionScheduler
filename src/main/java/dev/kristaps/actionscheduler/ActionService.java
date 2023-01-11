package dev.kristaps.actionscheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ActionService {

    @Scheduled(fixedDelayString = "${actionScheduler.runInterval}")
    private void scheduleReader(){


    }

}
