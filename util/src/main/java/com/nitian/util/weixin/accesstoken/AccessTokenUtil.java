package com.nitian.util.weixin.accesstoken;

import utilsystem.UtilNoSessionHttp;

import com.nitian.entity.weixin.AccessToken;

public class AccessTokenUtil {

	public static String getAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=";

	public static AccessToken getAccessToken(AccessToken accessToken) {
		getAccessTokenUrl = getAccessTokenUrl + accessToken.getAppid()
				+ "&secret=" + accessToken.getSecret();
		UtilNoSessionHttp httpString = new UtilNoSessionHttp();
		try {
			String result = httpString.POST(getAccessTokenUrl, null);
			System.out.println("resule = "+result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		AccessToken accessToken = new AccessToken();
		accessToken.setAppid("wx79f8f2aadd6ffb29");
		accessToken.setSecret("a01328abb661396fe11e00a6f6ca960f");
		AccessTokenUtil.getAccessToken(accessToken);
	}
}
