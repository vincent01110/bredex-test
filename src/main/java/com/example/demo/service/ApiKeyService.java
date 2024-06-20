package com.example.demo.service;

import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApiKeyService {

    @Autowired
    private final ClientRepository clientRepository;

    public ApiKeyService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public String generateNewKey(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public boolean isKeyValid(String key){
        return this.clientRepository.findByApiKey(key).isPresent();
    }
}
