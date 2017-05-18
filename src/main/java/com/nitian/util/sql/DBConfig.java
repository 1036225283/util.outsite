package com.nitian.util.sql;

/**
 * 数据库连接信息
 * 
 * @author 1036225283
 *
 */
public class DBConfig {

	private String url = "jdbc:mysql://rd908bp000online000readonly.mysql.rds.aliyuncs.com:3306/kmrcs?characterEncoding=utf8&useUnicode=true";
	private String username = "kmops_liuyuehan";
	private String password = "Pass4Rds1815lyh";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
