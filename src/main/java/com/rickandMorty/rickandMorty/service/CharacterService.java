package com.rickandMorty.rickandMorty.service;

import com.rickandMorty.rickandMorty.dto.CharacterDto;

import java.util.List;

public interface CharacterService {
    List<CharacterDto> getAllCharacters();
    List<CharacterDto> getCharactersByPage(int page);
    CharacterDto getCharacterByName(String name);
    CharacterDto saveCharacter(CharacterDto character);
}
