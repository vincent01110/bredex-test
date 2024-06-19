package com.example.demo.service;

import com.example.demo.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    ApiKeyService apiKeyService;
    public String saveClient(Client client){
        if(!validateEmail(client.getEmail())) throw new IllegalArgumentException("Email is not valid");
        if(!validateName(client.getName())) throw new IllegalArgumentException("Name is not valid");
        return apiKeyService.generateNewKey();
    }


    private boolean validateEmail(String email){
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex) && email.length() > 0;
    }

    private boolean validateName(String name){
        return name.length() <= 100 && name.length() > 0;
    }

}
