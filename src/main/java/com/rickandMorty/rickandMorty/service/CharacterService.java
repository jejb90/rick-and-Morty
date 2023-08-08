package com.rickandMorty.rickandMorty.service;

import com.rickandMorty.rickandMorty.dto.CharacterDto;

import java.util.List;
import java.util.Map;

public interface CharacterService {
    CharacterDto getCharacterByName(String name);
    CharacterDto saveCharacter(CharacterDto character);
    List<CharacterDto> getAllCharacters(Map<String, String> params);
}
