package com.myproject.main.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ;
	@Column(name = "name",nullable = false,unique = true)
	private String name;
	
	@ManyToMany(mappedBy = "tags"   )
	private Set<Problem> problems = new HashSet<Problem>();
	
	public Tag(int id, String nameTag) {
		super();
		this.id = id;
		this.name = nameTag;
	}

	public Tag(String name) {
		super();
		this.name = name;
	}

	public Tag() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameTag() {
		return name;
	}
	public void setNameTag(String nameTag) {
		this.name = nameTag;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		return id == other.id && Objects.equals(name, other.name);
	}
	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + "]";
	}
}


