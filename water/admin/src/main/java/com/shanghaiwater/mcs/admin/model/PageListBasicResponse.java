package com.shanghaiwater.mcs.admin.model;

import java.util.List;

import com.shanghaiwater.mcs.admin.common.ResponseModel;

public class PageListBasicResponse extends ResponseModel{

	private String count;

	private List<?> data;

	private Integer total;

	private Boolean success;
	
	
	public PageListBasicResponse reponse(List<?> dataList,int count) {
		this.setData(dataList);
		this.setCode(0);
		this.setCount(String.valueOf(count));
		this.setTotal(count);
		return this;
	}
	

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	
}
