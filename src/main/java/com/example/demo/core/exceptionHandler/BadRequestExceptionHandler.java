package com.example.demo.core.exceptionHandler;

import com.example.demo.core.exceptions.BadRequestExceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class BadRequestExceptionHandler {

    @ExceptionHandler(value = { BadRequestExceptions.class })
    protected ResponseEntity<Object> handleConflict(BadRequestExceptions ex, WebRequest request) {

        return ResponseEntity.status(ex.getCode()).body(ex.getExceptions());
    }
}
