package com.pb.calendar.event.recurrence;

import com.pb.calendar.event.Event;
import com.pb.calendar.event.EventCategory;
import com.pb.calendar.event.OneTimeEvent;

import java.time.LocalDateTime;

public class IrregularEvent extends Event {
    private int recurringEventCounter;
    private IrregularEventType irregularEventType;

    public IrregularEvent(int recurringEventCounter, int createdByUserId, String summary, String details, EventCategory category, LocalDateTime startTime, LocalDateTime endTime, IrregularEventType irregularEventType) {
        super(createdByUserId, summary, details, category, startTime, endTime);
        this.recurringEventCounter = recurringEventCounter;
        this.irregularEventType = irregularEventType;
    }

    public IrregularEvent(int recurringEventCounter, IrregularEventType irregularEventType, OneTimeEvent event) {
        super(event.getCreatedByUserId(),
                event.getSummary(),
                event.getDetails(),
                event.getCategory(),
                event.getStartTime(),
                event.getEndTime());
        this.recurringEventCounter = recurringEventCounter;
        this.irregularEventType = irregularEventType;
    }
    //this event is only used in RecurringEvents as a way to store exceptions to the recurrence pattern
}
