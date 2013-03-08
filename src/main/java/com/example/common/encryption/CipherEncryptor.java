package com.example.common.encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

/**
 * @author Huan Zhang
 * 
 */
public class CipherEncryptor implements Encryptor{
	private static CipherEncryptor encryptor;

	public synchronized static CipherEncryptor getInstance() {
		return encryptor;
	}

	public synchronized static void init(String spec, String key) {
		encryptor = new CipherEncryptor(spec, key);
	}

	private Cipher decryptingCipher;

	private Cipher encryptingCipher;

	CipherEncryptor(String spec, String key) {
		try {
			byte[] convertedKey = Hex.decodeHex(key.toCharArray());

			// Initialize the ciphers
			initDecryptingCipher(spec, convertedKey);
			initEncryptingCipher(spec, convertedKey);
		} catch (Exception e) {
			throw new RuntimeException("Ciphers could not be initialized", e);
		}
	}

	private byte[] addPadding(byte[] value, int numChars) {
		byte[] paddedArray = new byte[value.length + numChars];

		// Copy over the original values
		System.arraycopy(value, 0, paddedArray, 0, value.length);

		return paddedArray;
	}

	public String decrypt(String value) {
		try {
			return new String(decryptingCipher.doFinal(Hex.decodeHex(value
					.toCharArray()))).trim();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String encrypt(String value) {
		try {
			byte[] paddedBytes = padValue(value.getBytes("UTF-8"));
			return new String(Hex.encodeHex(encryptingCipher
					.doFinal(paddedBytes)));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private SecretKeySpec getSecretKeySpec(String cipherSpec, byte[] key) {
		int index = cipherSpec.indexOf('/');

		SecretKeySpec skeySpec;
		if (index > -1) {
			skeySpec = new SecretKeySpec(key, cipherSpec.substring(0, index));
		} else {
			skeySpec = new SecretKeySpec(key, cipherSpec);
		}
		return skeySpec;
	}

	private void initDecryptingCipher(String cipherSpec, byte[] cipherKey)
			throws Exception {
		// Convert the blowfish cookie to a byte array
		SecretKeySpec skeySpec = getSecretKeySpec(cipherSpec, cipherKey);
		decryptingCipher = Cipher.getInstance(cipherSpec);
		decryptingCipher.init(Cipher.DECRYPT_MODE, skeySpec);
	}

	private void initEncryptingCipher(String cipherSpec, byte[] cipherKey)
			throws Exception {
		// Convert the blowfish cookie to a byte array
		SecretKeySpec skeySpec = getSecretKeySpec(cipherSpec, cipherKey);
		encryptingCipher = Cipher.getInstance(cipherSpec);
		encryptingCipher.init(Cipher.ENCRYPT_MODE, skeySpec);
	}

	private byte[] padValue(byte[] value) {
		// Pad value if necessary
		int remainder = value.length % 16;

		if (remainder != 0) {
			value = addPadding(value, 16 - remainder);
		}
		return value;
	}
}
