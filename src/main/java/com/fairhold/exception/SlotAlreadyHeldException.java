package com.fairhold.exception;

public class SlotAlreadyHeldException extends RuntimeException {

    public SlotAlreadyHeldException(String message) {
        super(message);
    }
}