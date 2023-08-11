package com.br.alura.challange7.infra.exceptions;

import java.time.OffsetDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@RestControllerAdvice
public class ExceptionsInternalHandler {

    @ExceptionHandler(InternalEntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleTestimonyNotFoundException(InternalEntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }
    
    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<ExceptionResponse> handleTestimonyBadRequestException(MissingServletRequestPartException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }
    
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ExceptionResponse> handleTestimonyBadRequestParameterException(MissingServletRequestParameterException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }
    
    @ExceptionHandler(UniqueException.class)
    public ResponseEntity<ExceptionResponse> UniqueBadRequest(UniqueException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(HttpStatus.CONFLICT.value(), ex.getMessage()));
    }

    private record ExceptionResponse(Integer status, String message, OffsetDateTime timestamp) {
        public ExceptionResponse(Integer status, String message) {
            this(status, message, OffsetDateTime.now());
        }
    }
}
