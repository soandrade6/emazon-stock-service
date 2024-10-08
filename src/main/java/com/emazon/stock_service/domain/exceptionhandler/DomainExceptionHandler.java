package com.emazon.stock_service.domain.exceptionhandler;

import com.emazon.stock_service.domain.exception.NameTooLongException;
import com.emazon.stock_service.domain.exception.DescriptionTooLongException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class DomainExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NameTooLongException.class)
    public ResponseEntity<String> handleNameTooLongException(NameTooLongException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DescriptionTooLongException.class)
    public ResponseEntity<String> handleDescriptionTooLongException(DescriptionTooLongException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
