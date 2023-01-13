package dev.kristaps.actionscheduler.util;

import dev.kristaps.actionscheduler.dto.CVSEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

class CVSReaderTest {

    @Test
    void scheduleReaderCanReadFile() throws FileNotFoundException {
        String path = "src/test/java/dev/kristaps/actionscheduler/resources/test.csv";
        List<CVSEntry> expected = List.of(new CVSEntry(LocalTime.parse("12:00"), List.of(DayOfWeek.MONDAY)));

        List<CVSEntry> actual = CVSReader.scheduleReader(path);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void scheduleReaderThrowsFileNotFound() {
        String path = "src/test/java/dev/kristaps/actionscheduler/resources/nonexixtant.cvs";

        Assertions.assertThrows(FileNotFoundException.class, () -> CVSReader.scheduleReader(path));
    }
}