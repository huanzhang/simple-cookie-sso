package com.example.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.service.user.model.User;
import com.example.service.user.model.UserService;
import com.example.service.user.model.UserServiceImpl;
import com.example.services.sso.LoginService;
import com.example.services.sso.LoginServiceImpl;
import com.example.services.sso.model.CookieFactory;
import com.example.services.sso.model.SSOCookie;
import com.example.services.sso.model.Ticket;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		UserService userService = new UserServiceImpl();
		String login = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		if (login != null && password != null) {
			User user = userService.verifyPassword(login, password);

			if (user != null && login.equals(user.getLogin())) {

				processLogin(user, request, response);

				out.println("<h2>Logged In!</h2>");

				System.out.println(login + " logged in.");
			} else {
				System.out.println("Failed to login.");
			}
		}
	}

	private void processLogin(User user, HttpServletRequest request,
			HttpServletResponse response) {
		if (user == null || user.getLogin() == null) {
			return;
		}

		LoginService loginService = new LoginServiceImpl();

		Ticket ticket = loginService.login(user);
		ticket.setId(0);

		System.out.println("User name from ticket : " + ticket.getUsername());
		System.out.println("Key from ticket : " + ticket.getKey());

		String cookieValue = CookieFactory.createSSOCookie(ticket).toString();

		Cookie userCookie = new Cookie(SSOCookie.ID, cookieValue);
		response.addCookie(userCookie);

		System.out.println("Cookie added.");
	}

}
