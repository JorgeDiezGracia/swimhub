package com.svalero.swimhub.exception;

public class SwimmerNotFoundException extends Exception {
    public SwimmerNotFoundException() {
        super("Swimmer not found");
    }
    public SwimmerNotFoundException(String message) {
        super(message);
    }
}