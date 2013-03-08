package com.example.services.sso;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.example.services.sso.model.Ticket;

/**
 * should implement this with a cache framework
 * 
 * @author Huan Zhang
 * 
 */
public class TicketCache implements Serializable {
	private static final long serialVersionUID = 1L;

	private static Map<String, Ticket> ticketCache = null;

	public static void init() {
		ticketCache = new HashMap<String, Ticket>();
	}

	public Map<String, Ticket> getTicketCache() {
		return ticketCache;
	}

	public void put(Ticket ticket) {
		ticketCache.put(ticket.getKey(), ticket);
	}

	public Ticket get(String ticketKey) {
		return ticketCache.get(ticketKey);
	}

	public void remove(Ticket ticket) {
		ticketCache.remove(ticket.getKey());
	}

	public void remove(String ticketKey) {
		ticketCache.remove(ticketKey);
	}
}
