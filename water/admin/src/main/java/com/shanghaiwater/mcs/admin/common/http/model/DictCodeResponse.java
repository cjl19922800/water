package com.shanghaiwater.mcs.admin.common.http.model;

import java.util.List;


public class DictCodeResponse {

	private Boolean IsTruncated;
	
	private String RequestId;
	
	private Integer Code;
	
	private List<DictCode> data;

	public Boolean getIsTruncated() {
		return IsTruncated;
	}

	public void setIsTruncated(Boolean isTruncated) {
		IsTruncated = isTruncated;
	}

	public String getRequestId() {
		return RequestId;
	}

	public void setRequestId(String requestId) {
		RequestId = requestId;
	}

	public Integer getCode() {
		return Code;
	}

	public void setCode(Integer code) {
		Code = code;
	}

	public List<DictCode> getData() {
		return data;
	}

	public void setData(List<DictCode> data) {
		this.data = data;
	}
	
	
}
