package com.pb.calendar.event;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public abstract class Event {
    private int id;
    private int createdByUserId;
    private String summary;
    private String details;
    private EventCategory category;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", createdByUserId=" + createdByUserId +
                ", summary='" + summary + '\'' +
                ", details='" + details + '\'' +
                ", category=" + category +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public Event(int createdByUserId, String summary, String details, EventCategory category, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = 1;
        this.createdByUserId = createdByUserId;
        this.summary = summary;
        this.details = details;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
