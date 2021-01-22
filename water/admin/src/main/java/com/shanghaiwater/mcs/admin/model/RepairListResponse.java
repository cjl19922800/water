package com.shanghaiwater.mcs.admin.model;

import java.util.List;

import com.shanghaiwater.mcs.admin.common.ResponseModel;
import com.shanghaiwater.mcs.db.vo.RapirIncidentMgtVO;
import com.shanghaiwater.mcs.db.vo.RprUsewaterRepairMgtVO;


public class RepairListResponse extends ResponseModel {

	private String count;

	private List<RapirIncidentMgtVO> data;

	private Integer total;

	private Boolean success;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
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

	public List<RapirIncidentMgtVO> getData() {
		return data;
	}

	public void setData(List<RapirIncidentMgtVO> data) {
		this.data = data;
	}

}
