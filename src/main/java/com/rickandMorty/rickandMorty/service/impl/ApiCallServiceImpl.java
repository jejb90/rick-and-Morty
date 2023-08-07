package com.rickandMorty.rickandMorty.service.impl;

import com.rickandMorty.rickandMorty.entities.ApiCall;
import com.rickandMorty.rickandMorty.repository.ApiCallRepository;
import com.rickandMorty.rickandMorty.service.ApiCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApiCallServiceImpl implements ApiCallService {

    @Autowired
    private ApiCallRepository apiCallRepository;

    public void logApiCall(String method, String url, String requestBody, String responseBody, boolean error, String errorMessage) {
        ApiCall apiCall = new ApiCall();
        apiCall.setTimestamp(LocalDateTime.now());
        apiCall.setMethod(method);
        apiCall.setUrl(url);
        apiCall.setRequestBody(requestBody);
        apiCall.setResponseBody(responseBody);
        apiCall.setError(error);
        apiCall.setErrorMessage(errorMessage);
        apiCallRepository.save(apiCall);
    }
}
