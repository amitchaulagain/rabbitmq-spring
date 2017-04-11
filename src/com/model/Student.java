package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student implements Serializable {

	@Id
	@GeneratedValue
	private int id;

	private String usersssname;

	private String password;

	public String getUsername() {
		return this.usersssname;
	}

	public void setUsername(String username) {
		this.usersssname = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
