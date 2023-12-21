package com.myproject.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.main.model.TestCase;
import com.myproject.main.repository.TestCaseRepository;

@RestController
@RequestMapping("/api/testcases")
public class TestCaseController {
    @Autowired
    private TestCaseRepository testCaseRepository;

    @PostMapping
    public ResponseEntity<String> createTestCase(@RequestBody TestCase testCase) {
        testCaseRepository.save(testCase);
        return ResponseEntity.ok("Test case created successfully");
    }
}
