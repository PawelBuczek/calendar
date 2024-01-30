package com.pb.calendar.event.recurrence;

import com.pb.calendar.event.OneTimeEvent;
import com.pb.calendar.event.recurrence.strategy.RecurrenceStrategy;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class RecurringEvent {
    private OneTimeEvent firstEvent;
    private RecurrenceStrategy recurrenceStrategy;  // pattern of recurrence
    private int recurrenceCount; // if 0 then endless
    private List<IrregularEvent> irregularEvents; // exceptions to the pattern
    private LocalDateTime timeCreated;
    private LocalDateTime timeLastEdited;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public RecurringEvent(OneTimeEvent firstEvent, RecurrenceStrategy recurrenceStrategy, int recurrenceCount, LocalDateTime timeCreated, LocalDateTime timeLastEdited, LocalDateTime startTime, LocalDateTime endTime) {
        this.firstEvent = firstEvent;
        this.recurrenceStrategy = recurrenceStrategy;
        this.recurrenceCount = recurrenceCount;
        this.timeCreated = timeCreated;
        this.timeLastEdited = timeLastEdited;
        this.startTime = startTime;
        this.endTime = endTime;

        this.irregularEvents = new ArrayList<>();
    }
}
