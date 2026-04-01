package com.svalero.swimhub.exception;

public class RaceNotFoundException extends Exception {
    public RaceNotFoundException() {
        super("Race not found");
    }
    public RaceNotFoundException(String message) {
        super(message);
    }
}
