package com.myproject.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.main.model.TestCase;

public interface TestCaseRepository extends JpaRepository<TestCase, Integer>{

}
