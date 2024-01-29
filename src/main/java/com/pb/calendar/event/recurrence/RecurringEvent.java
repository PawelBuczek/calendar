package com.pb.calendar.event.recurrence;

import com.pb.calendar.event.Event;
import com.pb.calendar.event.OneTimeEvent;
import lombok.Data;

import java.util.List;

@Data
public class RecurringEvent extends Event {
    private OneTimeEvent firstEvent;
    private RecurrenceStrategy recurrenceStrategy;  // pattern of recurrence
    private int recurrenceCount; // if 0 then endless
    private List<IrregularEvent> irregularEvents; // exceptions to the pattern
}
