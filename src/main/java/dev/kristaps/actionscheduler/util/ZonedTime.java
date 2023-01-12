package dev.kristaps.actionscheduler.util;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class ZonedTime {
    @Value("${actionScheduler.timeZoneOffset}")
    private String timeZoneOffset;

    public ZonedTime() {
    }

    public OffsetDateTime getTimeToCheck() {
        LocalDateTime utc = LocalDateTime.now(ZoneId.of("UTC"));
        return OffsetDateTime.of(utc, ZoneOffset.of(timeZoneOffset));
    }


}
