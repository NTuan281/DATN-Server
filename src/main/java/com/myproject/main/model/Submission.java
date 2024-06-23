package com.myproject.main.model;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "source", nullable = false)
    private String source;
    
    @Column(name = "submission_time")
    private Date submissionTime; 
    
    @Column(name = "completed_time")
    private int completedTime; 
    
    @Column(name = "result", nullable = false)
    private String result;
    
    @Column(name = "is_test")
    private boolean isTest;
    
    @ManyToOne
    @JoinColumn(name="problem_id")
    private Problem problem;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    
    
    public Submission(int id, String source, Date submissionTime, int completedTime, String result, boolean isTest,
			Problem problem, User user) {
		super();
		this.id = id;
		this.source = source;
		this.submissionTime = submissionTime;
		this.completedTime = completedTime;
		this.result = result;
		this.isTest = isTest;
		this.problem = problem;
		this.user = user;
	}

	public Submission() {
        super();
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getSource() {
        return source;
    }
    
    public void setSource(String source) {
        this.source = source;
    }
    
    public Date getSubmissionTime() {
        return submissionTime;
    }
    
    public void setSubmissionTime(Date submissionTime) {
        this.submissionTime = submissionTime;
    }
    
    public int getCompletedTime() {
        return completedTime;
    }
    
    public void setCompletedTime(int completedTime) {
        this.completedTime = completedTime;
    }
    
    public String getResult() {
        return result;
    }
    
    public void setResult(String result) {
        this.result = result;
    }
    
    public boolean isTest() {
        return isTest;
    }
    
    public void setTest(boolean isTest) {
        this.isTest = isTest;
    }
    
    
    public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(completedTime, id, isTest, problem, result, source, submissionTime, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Submission other = (Submission) obj;
		return completedTime == other.completedTime && id == other.id && isTest == other.isTest
				&& Objects.equals(problem, other.problem) && Objects.equals(result, other.result)
				&& Objects.equals(source, other.source) && Objects.equals(submissionTime, other.submissionTime)
				&& Objects.equals(user, other.user);
	}

	
}
