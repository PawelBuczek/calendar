package com.pb.calendar.service;

import com.pb.calendar.event.Event;
import com.pb.calendar.event.OneTimeEvent;
import com.pb.calendar.event.recurrence.strategy.RecurrenceStrategy;
import com.pb.calendar.event.recurrence.RecurringEvent;
import com.pb.calendar.exception.EventsLoopException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class EventService {
    List<Event> getCreatedEvents(int userId, LocalDateTime startTime, LocalDateTime endTime) {
        // we take events from repo
        // then we transform all RecurrenceEvents to OneTimeEvents
        // (that's probably no the best way, this could be also done on front-end,
        // or maybe by a dedicated microservice)
        return new ArrayList<>();
    }

    List<OneTimeEvent> getOneTimeEvents(RecurringEvent recurringEvent) throws Exception {
        List<OneTimeEvent> events = new ArrayList<>();
        OneTimeEvent latestEvent = recurringEvent.getFirstEvent();
        RecurrenceStrategy strategy = recurringEvent.getRecurrenceStrategy();
        LocalDateTime recurrenceEndTime = recurringEvent.getEndTime();
        int limit = recurringEvent.getRecurrenceCount() == 0 ? Integer.MAX_VALUE : recurringEvent.getRecurrenceCount();

        for (int i = 0; i < limit; i++) {
            if (latestEvent.getEndTime().isAfter(recurrenceEndTime)){
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
            events.add(latestEvent);
            latestEvent = strategy.nextEvent(latestEvent);
        }

        return events;
    }


}
