package com.nitian.util.encrypt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * DH算法
 * 
 * @author 1036225283
 *
 */
public class UtilDH {

	public static void initDH(){
		try {
			//1.初始化发送方密钥
			KeyPairGenerator sendKeyPairGenerator = KeyPairGenerator.getInstance("DH");
			sendKeyPairGenerator.initialize(512);
			KeyPair senderKeyPair = sendKeyPairGenerator.genKeyPair();
			byte[] senderPublicKeyEnc = senderKeyPair.getPublic().getEncoded();//发送方密钥
			
			//2.初始化接收方密钥
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
	}
}
