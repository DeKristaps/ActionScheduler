package dev.kristaps.actionscheduler.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.List;

class DayParserTest {

    @Test
    void parseDaysTest() {
        List<DayOfWeek> expectedDays = List.of(DayOfWeek.MONDAY);
        String input = "1";

        List<DayOfWeek> actual = DayParser.parseDays(input);

        Assertions.assertEquals(expectedDays, actual);
    }
}