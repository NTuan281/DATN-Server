package com.myproject.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.myproject.main.model.Problem;
import com.myproject.main.repository.ProblemRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/problems")
public class ProblemController {

    private final ProblemRepository problemDao;

    @Autowired
    public ProblemController(ProblemRepository problemDao) {
        this.problemDao = problemDao;
    }

    @GetMapping
    public List<Problem> getAllProblems() {
        return problemDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Problem> getProblemById(@PathVariable int id) {
        Optional<Problem> problem = problemDao.findById(id);
        return problem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Problem> createProblem(@RequestBody Problem problem) {
        Problem createdProblem = problemDao.save(problem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProblem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Problem> updateProblem(@PathVariable int id, @RequestBody Problem problem) {
        Optional<Problem> existingProblem = problemDao.findById(id);
        if (existingProblem.isPresent()) {
            problem.setId(id);
            Problem updatedProblem = problemDao.save(problem);
            return ResponseEntity.ok(updatedProblem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProblem(@PathVariable int id) {
        if (problemDao.existsById(id)) {
            problemDao.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
