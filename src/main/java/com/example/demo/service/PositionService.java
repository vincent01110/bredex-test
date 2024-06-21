package com.example.demo.service;

import com.example.demo.exception.ApiKeyValidationException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.Position;
import com.example.demo.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if (!this.apiKeyService.isKeyValid(apiKey)) throw new ApiKeyValidationException("API key not found");
        validatePosition(position);
        try{
            Position pos = this.positionRepository.save(position);
            return linkifyPosition(pos);
        } catch (Error e){
            throw new RuntimeException("An error occurred while saving");
        }
    }

    private void validatePosition(Position position){
        if(position.getTitle() == null || position.getTitle().length() == 0) throw new ValidationException("Position title not provided");
        if(position.getTitle().length() > 50) throw new ValidationException("Position title too long");
        if(position.getLocation() == null || position.getLocation().length() == 0) throw new ValidationException("Position location not provided");
        if(position.getLocation().length() > 50) throw new ValidationException("Position location too long");
    }

    public List<String> findPosition(Map<String, String> queryParams, String apiKey){
        if (!this.apiKeyService.isKeyValid(apiKey)) throw new ApiKeyValidationException("API key not found");
        validateQuery(queryParams);
        String title = queryParams.get("title");
        String location = queryParams.get("location");
        List<Position> positions = this.positionRepository.findByPropertyLike(Objects.toString(location, ""), Objects.toString(title, ""));
        return positions.stream().map(this::linkifyPosition).collect(Collectors.toList());
    }


    private void validateQuery(Map<String, String> queryParams){
        String title = queryParams.get("title");
        String location = queryParams.get("location");
        if(title == null && location == null) throw new ValidationException("No query parameter provided");
    }

    private String linkifyPosition(Position position){
        return "http://localhost:8080/position/" + position.getId().toString();
    }


    public Position getPositionById(Long id, String apiKey){
        if (!this.apiKeyService.isKeyValid(apiKey)) throw new ApiKeyValidationException("API key not found");

        Optional<Position> pos = this.positionRepository.findById(id);
        if (pos.isEmpty()) throw new ResourceNotFoundException("Position not found with id: " + id);

        return pos.get();
    }

}
