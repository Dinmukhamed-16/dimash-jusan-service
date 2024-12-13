package com.example.cityservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static com.example.cityservice.util.UtilUrl.ErrorMessages.FORBIDDEN_ERROR;
import static com.example.cityservice.util.UtilUrl.ErrorMessages.SERVICE_UNAVAILABLE_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {
/*
    @ExceptionHandler(WebClientResponseException.Forbidden.class)
    public ResponseEntity<Object> handleForbiddenException(WebClientResponseException.Forbidden ex) {
        HttpStatusCode statusCode = ex.getStatusCode();
        HttpStatus status = (HttpStatus) statusCode;
        return new ResponseEntity<>(FORBIDDEN_ERROR, status);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<Object> handleWebClientException() {
        return new ResponseEntity<>(SERVICE_UNAVAILABLE_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
    }*/
}
