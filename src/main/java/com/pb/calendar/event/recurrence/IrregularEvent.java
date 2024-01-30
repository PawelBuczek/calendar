package com.pb.calendar.event.recurrence;

import com.pb.calendar.event.Event;
import com.pb.calendar.event.EventCategory;

import java.time.LocalDateTime;

public class IrregularEvent extends Event {
    private int reccuringEventId;
    private IrregularEventType irregularEventType;

    public IrregularEvent(int createdByUserId, String summary, String details, EventCategory category, LocalDateTime timeCreated, LocalDateTime timeLastEdited, LocalDateTime startTime, LocalDateTime endTime) {
        super(createdByUserId, summary, details, category, timeCreated, timeLastEdited, startTime, endTime);
    }
    //this event is only used in RecurringEvents as a way to store exceptions to the recurrence pattern
}
