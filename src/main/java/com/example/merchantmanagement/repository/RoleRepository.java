package com.example.merchantmanagement.repository;

import com.example.merchantmanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, String> {


    Set<Role> findByRoleName(String admin);
}
