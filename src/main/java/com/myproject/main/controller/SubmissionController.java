package com.myproject.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.myproject.main.model.Submission;
import com.myproject.main.service.SubmissionService;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;

    @Autowired
    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @GetMapping
    public List<Submission> getAllSubmissions() {
        return submissionService.getAllSubmissions();
    }

    @GetMapping("/{id}")
    public Submission getSubmissionById(@PathVariable int id) {
        return submissionService.getSubmissionById(id);
    }

    @PostMapping
    public Submission createSubmission(@RequestBody Submission submission) {
        return submissionService.createSubmission(submission);
    }

    @PutMapping("/{id}")
    public Submission updateSubmission(@PathVariable int id, @RequestBody Submission submission) {
        submission.setId(id);
        return submissionService.updateSubmission(id,submission);
    }

    @DeleteMapping("/{id}")
    public void deleteSubmission(@PathVariable int id) {
        submissionService.deleteSubmission(id);
    }
}
