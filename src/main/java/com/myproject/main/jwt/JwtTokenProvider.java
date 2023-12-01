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

import java.util.Date;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {

    private static final String SECRET_KEY = "yourSecretKey"; // Replace with a strong secret key

    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000); // Token valid for 1 hour

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
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
}

