package com.example.demo.controller;

import com.example.demo.model.Position;
import com.example.demo.service.PositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "position")
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping
    public ResponseEntity<String> postPosition(@RequestBody Position position, @RequestHeader("api-key") String apiKey){
        try {
            String url = this.positionService.savePosition(position, apiKey);
            return new ResponseEntity<>(url, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            System.out.println(e.getCause());
            return new ResponseEntity<>("An Error Occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
