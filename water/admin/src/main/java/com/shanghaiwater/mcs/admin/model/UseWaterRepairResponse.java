package com.shanghaiwater.mcs.admin.model;

import java.util.List;

import com.shanghaiwater.mcs.admin.common.ResponseModel;
import com.shanghaiwater.mcs.common.vo.DictType;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.RprUsewaterRepair;

public class UseWaterRepairResponse extends ResponseModel {

	private List<RprUsewaterRepair> data;

	private List<McsIncident> mcsIncident;

	private String count;

	private List<DictType> type;

	public List<DictType> getType() {
		return type;
	}

	public void setType(List<DictType> type) {
		this.type = type;
	}

	private Integer total;

	private boolean success;

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

	public List<McsIncident> getMcsIncident() {
		return mcsIncident;
	}

	public void setMcsIncident(List<McsIncident> mcsIncident) {
		this.mcsIncident = mcsIncident;
	}

	public List<RprUsewaterRepair> getData() {
		return data;
	}

	public void setData(List<RprUsewaterRepair> data) {
		this.data = data;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}
