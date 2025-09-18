package com.eccomerce.us.service;

import com.eccomerce.us.dto.request.RegisterRequest;
import com.eccomerce.us.dto.Role;
import com.eccomerce.us.exception.InvalidRoleException;
import com.eccomerce.us.model.User;
import com.eccomerce.us.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

//    password =123
    public User register(RegisterRequest request) {
        User user=new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setRole(this.mapRole(request.getRole()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setName(request.getName());
        return userRepo.save(user);
    }

    private Role mapRole(String role){
            return switch (role){
              case "U" -> Role.ROLE_USER;
                case "S" -> Role.ROLE_SELLER;
                case "A" -> Role.ROLE_ADMIN;
                default -> throw new InvalidRoleException("Invalid role: " + role);

        };
    }
}
