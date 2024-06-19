package com.example.demo.service;

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

    public List<Client> getClients(){
        return this.clientRepository.findAll();
    }

    public String saveClient(Client client){
        if(!validateEmail(client.getEmail())) throw new IllegalArgumentException("Email is not valid");
        if(!validateName(client.getName())) throw new IllegalArgumentException("Name is not valid");
        String token = this.apiKeyService.generateNewKey();
        client.setApi_key(token);
        this.clientRepository.save(client);
        return token;
    }



    private boolean validateEmail(String email){
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex) && email.length() > 0;
    }

    private boolean validateName(String name){
        return name.length() <= 100 && name.length() > 0;
    }

}
