package com.example.merchantmanagement.service.serviceImpl;

import com.example.merchantmanagement.dto.LoginRequest;
import com.example.merchantmanagement.dto.LoginResponse;
import com.example.merchantmanagement.jwt.JwtService;
import com.example.merchantmanagement.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service

public class AuthServiceImplementation implements AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImplementation.class);
    @Autowired
    JwtService jwtService;

    @Override
    public ResponseEntity<LoginResponse> authenticate(LoginRequest loginRequest) {
        try {
            return jwtService.createJwtToken(loginRequest);
        } catch (Exception e) {
            log.error("Error occurred {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
