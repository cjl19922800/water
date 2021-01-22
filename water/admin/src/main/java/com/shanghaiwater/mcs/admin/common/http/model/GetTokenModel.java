package com.shanghaiwater.mcs.admin.common.http.model;

public class GetTokenModel extends BaseResponseModel{

	private String expires_in;
	private String access_token;
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
	
}
