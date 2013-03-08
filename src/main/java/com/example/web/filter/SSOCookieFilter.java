package com.example.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SSOCookieFilter
 */
public class SSOCookieFilter implements Filter {

	private static final String SSO_COOKIE_NAME = "r_sso";
	private static final String USER_COOKIE_NAME = "r_user";

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		// we'll only ever get HTTP requests here
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		Cookie cookie = getCookie(request, SSO_COOKIE_NAME);

		if (cookie != null) {
			SSOAuthenticator authenticator = new SSOAuthenticator();
			if (authenticator.authenticate(cookie.getValue())) {
				// all good.
			} else {
				throw new ServletException("Unauthorized access");
			}
		} else {
			throw new ServletException("Unauthorized access");
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	/**
	 * Get a cookie by name from <code>request</code>.
	 * 
	 * @return Cookie or <code>null</code> if none sent.
	 */
	private Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();

		if (cookies == null) {
			return null;
		}

		for (int i = 0; i < cookies.length; ++i) {
			if (cookies[i].getName().equals(name)) {
				return cookies[i];
			}
		}

		return null;
	}

	/**
	 * Get the SSO username from the rh_sso cookie.
	 */
	private String getUsernameFromCookie(HttpServletRequest request) {
		Cookie userCookie = getCookie(request, USER_COOKIE_NAME);
		if (userCookie != null) {
			String value = userCookie.getValue();
			if (value != null && value.contains("|")) {
				return value.substring(0, value.indexOf('|'));
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * Destroy a cookie that may or may not exist.
	 */
	private void destroyCookie(HttpServletRequest request,
			HttpServletResponse response, String name) {
		Cookie cookie = getCookie(request, name);
		if (cookie != null) {
			cookie.setValue("");
			response.addCookie(cookie);
		}
	}

	/**
	 * Forcefully unauthenticate a user (usually in exceptional cases).
	 */
	private void unAuthenticate(HttpServletRequest request,
			HttpServletResponse response) {
		destroyCookie(request, response, SSO_COOKIE_NAME);
		destroyCookie(request, response, USER_COOKIE_NAME);
	}
}
