package dev.kristaps.actionscheduler;

import dev.kristaps.actionscheduler.config.ActionSchedulerConfig;
import dev.kristaps.actionscheduler.dto.CSVEntry;
import dev.kristaps.actionscheduler.util.CSVReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.time.*;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActionServiceTest {

    @Mock
    ActionSchedulerConfig mockConfig;

    @Mock
    Action action;

    @Mock
    Clock clock;

    @Mock
    CSVReader CSVReader;

    @InjectMocks
    ActionService service;

    @Test
    void ShouldTakeAction() throws FileNotFoundException {
        List<CSVEntry> cvsEntries = List.of(new CSVEntry(LocalTime.parse("12:00"), List.of(DayOfWeek.MONDAY)));
        LocalDateTime localDateTime = LocalDateTime.parse("2023-01-09T12:00:23.000");
        String path = "path";
        when(mockConfig.getFilePath()).thenReturn(path);
        when(CSVReader.scheduleReader(path)).thenReturn(cvsEntries);
        when(clock.instant()).thenReturn(localDateTime.atZone(ZoneId.of("Africa/Lagos")).toInstant());
        when(mockConfig.getZoneID()).thenReturn("Africa/Lagos");
        when(mockConfig.getRunInterval()).thenReturn("60000");

        service.scheduleEvaluator();
        verify(action).takeAction();
    }

    @Test
    void ShouldNOTTakeAction() throws FileNotFoundException {
        List<CSVEntry> cvsEntries = List.of(new CSVEntry(LocalTime.parse("12:00"), List.of(DayOfWeek.MONDAY)));
        LocalDateTime localDateTime = LocalDateTime.parse("2023-01-09T12:01:24.000");
        String path = "path";
        when(mockConfig.getFilePath()).thenReturn(path);
        when(CSVReader.scheduleReader(path)).thenReturn(cvsEntries);
        when(clock.instant()).thenReturn(localDateTime.atZone(ZoneId.of("Africa/Lagos")).toInstant());
        when(mockConfig.getZoneID()).thenReturn("Africa/Lagos");
        when(mockConfig.getRunInterval()).thenReturn("60000");

        service.scheduleEvaluator();
        verifyNoInteractions(action);
    }
}