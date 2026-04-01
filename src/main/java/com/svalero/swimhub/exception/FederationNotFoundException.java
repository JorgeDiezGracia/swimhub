package com.svalero.swimhub.exception;

public class FederationNotFoundException extends Exception {
    public FederationNotFoundException() {
        super("Federation not found");
    }
    public FederationNotFoundException(String message) {
        super(message);
    }
}