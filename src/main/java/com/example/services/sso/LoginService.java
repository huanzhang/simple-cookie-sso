package com.example.services.sso;

import com.example.service.user.model.User;
import com.example.services.sso.model.SSOCookie;
import com.example.services.sso.model.SSOPrincipal;
import com.example.services.sso.model.Ticket;

/**
 * @author Huan Zhang
 * 
 */
public interface LoginService {
	/**
	 * Given an SSO cookie, returns a Principal based on the ticket information
	 * from the shared ticket cache
	 * 
	 * @return The principal if the cookie belongs to a valid active session,
	 *         null otherwise
	 */
	SSOPrincipal validateUser(SSOCookie cookie);

	/**
	 * Creates the {@Ticket} in the ticket cache and updates the last
	 * logged in time.
	 * 
	 * @param user
	 *            the {@link User} to log in
	 * 
	 * @return the newly created {@link Ticket} for the given user
	 */
	Ticket login(User user);
}
