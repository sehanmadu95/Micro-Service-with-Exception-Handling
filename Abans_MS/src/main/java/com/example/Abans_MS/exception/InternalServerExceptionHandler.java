package com.example.Abans_MS.exception;

public class InternalServerExceptionHandler extends RuntimeException {
    public InternalServerExceptionHandler(String msg) {
        super(msg);
    }
}
