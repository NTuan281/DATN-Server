package com.myproject.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.myproject.main.model.Problem;
import com.myproject.main.service.ProblemService;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/problem")
public class ProblemController {

    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @GetMapping
    public List<Problem> getAllProblems() {
        return problemService.getAllProblems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Problem> getProblemById(@PathVariable int id) {
        Optional<Problem> problem = problemService.getProblemById(id);
        return problem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Problem> createProblem(@RequestBody Problem problem) {
    	problem.setCreateAt(new Date(System.currentTimeMillis())); // Use current time
    	Problem createdProblem = problemService.createProblem(problem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProblem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Problem> updateProblem(@PathVariable int id, @RequestBody Problem problem) {
        Optional<Problem> existingProblem = problemService.getProblemById(id);
        if (existingProblem.isPresent()) {
            problem.setId(id);
            Problem updatedProblem = problemService.updateProblem(id, problem);
            return ResponseEntity.ok(updatedProblem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProblem(@PathVariable int id) {
        if (problemService.existsById(id)) {
            problemService.deleteProblem(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
