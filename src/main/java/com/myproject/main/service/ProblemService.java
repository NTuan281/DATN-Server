package com.myproject.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.main.model.Problem;
import com.myproject.main.repository.ProblemRepository;



@Service
public class ProblemService {

    private final ProblemRepository problemDao;

    @Autowired
    public ProblemService(ProblemRepository problemDao) {
        this.problemDao = problemDao;
    }

    public List<Problem> getAllProblems() {
        return problemDao.findAll();
    }

    public Problem getProblemById(Integer id) {
        return problemDao.findById(id).orElse(null);
    }

    public Problem createProblem(Problem problem) {
        return problemDao.save(problem);
    }

    public Problem updateProblem(Integer id, Problem problem) {
        problem.setId(id);
        return problemDao.save(problem);
    }

    public void deleteProblem(Integer id) {
        problemDao.deleteById(id);
    }
}
