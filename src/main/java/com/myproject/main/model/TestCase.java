package com.myproject.main.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonType;

@TypeDef(name = "json", typeClass = JsonType.class)
@Entity
@Table(name="testcase")
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    @Type(type = "json")
    @Column(columnDefinition = "json", nullable = false)
    private List<Object> inputs;

    @Column(nullable = false)
    private String output;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public List<Object> getInputs() {
		return inputs;
	}

	public void setInputs(List<Object> inputs) {
		this.inputs = inputs;
	}

	public TestCase(int id, Problem problem, List<Object> inputs, String output) {
		super();
		this.id = id;
		this.problem = problem;
		this.inputs = inputs;
		this.output = output;
	}

	

    
}