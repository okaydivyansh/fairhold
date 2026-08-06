package com.fairhold.exception;

public class HoldNotFoundException extends RuntimeException {

    public HoldNotFoundException(String message) {
        super(message);
    }
}