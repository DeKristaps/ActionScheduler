package dev.kristaps.actionscheduler.util;

import dev.kristaps.actionscheduler.dto.CVSEntry;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CVSReader {

    public static List<CVSEntry> scheduleReader(String path) throws FileNotFoundException {
        List<CVSEntry> entries = new ArrayList<>();
        Scanner sc = new Scanner(new File(path));

        while (sc.hasNext()) {
            String[] timeAndDays = sc.next().split(",");
            CVSEntry CVSEntry = new CVSEntry(LocalTime.parse(timeAndDays[0]), DayParser.parseDays(timeAndDays[1]));
            entries.add(CVSEntry);
        }
        sc.close();
        return entries;
    }
}
