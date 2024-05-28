package com.example.merchantmanagement.dto;

import com.example.merchantmanagement.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String firstname;
    private String lastname;
    private String email;
    private Set<Role> role;
    private String jwtToken;

}
