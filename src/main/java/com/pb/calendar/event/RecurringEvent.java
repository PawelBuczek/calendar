package com.pb.calendar.event;

import java.util.List;

public class RecurringEvent extends Event {
    private OneTimeEvent firstEvent;
    private RecurrenceStrategy recurrenceStrategy;  // pattern of recurrence
    private int recurrenceCount; // if 0 then endless
    private List<IrregularEvent> irregularEvents; // exceptions to the pattern
}
