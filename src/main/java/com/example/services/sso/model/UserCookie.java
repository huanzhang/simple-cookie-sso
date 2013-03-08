package com.example.services.sso.model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Huan Zhang
 * 
 */
public class UserCookie implements Serializable {
	private static final String COOKIE_DELIMITER = "|";
	private static final long serialVersionUID = 0;

	public static final String ID = "rh_user";

	private static Log log = LogFactory.getLog(UserCookie.class);

	public static UserCookie parse(String value) {
		UserCookie result = new UserCookie();
		String[] valueArray = value.split("\\" + COOKIE_DELIMITER);

		if (valueArray.length < 3) {
			log.warn("invalid user cookie value detected = " + value);
			return null;
		}

		// If the cookie is valid, parse out the values
		result.username = valueArray[0];
		result.firstName = valueArray[1];

		if (valueArray.length > 3) {
			result.cartId = Long.parseLong(valueArray[3]);
		}

		return result;
	}

	private Long cartId;

	private String firstName;

	private String username;

	private UserCookie() {
	}

	UserCookie(String username, String firstName) {
		this(username, firstName, null);
	}

	UserCookie(String username, String firstName, Long cartId) {
		this.username = username;
		this.firstName = firstName;

		// Encode the first name
		try {
			this.firstName = URLEncoder.encode(firstName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.fatal("Could not encode user cookie", e);
		}

		this.cartId = cartId;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	public Long getCartId() {
		return cartId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(150);
		sb.append(username);
		sb.append(COOKIE_DELIMITER);
		sb.append(firstName);
		sb.append(COOKIE_DELIMITER);
		sb.append(COOKIE_DELIMITER);
		if (cartId != null) {
			sb.append(cartId);
		}
		return sb.toString();
	}
}
