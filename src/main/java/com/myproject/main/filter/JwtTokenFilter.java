package com.myproject.main.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.myproject.main.jwt.JwtTokenProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            if (auth != null) {
                // Logic tùy chỉnh cho dự án của bạn
                // Ví dụ, bạn có thể muốn ghi lại một số thông tin hoặc thực hiện các kiểm tra bổ sung

                // Đặt authentication vào SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(auth);

                // Tiếp tục chuỗi filter
                chain.doFilter(request, response);
                return;
            }
        }

        // Logic tùy chỉnh nếu token không hợp lệ hoặc xác thực thất bại
        // Ví dụ, bạn có thể muốn ghi lại một lỗi hoặc xử lý tình huống khác
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Tiếp tục chuỗi filter
        chain.doFilter(request, response);
    }

}
