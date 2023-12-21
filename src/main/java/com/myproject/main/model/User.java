package com.myproject.main.model;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;





@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "user_name",nullable = false,unique = true)
	private String userName;
	
	@Column(name = "password",nullable = false)
	private String password;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "full_name",nullable = false)
	private String fullName;
	
	@Column(name = "description")
	private String description;
	
	@Value("${some.key:USER}")
	@Column(name = "role",nullable = false)
	private String role;
	
	@Value("${some.key:true}")
	private boolean isActive;
	
	@Column(name = "create_at")
	private Date createAt;
	
	@OneToMany(mappedBy = "user")
	private List<Problem> problems;

	@OneToMany(mappedBy = "user")
	private List<Submission> submissions;
	
	
	public User(String userName, String password, String email, String fullName, String description, String role,
			Date createAt) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
		this.description = description;
		this.role = role;
		this.createAt = createAt;
	}


	public List<Problem> getProblems() {
		return problems;
	}


	public void setProblems(List<Problem> problems) {
		this.problems = problems;
	}


	@Override
	public String toString() {
		return "user [id=" + id + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", fullName=" + fullName + ", description=" + description + ", role=" + role + ", createAt="
				+ createAt + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public User() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(createAt, description, email, fullName, id, password, role, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(createAt, other.createAt) && Objects.equals(description, other.description)
				&& Objects.equals(email, other.email) && Objects.equals(fullName, other.fullName) && id == other.id
				&& Objects.equals(password, other.password) && Objects.equals(role, other.role)
				&& Objects.equals(userName, other.userName);
	}
	
}

