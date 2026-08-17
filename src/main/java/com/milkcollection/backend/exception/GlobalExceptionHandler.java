package com.milkcollection.backend.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> validation(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return build(HttpStatus.BAD_REQUEST, "Validation failed", errors);
    }

    @ExceptionHandler({IllegalArgumentException.class, EntityNotFoundException.class})
    public ResponseEntity<Map<String, Object>> badRequest(Exception ex) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> general(Exception ex) {
        return build(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal server error",
                null
        );
    }

    private ResponseEntity<Map<String, Object>> build(
            HttpStatus status,
            String message,
            Object details
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("message", message);

        if (details != null) {
            body.put("errors", details);
        }

        return ResponseEntity.status(status).body(body);
    }
}
