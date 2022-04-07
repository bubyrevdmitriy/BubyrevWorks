package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GreetingController {
    Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @GetMapping("/")
    public ResponseEntity<Map<Object, Object>> serviceInfo() {
        Map<Object, Object> response = new HashMap<>();
        response.put("projectName", "instazoo");
        response.put("description","full stack Web service");
        response.put("technologies", "Java, Spring, Spring Security, Hibernate, Rest API, PostgreSQL, Angular");
        response.put("author", "Bubyrev Dmitriy");

        logger.info("In GreetingController address:/ ");

        return ResponseEntity.ok(response);
    }
}
