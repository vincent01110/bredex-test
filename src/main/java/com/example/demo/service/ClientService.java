package com.example.demo.service;

import com.example.demo.exception.ValidationException;
import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private final ClientRepository clientRepository;
    @Autowired
    private final ApiKeyService apiKeyService;


    public ClientService(ClientRepository clientRepository, ApiKeyService apiKeyService) {
        this.clientRepository = clientRepository;
        this.apiKeyService = apiKeyService;
    }


    public String saveClient(Client client){
        validateEmail(client.getEmail());
        validateName(client.getName());
        String token = this.apiKeyService.generateNewKey();
        client.setApiKey(token);
        try{
            this.clientRepository.save(client);
            return token;
        } catch (Error e){
            throw new RuntimeException("An error occurred while saving");
        }
    }



    private void validateEmail(String email){
        if(email == null || email.length() == 0) throw new ValidationException("Email is not provided");
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if(!email.matches(emailRegex)){
            throw new ValidationException("Email is not in a valid format");
        }
        if(email.length() > 45){
            throw new ValidationException("Email is too long");
        }
        if(this.clientRepository.findByEmail(email).isPresent()) {
            throw new ValidationException("Email is already registered");
        }
    }

    private void validateName(String name){
        if(name.length() > 100) throw new ValidationException("Name is too long");
        if(name == null || name.length() == 0) throw new ValidationException("Name is not provided");
    }

}
