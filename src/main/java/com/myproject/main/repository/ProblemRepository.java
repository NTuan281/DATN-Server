package com.myproject.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.myproject.main.model.Problem;

public interface ProblemRepository extends JpaRepository<Problem, Integer>{

}
