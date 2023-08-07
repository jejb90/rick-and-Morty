package com.rickandMorty.rickandMorty.handler;

import com.rickandMorty.rickandMorty.service.ApiCallService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Component
public class ApiCallInterceptor implements HandlerInterceptor {

    @Autowired
    private ApiCallService apiCallService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String url = request.getRequestURI();
        String requestBody = "";
        apiCallService.logApiCall(method, url, requestBody, null, false, null);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String method = request.getMethod();
        String url = request.getRequestURI();
        // String responseBody = getResponseBody(response);
        boolean isError = response.getStatus() >= 400 || ex != null;
        String errorMessage = isError ? String.valueOf(response.getStatus()) : null;
        apiCallService.logApiCall(method, url, null, null, isError, errorMessage);
    }

    private String getRequestBody(HttpServletRequest request) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

    private String getResponseBody(HttpServletResponse response) {
        try {
            if (response instanceof ContentCachingResponseWrapper) {
                ContentCachingResponseWrapper responseWrapper = (ContentCachingResponseWrapper) response;
                byte[] responseBodyBytes = responseWrapper.getContentAsByteArray();
                if (responseBodyBytes.length > 0) {
                    return new String(responseBodyBytes, StandardCharsets.UTF_8);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}