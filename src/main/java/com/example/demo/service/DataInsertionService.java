package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.model.Position;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;

@Service
public class DataInsertionService {

    @Autowired
    private final ClientRepository clientRepository;

    @Autowired
    private final PositionRepository positionRepository;

    public DataInsertionService(ClientRepository clientRepository, PositionRepository positionRepository) {
        this.clientRepository = clientRepository;
        this.positionRepository = positionRepository;
    }

    @PostConstruct
    @Transactional
    public void insertData() {

        Client client1 = new Client(1L,"idk1@idk.com", "idk", "2e9c6f79-4d72-40a7-a3dc-406bda398627");
        Client client2 = new Client(2L,"idk2@idk.com", "idk2", "9f9dc54f-5d85-40e8-8f9c-0aa86095a1ab");
        clientRepository.save(client1);
        clientRepository.save(client2);


        Position position1 = new Position(1L,"react developer", "seattle");
        Position position2 = new Position(2L,"spring developer", "seattle");
        Position position3 = new Position(3L,"AI engineer", "san francisco");
        Position position4 = new Position(4L,"backend intern", "los angeles");
        positionRepository.save(position1);
        positionRepository.save(position2);
        positionRepository.save(position3);
        positionRepository.save(position4);
    }
}

