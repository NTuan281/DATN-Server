package com.myproject.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.main.model.TestCase;
import com.myproject.main.service.TestCaseService;

@RestController
@RequestMapping("/api/testcases")
public class TestCaseController {
    @Autowired
    private TestCaseService testCaseService;

    @GetMapping("/problem/{problemId}")
    public List<TestCase> getTestCasesByProblemId(@PathVariable int problemId) {
        return testCaseService.getTestCasesByProblemId(problemId);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TestCase> getTestCaseById(@PathVariable int id) {
        Optional<TestCase> testCase = testCaseService.getTestCaseById(id);
        return testCase.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<TestCase> createTestCase(@RequestBody TestCase testCase) {
        TestCase createdTestCase = testCaseService.createTestCase(testCase);
        return ResponseEntity.ok(createdTestCase);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestCase> updateTestCase(@PathVariable int id, @RequestBody TestCase newTestCase) {
        Optional<TestCase> updatedTestCase = testCaseService.updateTestCase(id, newTestCase);
        return updatedTestCase.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestCase(@PathVariable int id) {
        if (testCaseService.existsById(id)) {
            testCaseService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
