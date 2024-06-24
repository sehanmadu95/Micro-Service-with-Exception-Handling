package com.example.Day5_Singer_MS.exception;

public class InternalServerExceptionHandler extends RuntimeException {
    public InternalServerExceptionHandler(String message) {
        super(message);
    }
}
