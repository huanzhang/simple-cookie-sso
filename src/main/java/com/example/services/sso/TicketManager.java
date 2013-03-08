package com.example.services.sso;

import com.example.common.encryption.KeyGenerator;
import com.example.common.encryption.KeyGeneratorImpl;
import com.example.services.sso.model.Ticket;

/**
 * @author Huan Zhang
 * 
 */
public class TicketManager {
	private final KeyGenerator ticketGenerator = new KeyGeneratorImpl();
	private TicketCache ticketCache = new TicketCache();

	public TicketManager() {
		ticketCache.init();
	}

	public void deleteByKey(String ticketKey) {
		ticketCache.remove(ticketKey);
	}

	public void delete(Ticket ticket) {
		if (ticket != null) {
			deleteByKey(ticket.getKey());
		}
	}

	public Ticket create(String username) {
		Ticket ticket = new Ticket();
		// Generate a security key
		ticket.setKey(ticketGenerator.getNewValue());
		// Set the user name
		ticket.setUsername(username);
		// Add the ticket to the ticket cache
		ticketCache.put(ticket);
		return ticket;
	}

	public Ticket findByKey(String ticketKey) {
		return ticketCache.get(ticketKey);
	}

	public boolean isActiveKey(String username, String ticketKey) {
		Ticket ticket = findByKey(ticketKey);
		return ticket != null && ticket.getUsername().equals(username);
	}
}
