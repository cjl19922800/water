package com.shanghaiwater.mcs.admin.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shanghaiwater.mcs.admin.common.ResponseModel;
import com.shanghaiwater.mcs.db.entity.McsOplog;

public class LogPageResponse extends ResponseModel{
	
	private String count;

	public void setTotal(Long total) {
		this.total = total;
	}

	private IPage<McsOplog> data;

	private Long total;

	private Boolean success;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}


	public long getTotal() {
		return total;
	}

	public void setTotal(long l) {
		this.total = l;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public IPage<McsOplog> getData() {
		return data;
	}

	public void setData(IPage<McsOplog> data) {
		this.data = data;
	}
	
	
	
	


}
