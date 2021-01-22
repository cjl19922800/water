package com.shanghaiwater.mcs.admin.model;

import java.util.List;

import com.shanghaiwater.mcs.admin.common.ResponseModel;
import com.shanghaiwater.mcs.db.entity.McsOplog;

public class LogListResponse extends ResponseModel{
	
	private String count;

	private List<McsOplog> data;

	private Integer total;

	private Boolean success;

	public String getCount() {
		return count;
	}

	public void setCount(String i) {
		this.count = i;
	}

	public List<McsOplog> getData() {
		return data;
	}

	public void setData(List<McsOplog> data) {
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
