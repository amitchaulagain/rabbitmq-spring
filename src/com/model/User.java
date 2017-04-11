package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hamrouser") 
public class User implements Serializable {

	@Id
	@GeneratedValue
	private int id;

	 
	private String usersssname;
	
	
	
	@Column(name = "password", nullable = false, length = 60)
	private String password;
	@Column(name = "enable", nullable = true)
	private boolean enabled;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
