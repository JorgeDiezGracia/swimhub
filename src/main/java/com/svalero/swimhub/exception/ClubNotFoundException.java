package com.svalero.swimhub.exception;

public class ClubNotFoundException extends Exception {
    public ClubNotFoundException() {
        super("Club not found");
    }
    public ClubNotFoundException(String message) {
        super(message);
    }
}
