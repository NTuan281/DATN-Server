package com.myproject.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.main.model.Submission;
import com.myproject.main.repository.SubmissionRepository;



@Service
public class SubmissionService {
	 private final SubmissionRepository submissionDao;

	    @Autowired
	    public SubmissionService(SubmissionRepository submissionDao) {
	        this.submissionDao = submissionDao;
	    }

	    public List<Submission> getAllSubmissions() {
	        return submissionDao.findAll();
	    }

	    public Submission getSubmissionById(Integer id) {
	        return submissionDao.findById(id).orElse(null);
	    }

	    public Submission createSubmission(Submission submission) {
	        return submissionDao.save(submission);
	    }

	    public Submission updateSubmission(Integer id, Submission submission) {
	        submission.setId(id);
	        return submissionDao.save(submission);
	    }

	    public void deleteSubmission(Integer id) {
	    	submissionDao.deleteById(id);
	    }
}
