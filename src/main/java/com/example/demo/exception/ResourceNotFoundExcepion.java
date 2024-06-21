package com.example.demo.exception;

public class ResourceNotFoundExcepion extends RuntimeException{
    public ResourceNotFoundExcepion(String message){
        super("Resource Not Found Exception: " + message);
    }
}
