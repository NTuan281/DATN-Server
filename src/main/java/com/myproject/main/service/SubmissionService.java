package com.myproject.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.main.model.Submission;
import com.myproject.main.repository.SubmissionRepository;



@Service
public class SubmissionService {
	 private final SubmissionRepository submissionRepository;

	    @Autowired
	    public SubmissionService(SubmissionRepository submissionRepository) {
	        this.submissionRepository = submissionRepository;
	    }

	    public List<Submission> getAllSubmissions() {
	        return submissionRepository.findAll();
	    }

	    public Submission getSubmissionById(Integer id) {
	        return submissionRepository.findById(id).orElse(null);
	    }

	    public Submission createSubmission(Submission submission) {
	        return submissionRepository.save(submission);
	    }

	    public Submission updateSubmission(Integer id, Submission submission) {
	        submission.setId(id);
	        return submissionRepository.save(submission);
	    }

	    public void deleteSubmission(Integer id) {
	    	submissionRepository.deleteById(id);
	    }
}
