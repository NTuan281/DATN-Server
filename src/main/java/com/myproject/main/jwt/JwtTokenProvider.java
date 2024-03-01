package com.myproject.main.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.myproject.main.service.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    private Key key;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    public JwtTokenProvider() {
        // Sử dụng secretKeyFor để tạo một khóa an toàn với kích thước đủ cho HS512
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String generateToken(String username, String role, int id) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000); // Token valid for 1 hour

        return Jwts.builder()
                .setSubject(username)
                .claim("id", id)
                .claim("role", role) // Thêm trường "role" vào payload
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
    
    public int getIdFromToken(String token) {
    	Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return (int) claims.get("id");
    }
    
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Bỏ đi phần "Bearer " để chỉ lấy token
        }
        return null;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = getUserDetailsFromToken(token);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private UserDetails getUserDetailsFromToken(String token) {
        String username = getUsernameFromToken(token);
        // Đây là nơi bạn có thể lấy thông tin người dùng từ cơ sở dữ liệu hoặc bất kỳ nguồn dữ liệu nào khác

        // Ở đây, chúng ta giả định UserDetails chỉ có thông tin về tên người dùng và quyền hạn
        return new User(username, "", new ArrayList<>());
    }
    public String getRoleFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return (String) claims.get("role");
    }
}
