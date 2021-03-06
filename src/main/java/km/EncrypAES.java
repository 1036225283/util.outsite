package km;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class EncrypAES {

	private static final int CACHE_SIZE = 1024;
	/**
	 * 默认密钥，实际项目中可配置注入或数据库读取
	 */
	private static String defaultKey = "hsylgwk-20120101";

	/**
	 * 加密工具
	 */
	private Cipher encryptCipher = null;

	/**
	 * 解密工具
	 */
	private Cipher decryptCipher = null;

	private static EncrypAES instance = new EncrypAES(defaultKey);

	public static EncrypAES getInstance() {
		return instance;
	}

	private EncrypAES(String keyvalue) {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		if (keyvalue == null)
			keyvalue = defaultKey;
		byte[] arrBTmp = null;
		try {
			arrBTmp = keyvalue.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 创建一个空的length位字节数组（默认值为0）
		byte[] arrB = new byte[16];

		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "AES");

		// 生成Cipher对象,指定其支持的DES算法
		try {
			encryptCipher = Cipher.getInstance("AES");
			encryptCipher.init(Cipher.ENCRYPT_MODE, key);

			decryptCipher = Cipher.getInstance("AES");
			decryptCipher.init(Cipher.DECRYPT_MODE, key);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 对字符串加密
	 *
	 * @param str
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public byte[] Encrytor(String str) throws InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		byte[] src = null;
		try {
			src = str.getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encryptCipher.doFinal(src);
	}

	/**
	 * 对字符串解密
	 *
	 * @param buff
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public byte[] Decryptor(byte[] buff) throws InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		return decryptCipher.doFinal(buff);
	}

	/**
	 * @param args
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeyException
	 */
	public static void main(String[] args) throws Exception {
		EncrypAES de1 = EncrypAES.getInstance();
		String msg = "郭德纲-搞笑相声全集";
		// String msg = "6bc1bee22e409f96e93d7e117393172a";
		byte[] encontent = de1.Encrytor(msg);
		byte[] decontent = de1.Decryptor(encontent);
		// OutputStream out = new FileOutputStream("D:\\AES\\2.txt");
		// out.write(encontent);
		// out.close();
		// InputStream in = new FileInputStream("D:\\AES\\2.txt");
		// byte[] ib = new byte[in.available()];
		// in.read(ib);
		// byte[] deib = de1.Decryptor(ib);

		System.out.println("明文是:" + msg);
		System.out.println("加密后:" + new String(encontent));
		System.out.println("解密后:" + new String(decontent));
	}

	/* 文件加密 */
	public void encryptFile(String sourceFilePath, String destFilePath)
			throws Exception {
		File sourceFile = new File(sourceFilePath);
		File destFile = new File(destFilePath);
		if (sourceFile.exists() && sourceFile.isFile()) {
			if (!destFile.getParentFile().exists()) {
				destFile.getParentFile().mkdirs();
			}
			destFile.createNewFile();
			InputStream in = new FileInputStream(sourceFile);
			OutputStream out = new FileOutputStream(destFile);

			/*
			 * KeyGenerator kgen = KeyGenerator.getInstance("AES");
			 * kgen.init(128, new SecureRandom(defaultKey.getBytes())); // Key k
			 * = toKey(Base64Utils.decode(key)); // byte[] raw = k.getEncoded();
			 * SecretKeySpec secretKeySpec = new
			 * SecretKeySpec(kgen.generateKey().getEncoded(), "AES"); Cipher
			 * cipher = Cipher.getInstance("AES");
			 * cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			 */

			CipherInputStream cin = new CipherInputStream(in, encryptCipher);
			byte[] cache = new byte[CACHE_SIZE];
			int nRead = 0;
			while ((nRead = cin.read(cache)) != -1) {
				out.write(cache, 0, nRead);
				out.flush();
			}
			out.close();
			cin.close();
			in.close();
		}
	}

	/* 文件解密 */
	public void decryptFile(String sourceFilePath, String destFilePath)
			throws Exception {
		File sourceFile = new File(sourceFilePath);
		File destFile = new File(destFilePath);
		if (sourceFile.exists() && sourceFile.isFile()) {
			if (!destFile.getParentFile().exists()) {
				destFile.getParentFile().mkdirs();
			}
			destFile.createNewFile();
			FileInputStream in = new FileInputStream(sourceFile);
			FileOutputStream out = new FileOutputStream(destFile);
			/*
			 * Key k = toKey(Base64Utils.decode(key)); byte[] raw =
			 * k.getEncoded(); SecretKeySpec secretKeySpec = new
			 * SecretKeySpec(raw, ALGORITHM); Cipher cipher =
			 * Cipher.getInstance(ALGORITHM); cipher.init(Cipher.DECRYPT_MODE,
			 * secretKeySpec);
			 */
			CipherOutputStream cout = new CipherOutputStream(out, decryptCipher);
			byte[] cache = new byte[CACHE_SIZE];
			int nRead = 0;
			while ((nRead = in.read(cache)) != -1) {
				cout.write(cache, 0, nRead);
				cout.flush();
			}
			cout.close();
			out.close();
			in.close();
		}
	}
}