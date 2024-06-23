package com.myproject.main.controller;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.main.request.LoginRequest;
import com.myproject.main.request.RegisterRequest;
import com.myproject.main.service.AuthService;
import com.myproject.main.service.EmailService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    
//    private final EmailService emailService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
//        this.emailService = emailService;
    }
    

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        String token = authService.authenticateUser(loginRequest);
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest) {
    	registerRequest.setCreateAt(new Date(System.currentTimeMillis()));
//    	 String emailContent = "<a href='http://localhost:5173/'>click here</a>";
    	String token = authService.registerUser(registerRequest);
//    	emailService.sendSimpleEmail(registerRequest.getEmail(), emailContent);
        if (token != null) {
            return ResponseEntity.ok("Registration successful");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed. Username might already exist.");
        }
    }
}

