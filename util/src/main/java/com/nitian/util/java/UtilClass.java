package com.nitian.util.java;

import java.util.List;

import com.nitian.util.encrypt.UtilAES;
import com.nitian.util.encrypt.UtilRSA;
import com.nitian.util.file.UtilFile;
import com.nitian.util.time.UtilTime;

public class UtilClass {

	public static String publicKey = "30819F300D06092A864886F70D010101050003818D00308189028181009562A53ED6EC570476AFEDF4006E1581851E36713DEA96962268E5395A68E85DAF074F288D424E35E8E9C011B3D62723B68DD6CC74F6FCBD329B60B8FCA1C02BEE84DC0F5FFEA31B1420D37E90DA5CD6753CF2A25D6D38AAD3C955BF0FFA57C8029191874B564099596D87835764AE046F80C6B20E3E3E7A5C014AF969387F330203010001";

	public static String privateKey = "30820276020100300D06092A864886F70D0101010500048202603082025C020100028181009562A53ED6EC570476AFEDF4006E1581851E36713DEA96962268E5395A68E85DAF074F288D424E35E8E9C011B3D62723B68DD6CC74F6FCBD329B60B8FCA1C02BEE84DC0F5FFEA31B1420D37E90DA5CD6753CF2A25D6D38AAD3C955BF0FFA57C8029191874B564099596D87835764AE046F80C6B20E3E3E7A5C014AF969387F33020301000102818100941D9EE2538F3A283ED5E9054977DCF2F2DE25F4DA034313D142582529B362D1A0BD8A8B16A46B8FA0444420494348C36C39EE840264F1EB55C6217CC960477CBA74096B20B4861BA605C23913E56213FE80DE7D147B131147AA370A2FF28D77C109A2A2EC9E7BEFA40E1DD64F54F5DFE06467C32D44C935452EEE8F7CA9D761024100F3863B4BFD5DD919F482DE91F36B8717070476C162EE2C351279D151519DF83DACDDF3EB1F9D775747A2832FDB66DA75A0E9AFF366EA3ABADB33E9966FC6D7550241009D09CD28A46C089CEDE0853F3ABE07C6B572507CC9ED6899843F66B8D26F3B14D805434363A4D55E3EFF704989374720F9C89E292DA7CB3AB1F7F61F97E06C670240646E737DFF5BF8F12F59D998AF9F24F3663D7D0C7C54657EEDC3B783A5F9542B7B637DF8F5ECE70EE3C82DFA4AADBC62CE9E11A67C47153982B5821C3CB4F09102401D2956C9B74EC082B3C1BFBCB021DDBBCEB1FDB44EA3218B707D7D8BD0B80B16A1CEC860D93E20C93195A9BA5E1ABFC0A5E1D54CFC476E7BE5D49005E9E428FD024054FC6C745EA302FFE1BFB8CB0FE419DC1C5DF89CBC7A05B9D45064B5D16697CB1535DDAA441112D9D164F6859628B638719BF04291D1F3345E13816C4C7BFF31";

	public static String aesKey = "DC4233F479CFD8534BB936B9E3DC3933";

	public static void main(String[] args) throws Exception {
		String path = "C:\\Users\\1036225283\\Desktop\\util";
		UtilTime.startTest();
		encryptClassByAES(path, aesKey);
		UtilTime.endTest();
		// decryptClassByAES(path, aesKey);
	}

	public static void test() {
		byte[] a = UtilAES.encrypt(aesKey, "helloworld".getBytes());
		byte[] result = UtilAES.decrypt(aesKey, a);
		System.out.println(new String(result));
	}

	/**
	 * 对指定目录使用RSA加密
	 * 
	 * @param path
	 * @param rsaPrivateKey
	 */
	public static void encryptClassByRSA(String path, String rsaPrivateKey) {
		UtilRSA utilRSA = new UtilRSA();
		utilRSA.setPrivateKey(rsaPrivateKey);
		List<String> list = UtilFile.getAllFileName(path);
		for (String string : list) {
			System.out.println(string);
			byte[] bs = UtilFile.fileToHex(string);
			byte[] encrypt = utilRSA.privateKeyEncrypt(bs);
			UtilFile.hexToFile(encrypt, string);
		}
	}

	/**
	 * 对指定目录使用RSA解密
	 * 
	 * @param path
	 * @param rasPublicKey
	 */
	public static void unEncryptClassByRSA(String path, String rasPublicKey) {
		UtilRSA utilRSA = new UtilRSA();
		utilRSA.setPublicKey(rasPublicKey);
		List<String> list = UtilFile.getAllFileName(path);
		for (String string : list) {
			byte[] bs = UtilFile.fileToHex(string);
			byte[] encrypt = utilRSA.publicKeyUnEncrypt(bs);
			UtilFile.hexToFile(encrypt, string);
		}
	}

	/**
	 * 对指定目录使用AES加密
	 * 
	 * @param path
	 * @param rsaPrivateKey
	 */
	public static void encryptClassByAES(String path, String aesKey) {
		List<String> list = UtilFile.getAllFileName(path);
		for (String string : list) {
			byte[] bs = UtilFile.fileToHex(string);
			byte[] encrypt = UtilAES.encrypt(aesKey, bs);
			UtilFile.hexToFile(encrypt, string);
		}
	}

	/**
	 * 对指定目录使用AES解密
	 * 
	 * @param path
	 * @param rasPublicKey
	 */
	public static void decryptClassByAES(String path, String aesKey) {
		List<String> list = UtilFile.getAllFileName(path);
		for (String string : list) {
			byte[] bs = UtilFile.fileToHex(string);
			byte[] encrypt = UtilAES.decrypt(aesKey, bs);
			UtilFile.hexToFile(encrypt, string);
		}
	}

}
