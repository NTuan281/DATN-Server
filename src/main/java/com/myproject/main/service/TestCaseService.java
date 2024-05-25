package com.myproject.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.main.model.TestCase;
import com.myproject.main.repository.TestCaseRepository;

@Service
public class TestCaseService {

    @Autowired
    private TestCaseRepository testCaseRepository;

    public List<TestCase> getTestCasesByProblemId(int problemId) {
        return testCaseRepository.findByProblemId(problemId);
    }
    public Optional<TestCase> getTestCaseById(int id) {
        return testCaseRepository.findById(id);
    }

    public TestCase createTestCase(TestCase testCase) {
        return testCaseRepository.save(testCase);
    }

    public Optional<TestCase> updateTestCase(int id, TestCase newTestCase) {
        return testCaseRepository.findById(id).map(testCase -> {
            testCase.setProblem(newTestCase.getProblem());
            testCase.setInputs(newTestCase.getInputs());
            testCase.setOutput(newTestCase.getOutput());
            return testCaseRepository.save(testCase);
        });
    }

    public boolean existsById(int id) {
        return testCaseRepository.existsById(id);
    }

    public void deleteById(int id) {
        testCaseRepository.deleteById(id);
    }
}
