package com.pk.batch.BatchDemo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	private Integer id;
	private String name;
	private String department;
	private Integer salary;
	private Date date;
	
	public User() {
		super();
	}

	public User(Integer id, String name, String department, Integer salary, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.salary = salary;
		this.date = date;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", department=" + department + ", salary=" + salary + ", date="
				+ date + "]";
	}
	
	
	
}
