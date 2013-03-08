package com.example.web.filter;

import com.example.service.user.model.User;
import com.example.service.user.model.UserService;
import com.example.service.user.model.UserServiceImpl;
import com.example.services.sso.LoginService;
import com.example.services.sso.LoginServiceImpl;
import com.example.services.sso.model.CookieFactory;
import com.example.services.sso.model.SSOCookie;
import com.example.services.sso.model.SSOPrincipal;

/**
 * @author Huan Zhang
 * 
 */
public class SSOAuthenticator {
	private static final LoginService loginService = new LoginServiceImpl();
	private static final UserService userService = new UserServiceImpl();

	/**
	 * 
	 * @param ssoCookieValue
	 * @return
	 */
	public boolean authenticate(String ssoCookieValue) {
		final SSOCookie ssoCookie = CookieFactory
				.parseSSOCookie(ssoCookieValue);
		SSOPrincipal principal = loginService.validateUser(ssoCookie);
		if (principal == null) {
			return false;
		}
		// verify user
		User user = userService
				.findByLogin(principal.getTicket().getUsername());
		if ((user != null) && (user.getLogin() != null)) {
			return true;
		}
		return false;

	}
}
