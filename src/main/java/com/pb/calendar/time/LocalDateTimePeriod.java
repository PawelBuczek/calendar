package com.pb.calendar.time;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

@Data
@AllArgsConstructor
public class LocalDateTimePeriod {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Duration getDuration() {
        return Duration.between(startTime, endTime);
    }

    public Period getPeriod() {
        return Period.between(startTime.toLocalDate(), endTime.toLocalDate());
    }
}


