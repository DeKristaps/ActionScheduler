package dev.kristaps.actionscheduler.utilTest;

import dev.kristaps.actionscheduler.dto.CSVEntry;
import dev.kristaps.actionscheduler.util.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CVSReaderTest {
    @InjectMocks
    CSVReader csvReader;

    @Test
    void shouldReadFile() throws FileNotFoundException {
        String path = "src/test/java/dev/kristaps/actionscheduler/resources/test.csv";
        List<CSVEntry> expected = List.of(
                new CSVEntry(LocalTime.parse("12:00"), List.of(DayOfWeek.MONDAY)),
                new CSVEntry(LocalTime.parse("12:46"), List.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY)));

        List<CSVEntry> actual = csvReader.scheduleReader(path);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldThrowFileNotFound() {
        String path = "src/test/java/dev/kristaps/actionscheduler/resources/nonexixtant.cvs";

        Assertions.assertThrows(FileNotFoundException.class, () -> csvReader.scheduleReader(path));
    }
}