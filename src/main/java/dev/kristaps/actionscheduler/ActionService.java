package dev.kristaps.actionscheduler;

import dev.kristaps.actionscheduler.dto.Request;
import dev.kristaps.actionscheduler.util.DayParser;
import dev.kristaps.actionscheduler.util.ZonedTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class ActionService {
    private final Long SECONDS_IN_MS = 1000L;
    @Value("${actionScheduler.filePath}")
    private String path;

    @Value("${actionScheduler.runInterval}")
    private String runInterval;

    @Scheduled(fixedRateString = "${actionScheduler.runInterval}")
    private void scheduleEvaluator() throws FileNotFoundException {
        List<Request> requests = scheduleReader();
        ZonedTime zonedTime = new ZonedTime();

        OffsetDateTime timeToCheck = zonedTime.getTimeToCheck();

        requests.forEach(request -> {
            if (request.getDays().contains(timeToCheck.getDayOfWeek())
                    && timeToCheck.toLocalTime().isAfter(request.getTime())
                    && timeToCheck.toLocalTime().minusSeconds(Long.parseLong(runInterval) / SECONDS_IN_MS)
                    .isBefore(request.getTime())
            ) {
                System.out.println("It's time to do the action");
            }
        });
    }

    private List<Request> scheduleReader() throws FileNotFoundException {
        List<Request> requests = new ArrayList<>();
        DayParser dayParser = new DayParser();

        try {
            Scanner sc = new Scanner(new File(path));
            while (sc.hasNext()) {
                String[] timeAndDays = sc.next().split(",");
                Request request = new Request(LocalTime.parse(timeAndDays[0]), dayParser.parseDays(timeAndDays[1]));
                requests.add(request);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found!");
        }
        return requests;
    }


}
