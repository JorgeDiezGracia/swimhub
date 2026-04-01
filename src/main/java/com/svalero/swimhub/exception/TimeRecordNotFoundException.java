package com.svalero.swimhub.exception;

public class TimeRecordNotFoundException extends Exception {
    public TimeRecordNotFoundException() {
        super("Time record not found");
    }
    public TimeRecordNotFoundException(String message) {
        super(message);
    }
}
