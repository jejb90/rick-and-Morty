package com.rickandMorty.rickandMorty.mapper;

import com.rickandMorty.rickandMorty.dto.CharacterDto;
import com.rickandMorty.rickandMorty.entities.Character;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapper {

    public CharacterDto toDto(Character character) {
        CharacterDto dto = new CharacterDto();
        dto.setId(character.getId());
        dto.setName(character.getName());
        dto.setGender(character.getGender());
        dto.setStatus(character.getStatus());
        dto.setImage(character.getImage());
        return dto;
    }

    public Character toEntity(CharacterDto dto) {
        Character character = new Character();
        character.setId(dto.getId());
        character.setName(dto.getName());
        character.setGender(dto.getGender());
        character.setStatus(dto.getStatus());
        character.setImage(dto.getImage());
        return character;
    }
}
