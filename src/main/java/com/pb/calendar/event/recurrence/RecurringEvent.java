package com.pb.calendar.event.recurrence;

import com.pb.calendar.event.Event;
import com.pb.calendar.event.OneTimeEvent;
import com.pb.calendar.event.recurrence.strategy.RecurrenceStrategy;
import lombok.Data;

import java.util.List;

@Data
public class RecurringEvent extends Event {
    private int id;
    private OneTimeEvent firstEvent;
    private RecurrenceStrategy recurrenceStrategy;  // pattern of recurrence
    private int recurrenceCount; // if 0 then endless
    private List<IrregularEvent> irregularEvents; // exceptions to the pattern

    public RecurringEvent(OneTimeEvent firstEvent, RecurrenceStrategy recurrenceStrategy, int recurrenceCount) {
        this.id = 1;
        this.firstEvent = firstEvent;
        this.recurrenceStrategy = recurrenceStrategy;
        this.recurrenceCount = recurrenceCount;
    }
}
