package dev.kristaps.actionscheduler.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class CVSEntry {
    LocalTime time;
    List<DayOfWeek> days;

    public CVSEntry(LocalTime time, List<DayOfWeek> days) {
        this.time = time;
        this.days = days;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public List<DayOfWeek> getDays() {
        return days;
    }

    public void setDays(List<DayOfWeek> days) {
        this.days = days;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CVSEntry CVSEntry = (CVSEntry) o;
        return time.equals(CVSEntry.time) && days.equals(CVSEntry.days);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, days);
    }

    @Override
    public String toString() {
        return "CVSEntry{" +
                "time=" + time +
                ", days=" + days +
                '}';
    }
}

