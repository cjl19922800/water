package com.shanghaiwater.mcs.admin.model;

import com.shanghaiwater.mcs.admin.common.RequestModel;

public class LoginRequest extends RequestModel {

	private String userId;

	private String userPassword;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}
