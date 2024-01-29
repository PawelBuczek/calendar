package com.pb.calendar.event.recurrence;

import com.pb.calendar.event.OneTimeEvent;

public interface RecurrenceStrategy {
    OneTimeEvent nextEvent(OneTimeEvent previousEvent);
    // would be good to have some way of checking if created recurrence strategy is correct
    // and doesn't for example produce infinite loops of events
}
