package com.rickandMorty.rickandMorty.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/healthcheck")
public class IndexController {

    @GetMapping
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK");
    }
}

