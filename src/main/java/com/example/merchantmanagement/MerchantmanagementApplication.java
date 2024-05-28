package com.example.merchantmanagement;

import com.example.merchantmanagement.repository.RoleRepository;
import com.example.merchantmanagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MerchantmanagementApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(MerchantmanagementApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
//		Role role=new Role();
//		role.setRoleName(roles.ROLE_ADMIN.name());
//		roleRepository.save(role);
//
//		User user=new User();
//		user.setEmail("admin@gmail.com");
//		user.setFirstName("admin");
//		user.setLastName("123");
//		Set<Role> nrole=roleRepository.findByRoleName(roles.ROLE_ADMIN.name());
//		user.setRole(nrole);
//		user.setPassword(bCryptPasswordEncoder.encode("aaaabbbb"));
//		userRepository.save(user);
    }
}
