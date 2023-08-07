package com.rickandMorty.rickandMorty.exceptions;

public class CharacterAlreadyRegisteredException extends RuntimeException {
    public CharacterAlreadyRegisteredException(String message) {
        super(message);
    }
}
