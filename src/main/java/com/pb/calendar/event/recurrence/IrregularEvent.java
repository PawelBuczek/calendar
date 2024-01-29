package com.pb.calendar.event.recurrence;

import com.pb.calendar.event.Event;

public class IrregularEvent extends Event {
    private int reccuringEventId;
    private IrregularEventType irregularEventType;
    //this event is only used in RecurringEvents as a way to store exceptions to the recurrence pattern
}
