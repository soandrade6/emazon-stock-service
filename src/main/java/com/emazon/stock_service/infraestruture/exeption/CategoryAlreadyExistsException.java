package com.emazon.stock_service.infraestruture.exeption;

public class CategoryAlreadyExistsException extends RuntimeException{
    public CategoryAlreadyExistsException(String message){
        super(message);
    }

}
