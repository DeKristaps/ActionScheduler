package dev.kristaps.actionscheduler.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;


@Configuration
@ConfigurationProperties(prefix = "action-scheduler")
public class ActionSchedulerConfig {

    private String runInterval;
    private String filePath;
    private String zoneID;

    public String getRunInterval() {
        return runInterval;
    }

    public void setRunInterval(String runInterval) {
        this.runInterval = runInterval;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getZoneID() {
        return zoneID;
    }

    public void setZoneID(String zoneID) {
        this.zoneID = zoneID;
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
