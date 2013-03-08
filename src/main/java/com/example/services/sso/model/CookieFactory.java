package com.example.services.sso.model;

import com.example.service.user.model.User;

/**
 * @author Huan Zhang
 * 
 */
public class CookieFactory {

	/**
	 * Creates a SharedAuthCookie using the given user information
	 */
	public static SharedAuthCookie createSharedAuthCookie(User user) {
		// Get the login
		String login = user.getLogin();

		// Get the email address
		String emailAddress = null;

		// Get the name
		String name = user.getFirstName() + " " + user.getLastName();

		// Get the company
		String company = null;

		// Create the shared authentication cookie
		return new SharedAuthCookie(login, name, emailAddress, company);
	}

	/**
	 * Parses a cookie value to return a SharedAuthCookie
	 */
	public static SharedAuthCookie parseSharedAuthCookie(String value) {
		return SharedAuthCookie.parse(value);
	}

	/**
	 * Creates an SSO cookie using the ticket
	 */
	public static SSOCookie createSSOCookie(Ticket ticket) {
		return new SSOCookie(ticket.getId(), ticket.getKey());
	}

	/**
	 * Creates a user cookie using the user information
	 */
	public static UserCookie createUserCookie(User user, boolean isCustomer) {
		// Get the username
		String username = user.getLogin();

		// Get the first name
		String firstName = user.getFirstName();

		// Create the user cookie
		return new UserCookie(username, firstName);
	}

	/**
	 * Parses a cookie value to return a SSOCookie
	 */
	public static SSOCookie parseSSOCookie(String value) {
		return SSOCookie.parse(value);
	}

	/**
	 * Parses a cookie value to return a UserCookie
	 */
	public static UserCookie parseUserCookie(String value) {
		return UserCookie.parse(value);
	}
}
