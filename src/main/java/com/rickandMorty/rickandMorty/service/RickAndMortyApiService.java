package com.rickandMorty.rickandMorty.service;

import com.rickandMorty.rickandMorty.dto.CharacterDto;
import com.rickandMorty.rickandMorty.dto.CharacterResult;

import java.util.List;

public interface RickAndMortyApiService {
    List<CharacterResult> getAllCharactersFromApi();
    List<CharacterResult> getCharactersByNameFromApi(String name);
    List<CharacterResult> getCharactersByPage(int page);
}
