package com.example.Day5_Singer_MS.exception;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(String s) {
        super(s);
    }
}
