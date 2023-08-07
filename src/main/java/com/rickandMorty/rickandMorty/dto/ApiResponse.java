package com.rickandMorty.rickandMorty.dto;

import java.util.List;

public class ApiResponse {
    private Info info;
    private List<CharacterResult> results;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<CharacterResult> getResults() {
        return results;
    }

    public void setResults(List<CharacterResult> results) {
        this.results = results;
    }
}
