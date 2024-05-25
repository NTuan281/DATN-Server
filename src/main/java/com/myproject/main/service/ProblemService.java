package com.myproject.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.main.model.Problem;
import com.myproject.main.repository.ProblemRepository;



@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    @Autowired
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public List<Problem> getAllProblems() {
        return problemRepository.findAll();
    }

    public Optional<Problem> getProblemById(int id) {
        return problemRepository.findById(id).or(null);
    }

    public Problem createProblem(Problem problem) {
        return problemRepository.save(problem);
    }

    public Problem updateProblem(int id, Problem problem) {
        problem.setId(id);
        return problemRepository.save(problem);
    }

    public boolean existsById(int id) {
        return problemRepository.existsById(id);
    }
    
    public void deleteProblem(int id) {
        problemRepository.deleteById(id);
    }
}
