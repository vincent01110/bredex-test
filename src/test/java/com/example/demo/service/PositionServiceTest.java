package com.example.demo.service;

import com.example.demo.exception.ApiKeyValidationException;
import com.example.demo.exception.ResourceNotFoundExcepion;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.Position;
import com.example.demo.repository.PositionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PositionServiceTest {

    @Mock
    private PositionRepository positionRepository;

    @Mock
    private ApiKeyService apiKeyService;

    @InjectMocks
    private PositionService positionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSavePosition() {
        when(apiKeyService.isKeyValid(anyString())).thenReturn(true);

        Position position = new Position();
        position.setTitle("Software Engineer");
        position.setLocation("New York");


        when(positionRepository.save(any(Position.class))).thenReturn(position);


        String apiKey = "validApiKey";
        String result = positionService.savePosition(position, apiKey);


        verify(positionRepository, times(1)).save(position);


        assertNotNull(result);


        assertTrue(result.contains("http://localhost:8080/position/"));


        assertDoesNotThrow(() -> positionService.savePosition(position, apiKey));
    }

    @Test
    public void testSavePosition_InvalidApiKey() {

        when(apiKeyService.isKeyValid(anyString())).thenReturn(false);


        Position position = new Position();
        position.setTitle("Software Engineer");
        position.setLocation("New York");


        String apiKey = "invalidApiKey";
        assertThrows(ApiKeyValidationException.class, () -> positionService.savePosition(position, apiKey));


        verify(positionRepository, never()).save(position);
    }

    @Test
    public void testFindPosition() {

        when(apiKeyService.isKeyValid(anyString())).thenReturn(true);


        List<Position> positions = new ArrayList<>();
        Position position1 = new Position();
        position1.setId(1L);
        position1.setTitle("Software Engineer");
        position1.setLocation("New York");
        positions.add(position1);

        when(positionRepository.findByPropertyLike(anyString(), anyString())).thenReturn(positions);


        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("title", "Software Engineer");
        queryParams.put("location", "New York");
        String apiKey = "validApiKey";

        List<String> result = positionService.findPosition(queryParams, apiKey);


        verify(positionRepository, times(1)).findByPropertyLike("New York", "Software Engineer");


        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0).contains("http://localhost:8080/position/1"));
    }

    @Test
    public void testFindPosition_NoQueryParameters() {

        when(apiKeyService.isKeyValid(anyString())).thenReturn(true);


        Map<String, String> queryParams = new HashMap<>();
        String apiKey = "validApiKey";

        assertThrows(ValidationException.class, () -> positionService.findPosition(queryParams, apiKey));


        verify(positionRepository, never()).findByPropertyLike(anyString(), anyString());
    }

    @Test
    public void testGetPositionById() {

        when(apiKeyService.isKeyValid(anyString())).thenReturn(true);


        Position position = new Position();
        position.setId(1L);
        position.setTitle("Software Engineer");
        position.setLocation("New York");


        when(positionRepository.findById(1L)).thenReturn(Optional.of(position));


        String apiKey = "validApiKey";
        Position result = positionService.getPositionById(1L, apiKey);


        verify(positionRepository, times(1)).findById(1L);


        assertNotNull(result);
        assertEquals(position.getId(), result.getId());
        assertEquals(position.getTitle(), result.getTitle());
        assertEquals(position.getLocation(), result.getLocation());
    }

    @Test
    public void testGetPositionById_PositionNotFound() {

        when(apiKeyService.isKeyValid(anyString())).thenReturn(true);

        when(positionRepository.findById(1L)).thenReturn(Optional.empty());

        String apiKey = "validApiKey";
        assertThrows(ResourceNotFoundExcepion.class, () -> positionService.getPositionById(1L, apiKey));

        verify(positionRepository, times(1)).findById(1L);
    }
}

