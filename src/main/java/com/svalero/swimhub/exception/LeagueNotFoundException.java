package com.svalero.swimhub.exception;

public class LeagueNotFoundException extends Exception {
    public LeagueNotFoundException() {
        super("League not found");
    }
    public LeagueNotFoundException(String message) {
        super(message);
    }
}