package com.learn.rest.webservices.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class User {
	
	private Integer id;
	
	@NotNull(message = "name of user can not be NULL")
	@Size(min = 2,max = 30)
	private String name;
	
	@Past
	private Date birthDate;
	
	//private List<Post> posts;

	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

//	public List<Post> getPosts() {
//		return posts;
//	}
//
//	public void setPosts(List<Post> posts) {
//		this.posts = posts;
//	}
	
	

}