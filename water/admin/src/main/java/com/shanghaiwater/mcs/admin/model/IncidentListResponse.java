package com.shanghaiwater.mcs.admin.model;

import java.util.List;

import com.shanghaiwater.mcs.admin.common.ResponseModel;
import com.shanghaiwater.mcs.db.vo.IncidentMgtVO;

public class IncidentListResponse extends ResponseModel {

	private String count;

	private List<IncidentMgtVO> data;

	private Integer total;

	private boolean success;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public List<IncidentMgtVO> getData() {
		return data;
	}

	public void setData(List<IncidentMgtVO> data) {
		this.data = data;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
