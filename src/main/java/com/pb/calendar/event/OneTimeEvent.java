package com.pb.calendar.event;

import java.time.LocalDateTime;

public class OneTimeEvent extends Event {
    public OneTimeEvent(int createdByUserId, String summary, String details, EventCategory category, LocalDateTime startTime, LocalDateTime endTime) {
        super(createdByUserId, summary, details, category, startTime, endTime);
    }

}
