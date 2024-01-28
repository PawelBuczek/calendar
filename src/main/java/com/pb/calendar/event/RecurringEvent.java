package com.pb.calendar.event;

import java.time.LocalDateTime;

public class RecurringEvent extends Event {
    private LocalDateTime recurrenceInterval;
    private int recurrenceCount;
}
