package com.fairhold.exception;

import com.fairhold.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

//Instead of crashing with a 500 error, Spring gives your handler a chance to decide the response.

//It avoids duplicate try-catch blocks in controllers, keeps controllers clean, ensures consistent error responses,
// and centralizes exception-to-response mapping.

//The service layer should remain independent of the web framework. It contains business logic, while the controller
// layer is responsible for HTTP concerns such as status codes and response bodies.
@RestControllerAdvice //It's a specialized Spring component that provides centralized exception handling for
// all REST controllers. It intercepts exceptions thrown during request processing and converts them into
// HTTP responses.
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(
            EmailAlreadyExistsException ex,
            HttpServletRequest request) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }
}