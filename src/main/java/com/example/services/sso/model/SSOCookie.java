package com.example.services.sso.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author Huan Zhang
 * 
 */
public class SSOCookie implements Serializable {
	private static final String COOKIE_DELIMITER = "|";
	private static final long serialVersionUID = 0;

	public static final String ID = "r_sso";

	public static SSOCookie parse(String value) {
		// Parse the value into the SSOValue
		int index = value.indexOf(COOKIE_DELIMITER);

		SSOCookie result;

		try {
			if (index > 0) {
				result = new SSOCookie();
				result.id = Long.parseLong(value.substring(0, index));
				result.key = value.substring(index + 1);
			} else
				result = null;
		} catch (Exception e) {
			result = null;
		}

		return result;
	}

	private long id;

	private String key;

	public SSOCookie() {
	}

	public SSOCookie(long id, String key) {
		this.id = id;
		this.key = key;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	public long getId() {
		return id;
	}

	public String getKey() {
		return key;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(100);
		sb.append(id);
		sb.append(COOKIE_DELIMITER);
		sb.append(key);
		return sb.toString();
	}
}
