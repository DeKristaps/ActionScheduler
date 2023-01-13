package dev.kristaps.actionscheduler;

import dev.kristaps.actionscheduler.config.ActionSchedulerConfig;
import dev.kristaps.actionscheduler.dto.CVSEntry;
import dev.kristaps.actionscheduler.util.CVSReader;
import dev.kristaps.actionscheduler.util.LocalTimeAtZone;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActionService {
    private final Long SECONDS_IN_MS = 1000L;
    private final ActionSchedulerConfig config;

    public ActionService(ActionSchedulerConfig config) {
        this.config = config;
    }

    @Scheduled(fixedRateString = "${action-scheduler.runInterval}")
    private void scheduleEvaluator() throws FileNotFoundException {
        List<CVSEntry> entries = CVSReader.scheduleReader(config.getFilePath());
        LocalDateTime timeToCheck = LocalTimeAtZone.getTimeToCheck(config.getZoneID());

        entries.forEach(CVSEntry -> {
            if (CVSEntry.getDays().contains(timeToCheck.getDayOfWeek())) {
                LocalDateTime time = timeToCheck.toLocalDate().atTime(CVSEntry.getTime());
                if (timeToCheck.isAfter(time)
                        && timeToCheck.minusSeconds(Long.parseLong(config.getRunInterval()) / SECONDS_IN_MS)
                        .isBefore(time)
                ) {
                    Action.takeAction();
                }
            }
        });
    }
}
