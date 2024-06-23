package com.myproject.main.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.main.model.Submission;


public interface SubmissionRepository extends JpaRepository<Submission, Integer>{
    List<Submission> findBySubmissionTimeAndProblemId(Date date, int problemId);
    List<Submission> findByProblemId(int problemId);
}
