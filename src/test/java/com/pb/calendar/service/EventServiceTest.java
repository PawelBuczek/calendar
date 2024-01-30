package com.pb.calendar.service;

import com.pb.calendar.event.OneTimeEvent;
import com.pb.calendar.event.recurrence.RecurringEvent;
import com.pb.calendar.event.recurrence.strategy.RecurrenceStrategy;
import com.pb.calendar.event.recurrence.strategy.SimpleIntervalRecurrenceStrategy;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.time.Duration;

class EventServiceTest {

    @Test
    @SneakyThrows
    void getOneTimeEvents() {
        EventService eventService = new EventService();

        SimpleIntervalRecurrenceStrategy recurrenceStrategy = new SimpleIntervalRecurrenceStrategy();
        recurrenceStrategy.setSimpleInterval(Duration.ofHours(4));

        OneTimeEvent firstEvent = new OneTimeEvent();

        RecurringEvent recurringEvent = new RecurringEvent(firstEvent, recurrenceStrategy, 0);

        eventService.getOneTimeEvents(recurringEvent);
    }
}