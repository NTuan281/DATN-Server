package com.myproject.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.myproject.main.model.Submission;
import com.myproject.main.repository.SubmissionRepository;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final SubmissionRepository submissionDAO;

    @Autowired
    public SubmissionController(SubmissionRepository submissionDAO) {
        this.submissionDAO = submissionDAO;
    }

    @GetMapping
    public List<Submission> getAllSubmissions() {
        return submissionDAO.findAll();
    }

    @GetMapping("/{id}")
    public Submission getSubmissionById(@PathVariable int id) {
        return submissionDAO.findById(id).orElse(null);
    }

    @PostMapping
    public Submission createSubmission(@RequestBody Submission submission) {
        return submissionDAO.save(submission);
    }

    @PutMapping("/{id}")
    public Submission updateSubmission(@PathVariable int id, @RequestBody Submission submission) {
        submission.setId(id);
        return submissionDAO.save(submission);
    }

    @DeleteMapping("/{id}")
    public void deleteSubmission(@PathVariable int id) {
        submissionDAO.deleteById(id);
    }
}
