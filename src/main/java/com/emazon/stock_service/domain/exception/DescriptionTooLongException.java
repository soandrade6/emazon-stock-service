package com.emazon.stock_service.domain.exception;

public class DescriptionTooLongException extends RuntimeException {
    public DescriptionTooLongException(String message){
        super(message);
    }
}
