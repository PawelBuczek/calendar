package com.pb.calendar.event;

import java.time.LocalDateTime;

public abstract class Event {
    // +userId createdByUserId
    // +List<userId> hosts
    // +List<userId> guests

    // +locationId

    private String summary;
    private String details;
    private EventType type;
    private LocalDateTime timeCreated;
    private LocalDateTime timeLastEdited;

    // below two fields may have different meaning depending on the extending class
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
