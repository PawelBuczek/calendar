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

    // actually it would be better to have just the time ranges where recurring events cannot occur
    // then additional events would be in the list and there would be no need for recurringEventCounter
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
