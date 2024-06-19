package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "client")
public class ClientController {


    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }
    @GetMapping
    public ResponseEntity<List<Client>> getClients(){
        return new ResponseEntity<>(this.clientService.getClients(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<String> postClient(@RequestBody Client client){
        try{
            return new ResponseEntity<>(this.clientService.saveClient(client), HttpStatus.OK);
        } catch(IllegalArgumentException exc){
            return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            System.out.println(e.getCause());
            return new ResponseEntity<>("An Error Occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
