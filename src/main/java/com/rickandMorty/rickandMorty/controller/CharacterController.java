package com.rickandMorty.rickandMorty.controller;

import com.rickandMorty.rickandMorty.dto.CharacterDto;
import com.rickandMorty.rickandMorty.exceptions.CharacterAlreadyRegisteredException;
import com.rickandMorty.rickandMorty.service.CharacterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public List<CharacterDto> getAllCharacters(@RequestParam Map<String, String> params) {
        return characterService.getAllCharacters(params);
    }
    @GetMapping("/{name}")
    public ResponseEntity<CharacterDto> getCharacterByName(@PathVariable String name) {
        CharacterDto characterDto = characterService.getCharacterByName(name);
        if (characterDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(characterDto);
    }

    @PostMapping
    public ResponseEntity<?> saveCharacter(@Valid @RequestBody CharacterDto characterDto) {
        try{
            CharacterDto savedCharacter = characterService.saveCharacter(characterDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
        }catch (CharacterAlreadyRegisteredException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

