package com.myproject.main.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="testcase")
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    @Column(nullable = false)
    private String input;

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

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public TestCase(int id, Problem problem, String input, String output) {
		super();
		this.id = id;
		this.problem = problem;
		this.input = input;
		this.output = output;
	}

	public TestCase() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, input, output, problem);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestCase other = (TestCase) obj;
		return Objects.equals(id, other.id) && Objects.equals(input, other.input)
				&& Objects.equals(output, other.output) && Objects.equals(problem, other.problem);
	}

    
}