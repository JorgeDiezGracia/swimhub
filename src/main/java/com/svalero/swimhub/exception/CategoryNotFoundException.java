package com.svalero.swimhub.exception;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException() {
        super("Category not found");
    }
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
