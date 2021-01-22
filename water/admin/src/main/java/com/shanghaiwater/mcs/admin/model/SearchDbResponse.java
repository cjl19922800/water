package com.shanghaiwater.mcs.admin.model;

import java.util.List;
import java.util.Map;

import com.shanghaiwater.mcs.admin.common.ResponseModel;

public class SearchDbResponse extends ResponseModel{

	private List<Map<String,String>> data;
	
	private List<String> cols;

	private Boolean success;
	
	private int count;
	
	

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Map<String, String>> getData() {
		return data;
	}

	public void setData(List<Map<String, String>> data) {
		this.data = data;
	}

	public List<String> getCols() {
		return cols;
	}

	public void setCols(List<String> cols) {
		this.cols = cols;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	
	

}
