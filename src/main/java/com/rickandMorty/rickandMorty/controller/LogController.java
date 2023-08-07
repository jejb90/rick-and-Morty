package com.rickandMorty.rickandMorty.controller;

import com.rickandMorty.rickandMorty.entities.ApiCall;
import com.rickandMorty.rickandMorty.repository.ApiCallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {
    private final ApiCallRepository apiCallRepository;

    @Autowired
    public LogController(ApiCallRepository apiCallRepository) {
        this.apiCallRepository = apiCallRepository;
    }
    @GetMapping
    public List<ApiCall> getAllApiCalls() {
        return apiCallRepository.findAll();
    }

}

