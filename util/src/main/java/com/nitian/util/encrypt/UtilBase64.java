package com.nitian.util.encrypt;

import java.io.UnsupportedEncodingException;

import com.nitian.util.string.UtilStringHex;
import com.thoughtworks.xstream.core.util.Base64Encoder;

public class UtilBase64 {

	private Base64Encoder base64Encoder = new Base64Encoder();

	/**
	 * 编码
	 * 
	 * @param context
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String encode(String context) throws UnsupportedEncodingException {
		return base64Encoder.encode(context.getBytes("UTF-8"));
	}

	/**
	 * 解码
	 * 
	 * @param encode
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String decode(String encode) throws UnsupportedEncodingException {
		byte[] bs = base64Encoder.decode(encode);
		return new String(bs, "UTF-8");
	}

	public String decodeToHex(String encode)
			throws UnsupportedEncodingException {
		byte[] bs = base64Encoder.decode(encode);
		String result = UtilStringHex.bytesHexStr(bs);
		return result;
	}
}
