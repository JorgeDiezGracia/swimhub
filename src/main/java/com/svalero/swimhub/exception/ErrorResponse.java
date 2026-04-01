package com.svalero.swimhub.exception;

import lombok.Data;
import java.util.Map;

@Data
public class ErrorResponse {

    private int code;
    private String message;
    private Map<String, String> errors;

    public static ErrorResponse generalError(int code, String message) {
        ErrorResponse response = new ErrorResponse();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static ErrorResponse notFound(String message) {
        ErrorResponse response = new ErrorResponse();
        response.setCode(404);
        response.setMessage(message);
        return response;
    }

    public static ErrorResponse validationError(Map<String, String> errors) {
        ErrorResponse response = new ErrorResponse();
        response.setCode(400);
        response.setMessage("Validation error");
        response.setErrors(errors);
        return response;
    }

    public static ErrorResponse internalError() {
        ErrorResponse response = new ErrorResponse();
        response.setCode(500);
        response.setMessage("Internal Server Error");
        return response;
    }

    public static ErrorResponse unauthorized() {
        ErrorResponse response = new ErrorResponse();
        response.setCode(401);
        response.setMessage("Unauthorized");
        return response;
    }

    public static ErrorResponse forbidden() {
        ErrorResponse response = new ErrorResponse();
        response.setCode(403);
        response.setMessage("Forbidden");
        return response;
    }
}
