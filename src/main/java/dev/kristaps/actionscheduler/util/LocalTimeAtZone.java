package dev.kristaps.actionscheduler.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalTimeAtZone {

    public static LocalDateTime getTimeToCheck(String zoneID) {
        return LocalDateTime.now(ZoneId.of(zoneID));
    }
}
