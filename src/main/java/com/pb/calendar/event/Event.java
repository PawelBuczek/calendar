package com.pb.calendar.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class Event {
    private int id;

    public Event(int createdByUserId, String summary, String details, EventCategory category, LocalDateTime timeCreated, LocalDateTime timeLastEdited, LocalDateTime startTime, LocalDateTime endTime) {
        this.createdByUserId = createdByUserId;
        this.summary = summary;
        this.details = details;
        this.category = category;
        this.timeCreated = timeCreated;
        this.timeLastEdited = timeLastEdited;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private int createdByUserId;
    // +List<userId> hosts
    // +List<userId> guests

    // +locationId

    private String summary;
    private String details;
    private EventCategory category;
    private LocalDateTime timeCreated;
    private LocalDateTime timeLastEdited;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
