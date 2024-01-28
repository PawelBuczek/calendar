package com.pb.calendar.event;

import java.time.LocalDateTime;
import java.util.List;

public class RecurringEvent extends Event {
    private LocalDateTime recurrenceInterval;
    private int recurrenceCount;
    private List<IrregularEvent> irregularEvents;
}
