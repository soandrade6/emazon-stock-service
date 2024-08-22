package com.emazon.stock_service.domain.exception;

public class NameTooLongException extends RuntimeException{
    public NameTooLongException(String message){
        super(message);
    }
}
