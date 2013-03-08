package com.example.services.sso.model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.example.common.encryption.CipherEncryptor;

public class SharedAuthCookie implements Serializable {
	private static final String COOKIE_DELIMITER = "|";
	private static final long serialVersionUID = 0;
	public static final String ID = "r_shared_auth";
	private static final String UTF8 = "UTF-8";

	private static Log log = LogFactory.getLog(SharedAuthCookie.class);

	public static SharedAuthCookie parse(String value) {
		// Decrypt the value first
		String decryptedValue = CipherEncryptor.getInstance().decrypt(value);

		// Now parse out the cookie value
		SharedAuthCookie result = new SharedAuthCookie();
		String[] valueArray = decryptedValue.split("\\" + COOKIE_DELIMITER);

		if (valueArray.length < 3) {
			log.warn("invalid shared auth cookie value detected = " + value);
			return null;
		}

		// If the cookie is valid, parse out the values
		result.login = valueArray[0];
		result.email = valueArray[1];
		result.name = valueArray[2];

		if (valueArray.length > 3) {
			result.company = valueArray[3];
		}

		return result;
	}

	private String company;
	private String email;
	private String login;
	private String name;

	private SharedAuthCookie() {
	}

	SharedAuthCookie(String login, String name, String email) {
		this(login, name, email, null);
	}

	SharedAuthCookie(String login, String name, String email, String company) {
		try {
			this.login = URLEncoder.encode(login, UTF8);
			this.name = URLEncoder.encode(name, UTF8);
			this.email = URLEncoder.encode(email, UTF8);
			this.company = (company != null) ? URLEncoder.encode(company, UTF8)
					: null;
		} catch (UnsupportedEncodingException e) {
			log.error("Could not URLEncode cookie argument", e);
		}
	}

	public String getCompany() {
		return company;
	}

	public String getEmail() {
		return email;
	}

	public String getLogin() {
		return login;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		// Build up the cookie value
		StringBuffer sb = new StringBuffer(150);
		sb.append(login);
		sb.append(COOKIE_DELIMITER);
		sb.append(email);
		sb.append(COOKIE_DELIMITER);
		sb.append(name);
		sb.append(COOKIE_DELIMITER);
		if (company != null) {
			sb.append(company);
		}

		return CipherEncryptor.getInstance().encrypt(sb.toString());
	}
}
