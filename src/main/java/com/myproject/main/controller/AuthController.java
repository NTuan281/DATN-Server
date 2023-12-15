package com.myproject.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        String token = authService.authenticateUser(loginRequest);
        if (token != null) {
        	Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(3600); // Thời gian sống của cookie (đơn vị là giây), ở đây là 1 giờ
            cookie.setHttpOnly(true);
            cookie.setPath("/"); // Đặt path của cookie, ở đây là toàn bộ ứng dụng
            response.addCookie(cookie);
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest) {
        // Gọi phương thức registerUser từ AuthService
    	String token = authService.registerUser(registerRequest);
        if (token != null) {
            return ResponseEntity.ok("Registration successful");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed. Username might already exist.");
        }
    }
}

