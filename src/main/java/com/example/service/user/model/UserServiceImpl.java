package com.example.service.user.model;

import java.util.Date;

/**
 * @author Huan Zhang
 * 
 */
public class UserServiceImpl implements UserService {
	private static User user = new User();

	public UserServiceImpl() {
		user.setActive(true);
		user.setLogin("admin");
		user.setFirstName("Sa");
		user.setLoginUppercase("ADMIN");
		user.setCreatedDate(new Date());
		user.setPassword("admin");
	}

	/**
	 * Should interactive with DAO
	 * 
	 * @param login
	 * @param password
	 * @return
	 */
	public User verifyPassword(String login, String password) {
		if ("admin".equals(login) && "admin".equals(password)) {
			return user;
		}
		return null;
	}

	public User findByLogin(String login) {
		if ("admin".equals(login)) {
			return user;
		}
		return null;
	}
}
