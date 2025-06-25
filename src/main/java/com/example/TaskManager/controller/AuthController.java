package com.example.TaskManager.controller;

import com.example.TaskManager.model.User;
import com.example.TaskManager.dto.AuthRequest;
import com.example.TaskManager.dto.AuthResponse;
import com.example.TaskManager.service.CustomUserDetailsService;
import com.example.TaskManager.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private CustomUserDetailsService service;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        final UserDetails userDetails = service.loadUserByUsername(request.getUsername());
        final String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        service.save(user);
        return ResponseEntity.ok("User created");
    }
}
