package dev.kristaps.actionscheduler;

import dev.kristaps.actionscheduler.dto.Request;
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

    @Value("${actionScheduler.filePath}")
    private String path;

    @Value("${actionScheduler.runInterval}")
    private String runInterval;

    @Value("${actionScheduler.timeZoneOffset}")
    private String timeZoneOffset;


    @Scheduled(fixedRateString = "${actionScheduler.runInterval}")
    private void scheduleEvaluator() {
        List<Request> requests = scheduleReader();
        OffsetDateTime timeToCheck = getTimeToCheck();

        //TODO Must arrange conditional statement so it accounts for run Interval
        requests.forEach(request -> {
            if (request.getDays().contains(timeToCheck.getDayOfWeek())
                    && request.getTime().isAfter(timeToCheck.toLocalTime())
            ){
                System.out.println("It's time to do the action");
            }
        });
    }

    private List<Request> scheduleReader() {
        List<Request> requests = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(path));
            while (sc.hasNext()) {
                String[] timeAndDays = sc.next().split(",");
                Request request = new Request(LocalTime.parse(timeAndDays[0]), parseDays(timeAndDays[1]));
                requests.add(request);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return requests;
    }

    private List<DayOfWeek> parseDays(String days) {
        List<DayOfWeek> dayList = new ArrayList<>();
        String binaryDays = Integer.toBinaryString(Integer.parseInt(days));
        int day = 1;

        for (int i = binaryDays.length()-1; i >= 0; i--) {
            if (binaryDays.charAt(i) == '1') {
                dayList.add(DayOfWeek.of(day));
            }
            day++;
        }
        return dayList;
    }

    private OffsetDateTime getTimeToCheck() {
        LocalDateTime utc = LocalDateTime.now(ZoneId.of("UTC"));
        return OffsetDateTime.of(utc, ZoneOffset.of(timeZoneOffset));
    }
