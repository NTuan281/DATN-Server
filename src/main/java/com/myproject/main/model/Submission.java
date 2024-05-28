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
	
	@Column(name = "source",nullable = false)
	private String source;
	@Column(name = "submission_time")
	private Date submissionTime;
	@Column(name = "result",nullable = false)
	private String result;
	
	@ManyToOne
	@JoinColumn(name="problem_id")
	private Problem problem;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public Submission(int id, String source, Date submissionTime, String result) {
		super();
		this.id = id;
		this.source = source;
		this.submissionTime = submissionTime;
		this.result = result;
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
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, result, source, submissionTime);
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
		return id == other.id && Objects.equals(result, other.result) && Objects.equals(source, other.source)
				&& Objects.equals(submissionTime, other.submissionTime);
	}
	
	
}
