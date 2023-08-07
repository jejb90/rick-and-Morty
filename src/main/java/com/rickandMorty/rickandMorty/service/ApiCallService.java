package com.rickandMorty.rickandMorty.service;

public interface ApiCallService {
    void logApiCall(String method, String url, String requestBody, String responseBody, boolean error, String errorMessage);
}
