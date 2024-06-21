package com.example.demo.exception;

public class ValidationException extends IllegalArgumentException{
    public ValidationException(String message){
        super("Validation Exception: " + message);
    }
}
