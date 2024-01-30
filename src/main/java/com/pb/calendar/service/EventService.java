package com.pb.calendar.service;

import com.pb.calendar.event.Event;
import com.pb.calendar.event.OneTimeEvent;
import com.pb.calendar.event.recurrence.IrregularEvent;
import com.pb.calendar.event.recurrence.IrregularEventType;
import com.pb.calendar.event.recurrence.RecurringEvent;
import com.pb.calendar.event.recurrence.strategy.RecurrenceStrategy;
import com.pb.calendar.exception.EventsLoopException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventService {
    List<Event> getCreatedEvents(int userId, LocalDateTime startTime, LocalDateTime endTime) {
        // we take events from repo
        // then we transform all RecurrenceEvents to OneTimeEvents
        // (that's probably no the best way, this could be also done on front-end,
        // or maybe by a dedicated microservice)
        return new ArrayList<>();
    }

    List<OneTimeEvent> getOneTimeEvents(RecurringEvent recurringEvent) throws EventsLoopException {
        List<OneTimeEvent> events = new ArrayList<>();
        OneTimeEvent latestEvent = recurringEvent.getFirstEvent();
        RecurrenceStrategy strategy = recurringEvent.getRecurrenceStrategy();
        LocalDateTime recurrenceEndTime = recurringEvent.getEndTime();
        int limit = recurringEvent.getRecurrenceCount() == 0 ? Integer.MAX_VALUE : recurringEvent.getRecurrenceCount();
        List<IrregularEvent> irregularEvents = recurringEvent.getIrregularEvents();

        for (int i = 0; i < limit; i++) {
            if (latestEvent.getEndTime().isAfter(recurrenceEndTime)) {
                break;
            }
            LocalDateTime maxStartTime = events.stream()
                    .map(Event::getStartTime)
//                    .filter(Objects::nonNull)
                    .max(LocalDateTime::compareTo)
                    .orElse(LocalDateTime.MIN);

            if (latestEvent.getStartTime().isBefore(maxStartTime)) {
                throw new EventsLoopException("events should only move forward in time");
            }

            int finalI = i;
            //there should be one such event at most, this is yet to be enforced
            IrregularEvent irregularEvent = irregularEvents.stream()
                    .filter(event -> event.getRecurringEventCounter() == finalI)
                    .findFirst()
                    .orElse(null);

            if (irregularEvent == null) {
                events.add(latestEvent);
            } else {
                IrregularEventType eventType = irregularEvent.getIrregularEventType();
                switch (eventType) {
                    case MODIFIED -> events.add(new OneTimeEvent(irregularEvent.getId(),
                            irregularEvent.getSummary(),
                            irregularEvent.getDetails(),
                            irregularEvent.getCategory(),
                            irregularEvent.getStartTime(),
                            irregularEvent.getEndTime()));
                    case CANCELLED ->
                        // Handle CANCELLED case
                            System.out.println("Event nr " + i + " is cancelled");
                    default ->
                        // Handle default case (optional)
                            System.out.println("Wrong event type");
                }
            }

            latestEvent = strategy.nextEvent(latestEvent);
        }

        irregularEvents.stream()
                .filter(event -> event.getIrregularEventType().equals(IrregularEventType.ADDED))
                .forEach(event -> events.add(new OneTimeEvent(event.getId(),
                        event.getSummary(),
                        event.getDetails(),
                        event.getCategory(),
                        event.getStartTime(),
                        event.getEndTime())));

        return events;
    }


}
