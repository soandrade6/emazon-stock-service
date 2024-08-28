package com.emazon.stock_service.infraestruture.exeptionhandler;


import com.emazon.stock_service.infraestruture.exeption.CategoryAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class InfraestructureExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<String> categoryAlreadyExistsException(CategoryAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
