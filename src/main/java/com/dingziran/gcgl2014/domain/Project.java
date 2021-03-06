package com.dingziran.gcgl2014.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    
    private String description;
       
	@ManyToMany
	private Set<UserInfo> members;

	@OneToMany(mappedBy="project")
	private Set<TodoList> todos;
	
	public Set<TodoList> getTodos() {
		return todos;
	}

	public void setTodos(Set<TodoList> todos) {
		this.todos = todos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Set<UserInfo> getMembers() {
		return members;
	}

	public void setMembers(Set<UserInfo> members) {
		this.members = members;
	}
	
}
