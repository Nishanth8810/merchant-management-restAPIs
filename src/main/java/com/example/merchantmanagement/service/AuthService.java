package com.example.merchantmanagement.service;


import com.example.merchantmanagement.dto.LoginRequest;
import com.example.merchantmanagement.dto.LoginResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<LoginResponse> authenticate(LoginRequest loginRequest);
}
