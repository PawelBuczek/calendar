package com.pb.calendar.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class Event {
    private int id;
    private int createdByUserId;
    // +List<userId> hosts
    // +List<userId> guests

    // +locationId

    private String summary;
    private String details;
    private EventCategory category;
    private LocalDateTime timeCreated;
    private LocalDateTime timeLastEdited;

    // below two fields may have different meaning depending on the extending class
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
