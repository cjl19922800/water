package com.shanghaiwater.mcs.admin.model;

import java.util.List;

import com.shanghaiwater.mcs.admin.common.ResponseModel;
import com.shanghaiwater.mcs.db.vo.ResidentTransferMgtVO;

public class UswTransferListResponse extends ResponseModel {

	private String count;

	private List<ResidentTransferMgtVO> data;

	private Integer total;

	private Boolean success;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public List<ResidentTransferMgtVO> getData() {
		return data;
	}

	public void setData(List<ResidentTransferMgtVO> data) {
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
