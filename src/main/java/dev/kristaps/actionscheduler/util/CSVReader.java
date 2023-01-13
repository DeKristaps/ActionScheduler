package dev.kristaps.actionscheduler.util;

import dev.kristaps.actionscheduler.dto.CSVEntry;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CSVReader {

    public List<CSVEntry> scheduleReader(String path) throws FileNotFoundException {
        List<CSVEntry> entries = new ArrayList<>();
        Scanner sc = new Scanner(new File(path));

        while (sc.hasNext()) {
            String[] timeAndDays = sc.next().split(",");
            CSVEntry CSVEntry = new CSVEntry(LocalTime.parse(timeAndDays[0]), DayParser.parseDays(timeAndDays[1]));
            entries.add(CSVEntry);
        }
        sc.close();
        return entries;
    }
}
