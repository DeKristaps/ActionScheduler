package dev.kristaps.actionscheduler.util;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class DayParser {

    public static List<DayOfWeek> parseDays(String days) {
        List<DayOfWeek> dayList = new ArrayList<>();
        String binaryDays = Integer.toBinaryString(Integer.parseInt(days));
        int day = 1;

        for (int i = binaryDays.length() - 1; i >= 0; i--) {
            if (binaryDays.charAt(i) == '1') {
                dayList.add(DayOfWeek.of(day));
            }
            day++;
        }
        return dayList;
    }
}
