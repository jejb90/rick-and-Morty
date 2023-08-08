package com.rickandMorty.rickandMorty.service.impl;

import com.rickandMorty.rickandMorty.dto.CharacterDto;
import com.rickandMorty.rickandMorty.dto.CharacterResult;
import com.rickandMorty.rickandMorty.entities.Character;
import com.rickandMorty.rickandMorty.exceptions.CharacterAlreadyRegisteredException;
import com.rickandMorty.rickandMorty.repository.CharacterRepository;
import com.rickandMorty.rickandMorty.service.CharacterService;
import com.rickandMorty.rickandMorty.service.RickAndMortyApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final ModelMapper modelMapper;
    private final RickAndMortyApiService rickAndMortyApiService;
    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterServiceImpl(
            RickAndMortyApiService rickAndMortyApiService,
            CharacterRepository characterRepository,
            ModelMapper modelMapper) {
        this.rickAndMortyApiService = rickAndMortyApiService;
        this.characterRepository = characterRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CharacterDto getCharacterByName(String name) {
        List<CharacterResult> apiCharacters = rickAndMortyApiService.getCharactersByNameFromApi(name);
        List<CharacterDto> characterDtos = apiCharacters.stream()
                .map(this::mapCharacterResultToDto)
                .collect(Collectors.toList());

        Character character = characterRepository.findByName(name);
        if (character != null) {
            characterDtos.add(mapDTO(character));
        }
        return (characterDtos.size() > 0) ? characterDtos.get(0) : null;
    }

    @Override
    public CharacterDto saveCharacter(CharacterDto characterDto) {
        List<CharacterResult> characterResults = rickAndMortyApiService.getCharactersByNameFromApi(characterDto.getName());
        if (characterResults.size() > 0) {
            throw new CharacterAlreadyRegisteredException("The character was  found in the Rick and Morty API.");
        }
        Character character = characterRepository.findByName(characterDto.getName());
        if (character != null) {
            throw new CharacterAlreadyRegisteredException("The character was found in the BD.");
        }
        return mapDTO(characterRepository.save(mapEntity(characterDto)));
    }

    public List<CharacterDto> getAllCharacters(Map<String, String> params) {
        List<CharacterResult> apiCharacters = rickAndMortyApiService.getCharactersByParamsFromApi(params);
        List<CharacterDto> apiCharacterDtos = apiCharacters.stream()
                .map(this::mapCharacterResultToDto)
                .collect(Collectors.toList());

        if(params.size() == 0){
            List<Character> character = characterRepository.findAll();
            apiCharacterDtos.addAll(character.stream()
                    .map(this::mapDTO)
                    .collect(Collectors.toList()));
        }
        return apiCharacterDtos;
    }

    private CharacterDto mapDTO(Character character) {
        CharacterDto characterDto = modelMapper.map(character, CharacterDto.class);
        return characterDto;
    }

    private Character mapEntity(CharacterDto characterDto) {
        Character character = modelMapper.map(characterDto, Character.class);
        return character;
    }

    private CharacterDto mapCharacterResultToDto(CharacterResult characterResult) {
        return modelMapper.map(characterResult, CharacterDto.class);
    }
}
