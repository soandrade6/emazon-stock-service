package com.emazon.stock_service.domain.exceptionhandler;

import com.emazon.stock_service.domain.exception.NameTooLongException;
import com.emazon.stock_service.domain.exception.DescriptionTooLongException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NameTooLongException.class)
    public ResponseEntity<String> handleNameTooLongException(NameTooLongException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DescriptionTooLongException.class)
    public ResponseEntity<String> handleDescriptionTooLongException(DescriptionTooLongException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
