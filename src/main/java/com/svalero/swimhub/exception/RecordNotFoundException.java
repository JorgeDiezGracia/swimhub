package com.svalero.swimhub.exception;

public class RecordNotFoundException extends Exception {
    public RecordNotFoundException() {
        super("Record not found");
    }
    public RecordNotFoundException(String message) {
        super(message);
    }
}