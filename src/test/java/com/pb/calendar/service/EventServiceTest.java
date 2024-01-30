package com.pb.calendar.service;

import com.pb.calendar.event.EventCategory;
import com.pb.calendar.event.OneTimeEvent;
import com.pb.calendar.event.recurrence.RecurringEvent;
import com.pb.calendar.event.recurrence.strategy.SimpleIntervalRecurrenceStrategy;
import com.pb.calendar.exception.EventsLoopException;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

class EventServiceTest {

    @Test
    void getOneTimeEventsSimpleCase() throws EventsLoopException {
        EventService eventService = new EventService();

        SimpleIntervalRecurrenceStrategy recurrenceStrategy = new SimpleIntervalRecurrenceStrategy(Duration.ofHours(4));

        LocalDateTime time = LocalDateTime.now();
        OneTimeEvent firstEvent = new OneTimeEvent(1, "sum", "det", EventCategory.BASIC,
                time, time.plusMinutes(15));

        RecurringEvent recurringEvent = new RecurringEvent(firstEvent, recurrenceStrategy, 0,
                time, time, time, time.plusMonths(2));

        List<OneTimeEvent> oneTimeEvents = eventService.getOneTimeEvents(recurringEvent);
        oneTimeEvents.forEach(System.out::println);
        System.out.println((long) oneTimeEvents.size());

    }
}