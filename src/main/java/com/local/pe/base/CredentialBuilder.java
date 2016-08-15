package com.local.pe.base;

import com.amazonaws.auth.BasicAWSCredentials;

public class CredentialBuilder {
	public static final String ACCESS_KEY = "";
	public static final String SECRET_KEY = "";

	public static BasicAWSCredentials getCredential() {
		return new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
	}
}
