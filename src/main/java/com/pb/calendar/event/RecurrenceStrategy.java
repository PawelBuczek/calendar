package com.pb.calendar.event;

import java.time.LocalDateTime;

public interface RecurrenceStrategy {
    LocalDateTime nextEventDateTime(LocalDateTime eventStartTime);
}
