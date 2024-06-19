package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class ApiKeyService {

    public String generateNewKey(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
