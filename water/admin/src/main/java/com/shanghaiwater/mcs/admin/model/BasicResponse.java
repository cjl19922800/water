package com.shanghaiwater.mcs.admin.model;

import com.shanghaiwater.mcs.admin.common.ResponseModel;

public class BasicResponse extends ResponseModel  {

	private String dataString;

	private Object data;

	public String getDataString() {
		return dataString;
	}

	public void setDataString(String dataString) {
		this.dataString = dataString;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	/**
	 * 成功通用
	 * @return
	 */
	public BasicResponse success() {
		BasicResponse response = new BasicResponse();
		response.setCode(200);
		response.setSuccess(true);
		return response;
	}
	
	/**
	 * 失败通用
	 * @param errorCode
	 * @param errorMsg
	 * @return
	 */
	public BasicResponse error(String errorCode,String errorMsg) {
		BasicResponse response = new BasicResponse();
		response.setCode(500);
		response.setSuccess(false);
		response.setErrCode(errorCode);
		response.setErrMessage(errorMsg);
		return response;
	}
	
	
	
	
}
