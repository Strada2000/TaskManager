package com.example.TaskManager.controller;
import com.example.TaskManager.dto.AuthRequest;
import com.example.TaskManager.dto.AuthResponse;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    protected String getJwtToken (String username, String password, int port, TestRestTemplate restTemplate) {
    // Replace with your actual login request/response
    AuthRequest loginRequest = new AuthRequest(username, password);
    ResponseEntity<AuthResponse> response = restTemplate.postForEntity(
        "http://localhost:" + port + "/api/auth/login", loginRequest, AuthResponse.class);
    AuthResponse body = response.getBody();
    return body != null ? body.getToken() : null;
    }

}
