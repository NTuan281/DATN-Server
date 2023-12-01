package com.myproject.main.service;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;

import com.myproject.main.model.User;
import com.myproject.main.model.User;
import com.myproject.main.repository.UserRepository;
import com.myproject.main.repository.UserRepository;

import java.util.List;
import java.util.Optional; 
  
@Service
public class UserService { 
  
	private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
    	userRepository.deleteById(id);
    }
  
  
}
