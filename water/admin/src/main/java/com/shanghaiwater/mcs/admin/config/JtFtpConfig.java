package com.shanghaiwater.mcs.admin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.shanghaiwater.mcs.common.model.ResponseModel;

@Configuration
@ConfigurationProperties(prefix = "jtftp")
public class JtFtpConfig extends ResponseModel{

	private String hostname;
	
	private String port;
	
	private String username;
	
	private String password;

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
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
