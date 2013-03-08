package com.example.services.sso;

import java.util.HashSet;
import java.util.Set;

import com.example.service.user.model.User;
import com.example.services.sso.model.SSOCookie;
import com.example.services.sso.model.SSOPrincipal;
import com.example.services.sso.model.Ticket;

/**
 * @author Huan Zhang
 * 
 */
public class LoginServiceImpl implements LoginService {

	private static final TicketManager ticketManager = new TicketManager();

	public SSOPrincipal validateUser(SSOCookie cookie) {
		SSOPrincipal principal = null;

		if (cookie != null) {
			Ticket ticket = ticketManager.findByKey(cookie.getKey());

			// Verify the ticket value matches the cookie value
			if (ticket != null && ticket.getKey().equals(cookie.getKey())) {
				principal = new SSOPrincipal();
				principal.setTicket(ticket);
				Set<String> roles = new HashSet<String>();
				roles.add("Admin");
				principal.setRoles(roles);
			}
		}

		return principal;
	}

	public Ticket login(User user) {
		Ticket ticket = ticketManager.create(user.getLogin());
		return ticket;
	}
}
