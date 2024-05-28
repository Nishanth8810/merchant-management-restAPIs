package com.example.merchantmanagement.jwt;


import com.example.merchantmanagement.dto.LoginRequest;
import com.example.merchantmanagement.dto.LoginResponse;
import com.example.merchantmanagement.entity.User;
import com.example.merchantmanagement.repository.UserRepository;
import com.example.merchantmanagement.service.serviceImpl.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    CustomUserDetails customUserDetails;


    public ResponseEntity<LoginResponse> createJwtToken(LoginRequest req) {
        try {
            User user = userRepository.findByEmailIgnoreCase(req.getEmail()).orElseThrow();
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
            } catch (AuthenticationException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            String newGeneratedToken = jwtToken(req);
            return ResponseEntity.ok(new LoginResponse(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getRole(),
                    newGeneratedToken));

        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public String jwtToken(LoginRequest req) {
        final UserDetails userDetails = customUserDetails.loadUserByUsername(req.getEmail());
        User user = userRepository.findByEmail(req.getEmail()).orElseThrow();
        return jwtUtil.generateToken(userDetails, user.getId());
    }


}
