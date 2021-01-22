package com.shanghaiwater.mcs.admin.common;

abstract public class RequestModel extends AbstractModel {
	private String requestId;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

}
