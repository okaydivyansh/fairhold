package com.fairhold.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

//If everything throws RuntimeException, your global exception handler can't easily distinguish one
// problem from another.
public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
