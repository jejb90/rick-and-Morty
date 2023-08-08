package com.rickandMorty.rickandMorty.service;

import com.rickandMorty.rickandMorty.dto.CharacterDto;
import com.rickandMorty.rickandMorty.dto.CharacterResult;

import java.util.List;
import java.util.Map;

public interface RickAndMortyApiService {
    List<CharacterResult> getAllCharactersFromApi();
    List<CharacterResult> getCharactersByNameFromApi(String name);
    List<CharacterResult> getCharactersByPage(int page);
    List<CharacterResult> getCharactersByParamsFromApi(Map<String, String> params);
}
