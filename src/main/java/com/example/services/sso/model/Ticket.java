package com.example.services.sso.model;

/**
 * SSO ticket
 * 
 * @author Huan Zhang
 * 
 */
public class Ticket {
	private long id;
	private String username;
	private String key;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
