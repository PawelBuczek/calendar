package com.pb.calendar.event;

import com.pb.calendar.time.LocalDateTimePeriod;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
public abstract class Event {
    private int id;
    private int createdByUserId;
    private String summary;
    private String details;
    private EventCategory category;
    LocalDateTimePeriod period;

    public Event(int createdByUserId, String summary, String details, EventCategory category, LocalDateTimePeriod period) {
        this.id = 1;
        this.createdByUserId = createdByUserId;
        this.summary = summary;
        this.details = details;
        this.category = category;
        this.period = period;
    }

}
