package com.myproject.main.repository;
 import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.myproject.main.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserName(String username); 
	boolean existsByUserName(String username);
}
