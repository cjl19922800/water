package com.shanghaiwater.mcs.admin.common.http.model;

public class GetUserModel extends BaseResponseModel{

	private UserEntity data;

	public UserEntity getData() {
		return data;
	}

	public void setData(UserEntity data) {
		this.data = data;
	}
	
	
}
