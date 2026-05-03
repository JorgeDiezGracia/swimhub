package com.svalero.swimhub.exception;

public class CommunityNotFoundException extends Exception {
    public CommunityNotFoundException() {
        super("Community not found");
    }
    public CommunityNotFoundException(String message) {
        super(message);
    }
}