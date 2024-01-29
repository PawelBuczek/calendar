package com.pb.calendar.event.recurrence;

import com.pb.calendar.event.OneTimeEvent;

import java.time.LocalDateTime;

public interface RecurrenceStrategy {
    OneTimeEvent nextEvent(OneTimeEvent previousEvent);
}
