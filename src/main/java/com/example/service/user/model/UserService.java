package com.example.service.user.model;

public interface UserService {
	public User verifyPassword(String login, String password);
	public User findByLogin(String login);
}
