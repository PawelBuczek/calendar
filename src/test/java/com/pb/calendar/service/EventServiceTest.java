package com.pb.calendar.service;

import com.pb.calendar.event.EventCategory;
import com.pb.calendar.event.OneTimeEvent;
import com.pb.calendar.event.recurrence.IrregularEvent;
import com.pb.calendar.event.recurrence.IrregularEventType;
import com.pb.calendar.event.recurrence.RecurringEvent;
import com.pb.calendar.event.recurrence.strategy.SimpleIntervalRecurrenceStrategy;
import com.pb.calendar.exception.EventsLoopException;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventServiceTest {

    private static final EventService eventService = new EventService();

    private RecurringEvent createRecurringEvent(LocalDateTime startTime, LocalDateTime endTime) {
        SimpleIntervalRecurrenceStrategy recurrenceStrategy = new SimpleIntervalRecurrenceStrategy(Duration.ofHours(4));

        OneTimeEvent firstEvent = new OneTimeEvent(1, "sum", "det", EventCategory.BASIC, startTime, startTime.plusMinutes(15));

        return new RecurringEvent(firstEvent, recurrenceStrategy, 0, startTime, startTime, startTime, endTime);
    }

    @Test
    void getOneTimeEventsSimpleCase() throws EventsLoopException {
        LocalDateTime time = LocalDateTime.now();
        RecurringEvent recurringEvent = createRecurringEvent(time, time.plusMonths(2));

        List<OneTimeEvent> oneTimeEvents = eventService.getOneTimeEvents(recurringEvent);

        oneTimeEvents.forEach(System.out::println);
        assertEquals(360, oneTimeEvents.size());
    }

    @Test
    void getOneTimeEventsWithIrregularEventCancelled() throws EventsLoopException {
        LocalDateTime time = LocalDateTime.now();
        RecurringEvent recurringEvent = createRecurringEvent(time, time.plusMonths(2));

        IrregularEvent irregularEvent = new IrregularEvent(1, "sum", "det", EventCategory.BASIC,
                time, time.plusMinutes(15), 3, IrregularEventType.CANCELLED);

        recurringEvent.getIrregularEvents().add(irregularEvent);

        List<OneTimeEvent> oneTimeEvents = eventService.getOneTimeEvents(recurringEvent);

        oneTimeEvents.forEach(System.out::println);
        assertEquals(359, oneTimeEvents.size());
    }

    @Test
    void getOneTimeEventsWithIrregularEventModified() throws EventsLoopException {
        LocalDateTime time = LocalDateTime.now();
        RecurringEvent recurringEvent = createRecurringEvent(time, time.plusMonths(2));

        IrregularEvent irregularEvent = new IrregularEvent(1, "sum", "det", EventCategory.BASIC,
                time, time.plusMinutes(15), 3, IrregularEventType.MODIFIED);

        recurringEvent.getIrregularEvents().add(irregularEvent);

        List<OneTimeEvent> oneTimeEvents = eventService.getOneTimeEvents(recurringEvent);

        oneTimeEvents.forEach(System.out::println);
        assertEquals(360, oneTimeEvents.size());
    }

    @Test
    void getOneTimeEventsWithIrregularEventAdded() throws EventsLoopException {
        LocalDateTime time = LocalDateTime.now();
        RecurringEvent recurringEvent = createRecurringEvent(time, time.plusMonths(2));

        IrregularEvent irregularEvent = new IrregularEvent(1, "sum", "det", EventCategory.BASIC,
                time, time.plusMinutes(20), -1, IrregularEventType.ADDED);

        recurringEvent.getIrregularEvents().add(irregularEvent);

        List<OneTimeEvent> oneTimeEvents = eventService.getOneTimeEvents(recurringEvent);

        oneTimeEvents.forEach(System.out::println);
        assertEquals(361, oneTimeEvents.size());
    }
}