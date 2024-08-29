package com.emazon.stock_service.infraestruture.exeption;

public class NameAlreadyExistsException extends RuntimeException{
    public NameAlreadyExistsException(String message){
        super(message);
    }

}
