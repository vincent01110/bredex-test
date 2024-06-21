package com.example.demo.exception;

public class ApiKeyValidationException extends RuntimeException{
    public ApiKeyValidationException(String message){
        super("Api Key Validation Exception: " + message);
    }
}
