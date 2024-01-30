package com.pb.calendar.exception;


public class EventsLoopException extends Exception {
    public EventsLoopException(String errorMessage) {
        super(errorMessage);
    }
}
