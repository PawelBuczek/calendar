package com.pb.calendar.service;

import com.pb.calendar.event.EventCategory;
import com.pb.calendar.event.OneTimeEvent;
import com.pb.calendar.event.recurrence.RecurringEvent;
import com.pb.calendar.event.recurrence.strategy.RecurrenceStrategy;
import com.pb.calendar.event.recurrence.strategy.SimpleIntervalRecurrenceStrategy;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

class EventServiceTest {

    @Test
    @SneakyThrows
    void getOneTimeEvents() {
        EventService eventService = new EventService();

        SimpleIntervalRecurrenceStrategy recurrenceStrategy = new SimpleIntervalRecurrenceStrategy();
        recurrenceStrategy.setSimpleInterval(Duration.ofHours(4));

        LocalDateTime time = LocalDateTime.now();
        OneTimeEvent firstEvent = new OneTimeEvent(1, "summ",
                "det", EventCategory.BASIC, time, time, time, time.plusMinutes(15));

        RecurringEvent recurringEvent = new RecurringEvent(firstEvent, recurrenceStrategy, 0,time,time,time,time.plusMonths(2));

        List<OneTimeEvent> oneTimeEvents = eventService.getOneTimeEvents(recurringEvent);
        System.out.println(oneTimeEvents);

    }
}