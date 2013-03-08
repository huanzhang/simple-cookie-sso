package com.example.web.init;

import com.example.common.encryption.CipherEncryptor;

/**
 * @author Huan Zhang
 * 
 */
public class SSOHelper {
	private String sharedAuthSpec = "AES/ECB/NoPadding";
	private String sharedAuthKey = "00000000000000000000000000000000";

	/**
	 * Initialize the CipherEncryptor, making various encryption related
	 * features available.
	 */
	public void init() {
		CipherEncryptor.init(getSharedAuthSpec(), getSharedAuthKey());
	}

	public String getSharedAuthSpec() {
		return sharedAuthSpec;
	}

	public void setSharedAuthSpec(String sharedAuthSpec) {
		this.sharedAuthSpec = sharedAuthSpec;
	}

	public String getSharedAuthKey() {
		return sharedAuthKey;
	}

	public void setSharedAuthKey(String sharedAuthKey) {
		this.sharedAuthKey = sharedAuthKey;
	}

}
