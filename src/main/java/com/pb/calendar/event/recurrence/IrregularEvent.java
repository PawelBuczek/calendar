package com.pb.calendar.event.recurrence;

import com.pb.calendar.event.Event;
import com.pb.calendar.event.EventCategory;
import com.pb.calendar.event.OneTimeEvent;
import com.pb.calendar.time.LocalDateTimePeriod;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class IrregularEvent extends Event {
    private int recurringEventCounter;
    private IrregularEventType irregularEventType;

    public IrregularEvent(int createdByUserId, String summary, String details, EventCategory category, LocalDateTime startTime, LocalDateTime endTime, int recurringEventCounter, IrregularEventType irregularEventType) {
        super(createdByUserId, summary, details, category, new LocalDateTimePeriod(startTime, endTime));
        this.recurringEventCounter = recurringEventCounter;
        this.irregularEventType = irregularEventType;
    }

    public IrregularEvent(OneTimeEvent event, int recurringEventCounter, IrregularEventType irregularEventType) {
        super(event.getCreatedByUserId(),
                event.getSummary(),
                event.getDetails(),
                event.getCategory(),
                event.getPeriod());
        this.recurringEventCounter = recurringEventCounter;
        this.irregularEventType = irregularEventType;
    }
    //this event is only used in RecurringEvents as a way to store exceptions to the recurrence pattern
}
