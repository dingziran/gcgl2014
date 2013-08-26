package com.dingziran.gcgl2014.domain.demo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    
    @ManyToOne
    private SinglePerson person;
    
    @Temporal(TemporalType.DATE)
    private Date buyDate;
    
    @OneToMany//(mappedBy="beUsed")
    private Set<SinglePerson> users=new HashSet<SinglePerson>();
    
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
	public SinglePerson getPerson() {
		return person;
	}
	public void setPerson(SinglePerson person) {
		this.person = person;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public Set<SinglePerson> getUsers() {
		return users;
	}
	public void setUsers(Set<SinglePerson> users) {
		this.users = users;
	}
    
    
}
