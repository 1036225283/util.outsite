package com.nitian.util.encrypt;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class UtilRSA {

	public static void main(String[] args) {
		//1.第一步，初始化密钥
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
