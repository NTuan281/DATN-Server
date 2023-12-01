package com.myproject.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.main.model.Submission;


public interface SubmissionRepository extends JpaRepository<Submission, Integer>{

}
