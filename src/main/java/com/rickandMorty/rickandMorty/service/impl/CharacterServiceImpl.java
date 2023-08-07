package com.rickandMorty.rickandMorty.service.impl;

import com.rickandMorty.rickandMorty.dto.CharacterDto;
import com.rickandMorty.rickandMorty.dto.CharacterResult;
import com.rickandMorty.rickandMorty.entities.Character;
import com.rickandMorty.rickandMorty.exceptions.CharacterAlreadyRegisteredException;
import com.rickandMorty.rickandMorty.mapper.CharacterMapper;
import com.rickandMorty.rickandMorty.repository.CharacterRepository;
import com.rickandMorty.rickandMorty.service.CharacterService;
import com.rickandMorty.rickandMorty.service.RickAndMortyApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private ModelMapper modelMapper;

    private final RickAndMortyApiService rickAndMortyApiService;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Autowired
    public CharacterServiceImpl(
            RickAndMortyApiService rickAndMortyApiService,
            CharacterRepository characterRepository,
            CharacterMapper characterMapper) {
        this.rickAndMortyApiService = rickAndMortyApiService;
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
    }
    public List<CharacterDto> getAllCharacters() {
        List<CharacterResult> apiCharacters = rickAndMortyApiService.getAllCharactersFromApi();
        List<CharacterDto> apiCharacterDtos = apiCharacters.stream()
                .map(this::mapCharacterResultToDto)
                .collect(Collectors.toList());

        List<Character> databaseCharacters = characterRepository.findAll();
        List<CharacterDto> databaseCharacterDtos = databaseCharacters.stream()
                .map(this::mapDTO)
                .collect(Collectors.toList());

        List<CharacterDto> allCharacterDtos = new ArrayList<>();
        allCharacterDtos.addAll(apiCharacterDtos);
        allCharacterDtos.addAll(databaseCharacterDtos);

        return allCharacterDtos;
    }


    @Override
    public List<CharacterDto> getCharactersByPage(int page) {
        List<CharacterResult> apiCharacters = rickAndMortyApiService.getCharactersByPage(page);

        return apiCharacters.stream()
                .map(this::mapCharacterResultToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CharacterDto getCharacterByName(String name) {
        List<CharacterResult> apiCharacters = rickAndMortyApiService.getCharactersByNameFromApi(name);
        List<CharacterDto> characterDtos = apiCharacters.stream()
                .map(this::mapCharacterResultToDto)
                .collect(Collectors.toList());

        if (characterDtos.isEmpty()) {
            List<Character> characters = characterRepository.findAll();
            characterDtos = characters.stream().map(character -> mapDTO(character)).collect(Collectors.toList());
        }

        return (characterDtos.size() > 0) ? characterDtos.get(0) : null;
    }

    @Override
    public CharacterDto saveCharacter(CharacterDto characterDto) {
        List<CharacterResult>  characterResults = rickAndMortyApiService.getCharactersByNameFromApi(characterDto.getName());
        if (characterResults.size() > 0) {
            throw new CharacterAlreadyRegisteredException("The character was  found in the Rick and Morty API.");
        }
        Character character = characterRepository.findByName(characterDto.getName());
        if ( character!= null) {
            throw new CharacterAlreadyRegisteredException("The character was found in the BD.");
        }
        return mapDTO(characterRepository.save(mapEntity(characterDto)));
    }

    private CharacterDto mapDTO(Character character){
        CharacterDto characterDto = modelMapper.map(character, CharacterDto.class);
        return characterDto;
    }
    private Character mapEntity(CharacterDto characterDto){
        Character character = modelMapper.map(characterDto, Character.class);
        return character;
    }

    private CharacterDto mapCharacterResultToDto(CharacterResult characterResult) {
        return modelMapper.map(characterResult, CharacterDto.class);
    }
}
