package com.example.demo.service;

import com.example.demo.model.Position;
import com.example.demo.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

@Service
public class PositionService {

    @Autowired
    private final PositionRepository positionRepository;
    @Autowired
    private final ApiKeyService apiKeyService;

    public PositionService(PositionRepository positionRepository, ApiKeyService apiKeyService) {
        this.positionRepository = positionRepository;
        this.apiKeyService = apiKeyService;
    }

    public String savePosition(Position position, String apiKey) {
        if (!this.apiKeyService.isKeyValid(apiKey)) throw new IllegalArgumentException("API key not found");
        validatePosition(position);
        try{
            Position pos = this.positionRepository.save(position);
            return "http://localhost:8080/position/" + pos.getId().toString();
        } catch (Error e){
            throw new RuntimeException("An error occurred while saving");
        }
    }

    private void validatePosition(Position position){
        if(position.getTitle() == null || position.getTitle().length() == 0) throw new IllegalArgumentException("Position title not provided");
        if(position.getTitle().length() > 50) throw new IllegalArgumentException("Position title too long");
        if(position.getLocation() == null || position.getLocation().length() == 0) throw new IllegalArgumentException("Position location not provided");
        if(position.getLocation().length() > 50) throw new IllegalArgumentException("Position location too long");
    }

}