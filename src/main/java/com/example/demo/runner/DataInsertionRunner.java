package com.example.demo.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.example.demo.service.DataInsertionService;

@Component
public class DataInsertionRunner implements ApplicationRunner {

    @Autowired
    private DataInsertionService dataInsertionService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        dataInsertionService.insertData();
    }
}
