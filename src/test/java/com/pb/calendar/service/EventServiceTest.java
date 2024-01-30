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

class EventServiceTest {
    private static final EventService eventService = new EventService();

    @Test
    void getOneTimeEventsSimpleCase() throws EventsLoopException {
        SimpleIntervalRecurrenceStrategy recurrenceStrategy = new SimpleIntervalRecurrenceStrategy(Duration.ofHours(4));

        LocalDateTime time = LocalDateTime.now();
        OneTimeEvent firstEvent = new OneTimeEvent(1, "sum", "det", EventCategory.BASIC,
                time, time.plusMinutes(15));

        RecurringEvent recurringEvent = new RecurringEvent(firstEvent, recurrenceStrategy, 0,
                time, time, time, time.plusMonths(2));

        List<OneTimeEvent> oneTimeEvents = eventService.getOneTimeEvents(recurringEvent);
        oneTimeEvents.forEach(System.out::println);

        assert oneTimeEvents.size() == 360;

    }

    @Test
    void getOneTimeEventsWithIrregularEventCancelled() throws EventsLoopException {
        SimpleIntervalRecurrenceStrategy recurrenceStrategy = new SimpleIntervalRecurrenceStrategy(Duration.ofHours(4));

        LocalDateTime time = LocalDateTime.now();
        OneTimeEvent firstEvent = new OneTimeEvent(1, "sum", "det", EventCategory.BASIC,
                time, time.plusMinutes(15));

        RecurringEvent recurringEvent = new RecurringEvent(firstEvent, recurrenceStrategy, 0,
                time, time, time, time.plusMonths(2));

        IrregularEvent irregularEvent = new IrregularEvent(1, "sum", "det", EventCategory.BASIC,
                time, time.plusMinutes(15), 3, IrregularEventType.CANCELLED);

        recurringEvent.getIrregularEvents().add(irregularEvent);

        List<OneTimeEvent> oneTimeEvents = eventService.getOneTimeEvents(recurringEvent);
        oneTimeEvents.forEach(System.out::println);
        assert oneTimeEvents.size() == 359;
    }

    @Test
    void getOneTimeEventsWithIrregularEventModified() throws EventsLoopException {
        SimpleIntervalRecurrenceStrategy recurrenceStrategy = new SimpleIntervalRecurrenceStrategy(Duration.ofHours(4));

        LocalDateTime time = LocalDateTime.now();
        OneTimeEvent firstEvent = new OneTimeEvent(1, "sum", "det", EventCategory.BASIC,
                time, time.plusMinutes(15));

        RecurringEvent recurringEvent = new RecurringEvent(firstEvent, recurrenceStrategy, 0,
                time, time, time, time.plusMonths(2));

        IrregularEvent irregularEvent = new IrregularEvent(1, "sum", "det", EventCategory.BASIC,
                time, time.plusMinutes(15), 3, IrregularEventType.MODIFIED);

        recurringEvent.getIrregularEvents().add(irregularEvent);

        List<OneTimeEvent> oneTimeEvents = eventService.getOneTimeEvents(recurringEvent);
        oneTimeEvents.forEach(System.out::println);
        assert oneTimeEvents.size() == 360;
    }

    @Test
    void getOneTimeEventsWithIrregularEventAdded() throws EventsLoopException {
        SimpleIntervalRecurrenceStrategy recurrenceStrategy = new SimpleIntervalRecurrenceStrategy(Duration.ofHours(4));

        LocalDateTime time = LocalDateTime.now();
        OneTimeEvent firstEvent = new OneTimeEvent(1, "sum", "det", EventCategory.BASIC,
                time, time.plusMinutes(15));

        RecurringEvent recurringEvent = new RecurringEvent(firstEvent, recurrenceStrategy, 0,
                time, time, time, time.plusMonths(2));

        IrregularEvent irregularEvent = new IrregularEvent(1, "sum", "det", EventCategory.BASIC,
                time, time.plusMinutes(20), -1, IrregularEventType.ADDED);

        recurringEvent.getIrregularEvents().add(irregularEvent);

        List<OneTimeEvent> oneTimeEvents = eventService.getOneTimeEvents(recurringEvent);
        oneTimeEvents.forEach(System.out::println);
        assert oneTimeEvents.size() == 361;
    }
}