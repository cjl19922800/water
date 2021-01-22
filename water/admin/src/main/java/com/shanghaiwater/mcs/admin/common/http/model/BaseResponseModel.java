package com.shanghaiwater.mcs.admin.common.http.model;

import lombok.Data;

@Data
public class BaseResponseModel {

	private String isSuccess;
	private String code;
	private String msg;
	private String version;
//	private String data;
	public String getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "BaseResponseModel [isSuccess=" + isSuccess + ", code=" + code + ", msg=" + msg + ", version=" + version
				+ "]";
	}
	
}
