package com.nitian.util.encrypt;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import com.nitian.util.string.UtilStringHex;

public class UtilRSA {

	private String publicKey;

	private String privateKey;

	private KeyFactory keyFactory;

	public UtilRSA() {
		// TODO Auto-generated constructor stub
		init();
	}

	private void init() {
		try {
			keyFactory = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 创建公钥，私钥
	 */
	public void createKey() {
		KeyPairGenerator keyPairGenerator;
		try {
			keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(1024);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
			publicKey = UtilStringHex.bytesHexStr(rsaPublicKey.getEncoded());
			privateKey = UtilStringHex.bytesHexStr(rsaPrivateKey.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 公钥加密
	 * 
	 * @param value
	 * @return
	 */
	public byte[] publicKeyEncrypt(byte[] value) {
		byte[] result = null;
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
				UtilStringHex.initByte(publicKey));
		try {
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			result = cipher.doFinal(value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 公钥解密
	 * 
	 * @param value
	 * @return
	 */
	public byte[] publicKeyUnEncrypt(byte[] value) {
		byte[] result = null;
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
				UtilStringHex.initByte(publicKey));
		try {
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			result = cipher.doFinal(value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 私钥加密
	 * 
	 * @param value
	 * @return
	 */
	public byte[] privateKeyEncrypt(byte[] value) {
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
				UtilStringHex.initByte(privateKey));
		byte[] result = null;
		try {
			PrivateKey privateKey = keyFactory
					.generatePrivate(pkcs8EncodedKeySpec);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			result = cipher.doFinal(value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 私钥解密
	 * 
	 * @param value
	 * @return
	 */
	public byte[] privateKeyUnEncrypt(byte[] value) {
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
				UtilStringHex.initByte(privateKey));
		byte[] result = null;
		try {
			PrivateKey privateKey = keyFactory
					.generatePrivate(pkcs8EncodedKeySpec);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			result = cipher.doFinal(value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		UtilRSA utilRSA = new UtilRSA();
		utilRSA.createKey();
		byte[] result = utilRSA
				.privateKeyEncrypt("private encrypt public unencrypt"
						.getBytes());
		byte[] ss = utilRSA.publicKeyUnEncrypt(result);
		System.out.println(new String(ss));

		byte[] pe = utilRSA.publicKeyEncrypt("public encrypt private unencrypt"
				.getBytes());
		byte[] dd = utilRSA.privateKeyUnEncrypt(pe);
		System.out.println(new String(dd));
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
}
