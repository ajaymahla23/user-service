package com.eccomerce.us.controller;

import com.eccomerce.us.dto.request.RegisterRequest;
import com.eccomerce.us.dto.request.AuthRequest;
import com.eccomerce.us.dto.response.AuthResponse;
import com.eccomerce.us.model.User;
import com.eccomerce.us.repository.UserRepo;
import com.eccomerce.us.service.UserService;
import com.eccomerce.us.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final UserRepo userRepo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request){
        try {
            User user=userService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (RuntimeException e) {
            throw new RuntimeException("User not regiestered");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        User user = userRepo.findByUsername(request.getUsername()).orElseThrow(()->new RuntimeException("User not found"));
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
        String token = jwtUtil.generateToken(user);
        System.out.println("token = "+token);
        return ResponseEntity.ok(new AuthResponse(token));
    }

}
