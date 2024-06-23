package com.myproject.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.myproject.main.exception.DuplicateTestSubmissionException;
import com.myproject.main.model.Submission;
import com.myproject.main.request.SubmissionRequest;
import com.myproject.main.request.TestSubmissionRequest;
import com.myproject.main.service.SubmissionService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;
    @Autowired
    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping("/leaderboard")
    public List<Submission> getLeaderboard(@RequestBody TestSubmissionRequest request) {
        return submissionService.getLeaderboard(request.getProblemId());
    }
    
    @PostMapping("/getSubmissions")
    public List<Submission> getSubmissions(
    		@RequestBody SubmissionRequest request) {
        return submissionService.getSubmissionsByTimeAndProblemId(request.getSubmissionTime(), request.getProblemId());
    }
    
    @GetMapping("/all")
    public List<Submission> getAllSubmissions(){   
        return submissionService.getAllSubmissions();
    }

    @GetMapping("/{id}")
    public Submission getSubmissionById(@PathVariable int id) {
        return submissionService.getSubmissionById(id);
    }

    @PostMapping
    public ResponseEntity<?> createSubmission(@RequestBody Submission submission) throws SQLException {
            Submission savedSubmission = submissionService.createSubmission(submission);
            return ResponseEntity.ok(savedSubmission);
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
