package com.shanghaiwater.mcs.admin.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shanghaiwater.mcs.admin.common.ResponseModel;

public class CommonPageReponse extends ResponseModel{
	
	private String count;

	private IPage<?> data;

	private Long total;

	private Boolean success;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public IPage<?> getData() {
		return data;
	}

	public void setData(IPage<?> data) {
		this.data = data;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
}
