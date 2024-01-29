package com.pb.calendar.service;

import com.pb.calendar.event.Event;
import com.pb.calendar.event.OneTimeEvent;
import com.pb.calendar.event.recurrence.RecurrenceStrategy;
import com.pb.calendar.event.recurrence.RecurringEvent;

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

    List<OneTimeEvent> getOneTimeEvents(RecurringEvent recurringEvent) {
        List<OneTimeEvent> events = new ArrayList<>();
        OneTimeEvent latestEvent = recurringEvent.getFirstEvent();
        RecurrenceStrategy strategy = recurringEvent.getRecurrenceStrategy();
        LocalDateTime recurrenceEndTime = recurringEvent.getEndTime();
        while (latestEvent.getEndTime().isBefore(recurrenceEndTime)) {
            events.add(latestEvent);
            latestEvent = strategy.nextEvent(latestEvent);
        }

        return events;
    }


}
