package com.rickandMorty.rickandMorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rickandMorty.rickandMorty.dto.ApiResponse;
import com.rickandMorty.rickandMorty.dto.CharacterResult;
import com.rickandMorty.rickandMorty.service.RickAndMortyApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;
import java.util.List;

@Service
public class RickAndMortyApiServiceImpl implements RickAndMortyApiService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public RickAndMortyApiServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<CharacterResult> getAllCharactersFromApi() {
        String apiUrl = "https://rickandmortyapi.com/api/character";
        ApiResponse apiResponse = restTemplate.getForObject(apiUrl, ApiResponse.class);
        return apiResponse.getResults();
    }

    @Override
    public List<CharacterResult> getCharactersByNameFromApi(String name) {

        try {
            String apiUrl = "https://rickandmortyapi.com/api/character/?name=" + name;

        ResponseEntity<ApiResponse> responseEntity = restTemplate.getForEntity(apiUrl, ApiResponse.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            ApiResponse apiResponse = responseEntity.getBody();
            if (apiResponse != null) {
                return apiResponse.getResults();
            }
            return Collections.emptyList();
        }}catch (Exception e){
            return Collections.emptyList();
        }

        return Collections.emptyList();
    }
    @Override
    public List<CharacterResult> getCharactersByPage(int page) {
        String apiUrl = "https://rickandmortyapi.com/api/character/?page=" + page;
        ApiResponse apiResponse = restTemplate.getForObject(apiUrl, ApiResponse.class);
        return apiResponse.getResults();
    }
}
