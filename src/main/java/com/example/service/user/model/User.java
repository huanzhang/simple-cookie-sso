package com.example.service.user.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Huan Zhang
 * 
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1l;

	private long id;
	private String login;
	private String loginUppercase;
	private String password;
	private String firstName;
	private String lastName;
	private boolean active;
	private Date createdDate;
	private Date updatedDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLoginUppercase() {
		return loginUppercase;
	}

	public void setLoginUppercase(String loginUppercase) {
		this.loginUppercase = loginUppercase;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}