package com.example.demo.controller;

import com.example.demo.exception.ApiKeyValidationException;
import com.example.demo.exception.ResourceNotFoundExcepion;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.Position;
import com.example.demo.service.PositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "position")
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }


    @GetMapping(path = "/search")
    public ResponseEntity<?> searchPositions(@RequestParam Map<String, String> queryParams, @RequestHeader("api-key") String apiKey){
        try{
            return new ResponseEntity<>(this.positionService.findPosition(queryParams, apiKey), HttpStatus.OK);
        } catch (ValidationException | ApiKeyValidationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            System.out.println(e.getCause());
            return new ResponseEntity<>("An Error Occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getPosition(@PathVariable Long id, @RequestHeader("api-key") String apiKey){
        try{
            return new ResponseEntity<>(this.positionService.getPositionById(id, apiKey), HttpStatus.OK);
        } catch (ValidationException | ApiKeyValidationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundExcepion e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            System.out.println(e.getCause());
            return new ResponseEntity<>("An Error Occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<String> postPosition(@RequestBody Position position, @RequestHeader("api-key") String apiKey){
        try {
            String url = this.positionService.savePosition(position, apiKey);
            return new ResponseEntity<>(url, HttpStatus.CREATED);
        } catch (ValidationException | ApiKeyValidationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            System.out.println(e.getCause());
            return new ResponseEntity<>("An Error Occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
