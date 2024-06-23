package com.myproject.main.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.myproject.main.exception.DuplicateTestSubmissionException;
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

	public List<Submission> getSubmissionsByTimeAndProblemId(Date date, int problemId) {
		return submissionRepository.findBySubmissionTimeAndProblemId(date, problemId);
	}
	
	public List<Submission> getSubmissionsByProblemId(int problemId){
		return submissionRepository.findByProblemId(problemId);
	}

	public List<Submission> getLeaderboard(int problemId) {
		List<Submission> submissions = getSubmissionsByProblemId(problemId);
		submissions.sort((a, b) -> {
			if (a.getResult().equals("ACCEPTED") && b.getResult().equals("ACCEPTED")) {
				return Integer.compare(a.getCompletedTime(), b.getCompletedTime());
			}
			return getResultRank(a.getResult()) - getResultRank(b.getResult());
		});
		return submissions;
	}

	private int getResultRank(String result) {
		switch (result) {
		case "COMPILE ERROR":
			return 0;
		case "WRONG ANSWER":
			return 1;
		case "ACCEPTED":
			return 2;
		default:
			return 3;
		}
	}
	
	
}
