package com.example.merchantmanagement.service.serviceImpl;

import com.example.merchantmanagement.entity.Role;
import com.example.merchantmanagement.entity.User;
import com.example.merchantmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService, UserDetails {

    private final UserRepository userRepository;
    private String email;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        this.email = email;
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            return new org.springframework.security.core.userdetails
                    .User(user.getEmail(), user.getPassword(), getAuthorities());
        }
        log.error("user not found");
        throw new UsernameNotFoundException("user not found");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        User user = userRepository.findByEmail(email).orElseThrow();
        for (Role role : user.getRole()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
