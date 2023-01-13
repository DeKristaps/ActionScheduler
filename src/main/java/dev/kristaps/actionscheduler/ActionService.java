package dev.kristaps.actionscheduler;

import dev.kristaps.actionscheduler.config.ActionSchedulerConfig;
import dev.kristaps.actionscheduler.dto.CSVEntry;
import dev.kristaps.actionscheduler.util.CSVReader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class ActionService {
    private final Long SECONDS_IN_MS = 1000L;
    private final ActionSchedulerConfig config;
    private final CSVReader csvReader;
    private final Action action;
    private final Clock clock;

    public ActionService(ActionSchedulerConfig config, CSVReader csvReader, Action action, Clock clock) {
        this.config = config;
        this.csvReader = csvReader;
        this.action = action;
        this.clock = clock;
    }

    @Scheduled(fixedRateString = "${action-scheduler.runInterval}")
    public void scheduleEvaluator() throws FileNotFoundException {
        List<CSVEntry> entries = csvReader.scheduleReader(config.getFilePath());
        LocalDateTime timeToCheck = getTimeToCheck(config.getZoneID());

        entries.forEach(csvEntry -> {
            if (csvEntry.getDays().contains(timeToCheck.getDayOfWeek())) {
                LocalDateTime time = timeToCheck.toLocalDate().atTime(csvEntry.getTime());
                if (timeToCheck.isAfter(time)
                        && timeToCheck.minusSeconds(Long.parseLong(config.getRunInterval()) / SECONDS_IN_MS)
                        .isBefore(time)
                ) {
                    action.takeAction();
                }
            }
        });
    }

    private LocalDateTime getTimeToCheck(String zoneID) {
        return clock.instant().atZone(ZoneId.of(zoneID)).toLocalDateTime();
    }
}
