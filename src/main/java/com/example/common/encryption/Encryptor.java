package com.example.common.encryption;

/**
 * @author Huan Zhang
 * 
 */
public interface Encryptor {
	public String encrypt(String value);
	public String decrypt(String value);
}
