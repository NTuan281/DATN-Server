package com.myproject.main.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "problem") // Define the table name
public class Problem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name",nullable = false,unique = true)
	private String name;
	
	@Column(name = "desciption")
	private String description;
	
	@Column(name = "guide")
	private String guide;
	
	@Column(name = "difficulty")
	private String difficulty;

	@Column(name = "create_at")
	private Date createAt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "problem", cascade = CascadeType.ALL)
    private List<TestCase> testcases;

	@OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Submission> submissions;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "problem_tags", joinColumns = @JoinColumn(name = "problem_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private Set<Tag> tags = new HashSet<>();

	
	
	public List<TestCase> getTestcases() {
		return testcases;
	}

	public void setTestcases(List<TestCase> testcases) {
		this.testcases = testcases;
	}
	
	public Problem(int id, String name, String description, String guide, String difficulty, Date createAt, User user,
			List<TestCase> testcases, List<Submission> submissions, Set<Tag> tags) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.guide = guide;
		this.difficulty = difficulty;
		this.createAt = createAt;
		this.user = user;
		this.testcases = testcases;
		this.submissions = submissions;
		this.tags = tags;
	}

	public Problem() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGuide() {
		return guide;
	}

	public void setGuide(String guide) {
		this.guide = guide;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Submission> getSubmissions() {
		return submissions;
	}

	public void setSubmissions(List<Submission> submissions) {
		this.submissions = submissions;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public int hashCode() {
		return Objects.hash(createAt, description, difficulty, guide, id, name, submissions, tags, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Problem other = (Problem) obj;
		return Objects.equals(createAt, other.createAt) && Objects.equals(description, other.description)
				&& Objects.equals(difficulty, other.difficulty) && Objects.equals(guide, other.guide) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(submissions, other.submissions)
				&& Objects.equals(tags, other.tags) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Problem [id=" + id + ", name=" + name + ", description=" + description + ", guide=" + guide
				+ ", difficulty=" + difficulty + ", createAt=" + createAt + ", user=" + user + ", submissions="
				+ submissions + ", tags=" + tags + "]";
	}

}
