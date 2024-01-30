package com.pb.calendar.event.recurrence.strategy;

import com.pb.calendar.event.OneTimeEvent;

import java.time.Duration;

public class SimpleIntervalRecurrenceStrategy implements RecurrenceStrategy {
    private Duration simpleInterval = Duration.ofDays(1);

    public Duration getSimpleInterval() {
        return simpleInterval;
    }

    public boolean setSimpleInterval(Duration simpleInterval) {
        if (simpleInterval.isNegative() || simpleInterval.isZero()) {
            return false;
        } else {
            this.simpleInterval = simpleInterval;
            return true;
        }
    }

    @Override
    public OneTimeEvent nextEvent(OneTimeEvent previousEvent) {
        previousEvent.getStartTime().plus(simpleInterval);
        return new OneTimeEvent();
    }
}
