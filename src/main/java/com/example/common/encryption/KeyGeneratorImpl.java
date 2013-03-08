package com.example.common.encryption;

import java.security.SecureRandom;

/**
 * @author Huan Zhang
 * 
 */
public class KeyGeneratorImpl implements KeyGenerator {
	/** The array of printable characters to be used in our random string. */
	private char[] validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ012345679"
			.toCharArray();

	/** The default maximum length. */
	private int length = 35;

	/** An instance of secure random to ensure randomness is secure. */
	private SecureRandom randomizer = new SecureRandom();

	private String convertBytesToString(final byte[] random) {
		final char[] output = new char[random.length];
		for (int i = 0; i < random.length; i++) {
			final int index = Math.abs(random[i] % validChars.length);
			output[i] = validChars[index];
		}

		return new String(output);
	}

	public synchronized String getNewValue() {
		final byte[] random = new byte[length];

		this.randomizer.nextBytes(random);

		return convertBytesToString(random);
	}

	public synchronized void setLength(int length) {
		this.length = length;
	}

	public synchronized void setValidChars(String validChars) {
		this.validChars = validChars.toCharArray();
	}
}