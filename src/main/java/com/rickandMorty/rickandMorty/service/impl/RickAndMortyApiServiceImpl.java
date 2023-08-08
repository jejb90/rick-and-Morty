package com.rickandMorty.rickandMorty.service.impl;

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
import java.util.Map;

@Service
public class RickAndMortyApiServiceImpl implements RickAndMortyApiService {

    private final RestTemplate restTemplate;

    @Autowired
    public RickAndMortyApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<CharacterResult> getAllCharactersFromApi() {
        String apiUrl = "https://rickandmortyapi.com/api/character";
        return fetchCharacterResults(apiUrl);
    }

    @Override
    public List<CharacterResult> getCharactersByNameFromApi(String name) {
        String apiUrl = "https://rickandmortyapi.com/api/character/?name=" + name;
        return fetchCharacterResults(apiUrl);
    }

    @Override
    public List<CharacterResult> getCharactersByPage(int page) {
        String apiUrl = "https://rickandmortyapi.com/api/character/?page=" + page;
        return fetchCharacterResults(apiUrl);
    }

    public List<CharacterResult> getCharactersByParamsFromApi(Map<String, String> params) {
        StringBuilder apiUrlBuilder = new StringBuilder("https://rickandmortyapi.com/api/character/");
        boolean firstParam = true;

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (firstParam) {
                apiUrlBuilder.append("?").append(entry.getKey()).append("=").append(entry.getValue());
                firstParam = false;
            } else {
                apiUrlBuilder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }

        String apiUrl = apiUrlBuilder.toString();
        return fetchCharacterResults(apiUrl);
    }

    private List<CharacterResult> fetchCharacterResults(String apiUrl) {
        try {
            ResponseEntity<ApiResponse> responseEntity = restTemplate.getForEntity(apiUrl, ApiResponse.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                ApiResponse apiResponse = responseEntity.getBody();
                if (apiResponse != null) {
                    return apiResponse.getResults();
                }
                return Collections.emptyList();
            }
        } catch (Exception e) {
            return Collections.emptyList();
        }

        return Collections.emptyList();
    }
}
