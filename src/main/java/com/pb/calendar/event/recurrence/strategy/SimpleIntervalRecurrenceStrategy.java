package com.pb.calendar.event.recurrence.strategy;

import com.pb.calendar.event.OneTimeEvent;

import java.time.Duration;

public final class SimpleIntervalRecurrenceStrategy implements RecurrenceStrategy {
    private Duration simpleInterval = Duration.ofDays(1);

    public SimpleIntervalRecurrenceStrategy(Duration simpleInterval) {
        if (!simpleInterval.isNegative() && !simpleInterval.isZero()) {
            this.simpleInterval = simpleInterval.plus(Duration.ZERO);
        }
    }

    public Duration getSimpleInterval() {
        return simpleInterval.plus(Duration.ZERO);
    }


    @Override
    public OneTimeEvent nextEvent(OneTimeEvent event) {
        return new OneTimeEvent(
                event.getCreatedByUserId(),
                event.getSummary(),
                event.getDetails(),
                event.getCategory(),
                event.getPeriod().getStartTime().plus(simpleInterval),
                event.getPeriod().getEndTime().plus(simpleInterval)
        );
    }
}
