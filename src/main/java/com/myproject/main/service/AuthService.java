package com.myproject.main.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myproject.main.exception.UsernameAlreadyExistsException;
import com.myproject.main.jwt.JwtTokenProvider;
import com.myproject.main.model.User;
import com.myproject.main.repository.UserRepository;
import com.myproject.main.request.LoginRequest;
import com.myproject.main.request.RegisterRequest;

@Service
public class AuthService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    public User createUser(User newUser) {
        // Kiểm tra xem tên người dùng đã tồn tại hay chưa
        if (userRepository.existsByUserName(newUser.getUserName())) {
            // Xử lý tên người dùng đã tồn tại
            // Điều này tùy thuộc vào yêu cầu cụ thể của ứng dụng của bạn
            throw new UsernameAlreadyExistsException("Username already exists: " + newUser.getUserName());
        }

        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
        String encodedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);

        // Lưu user vào cơ sở dữ liệu
        return userRepository.save(newUser);
    }

    public String registerUser(RegisterRequest registerRequest) {
        // Kiểm tra xem người dùng có tồn tại không
        if (userRepository.existsByUserName(registerRequest.getUsername())) {
            throw new UsernameAlreadyExistsException("Username is already taken");
        }

        // Tạo một đối tượng User từ thông tin đăng ký
        User newUser = new User();
        newUser.setUserName(registerRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword())); // Đảm bảo mật khẩu được mã hóa trước khi lưu vào cơ sở dữ liệu
        newUser.setEmail(registerRequest.getEmail());
        newUser.setFullName(registerRequest.getFullname());
        newUser.setDescription(registerRequest.getDescription());
        newUser.setRole(registerRequest.getRole());

        // Lưu người dùng mới vào cơ sở dữ liệu
        userRepository.save(newUser);

        // Trả về JWT token cho việc đăng nhập ngay sau khi đăng ký
        return jwtTokenProvider.generateToken(newUser.getUserName(), newUser.getRole());
    }

    public String authenticateUser(LoginRequest loginRequest) {
        User user = userRepository.findByUserName(loginRequest.getUsername());

        // Kiểm tra xem user có tồn tại không
        if (user != null) {
            // Kiểm tra mật khẩu
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                String role = user.getRole(); // Lấy vai trò từ đối tượng User

                // Gửi thông tin username và role vào phương thức generateToken
                return jwtTokenProvider.generateToken(loginRequest.getUsername(), role);
            }
        }
        return null;
    }

}

