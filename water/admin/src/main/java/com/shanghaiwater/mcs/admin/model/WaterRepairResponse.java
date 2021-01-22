package com.shanghaiwater.mcs.admin.model;

import java.util.List;

import com.shanghaiwater.mcs.common.model.ResponseModel;
import com.shanghaiwater.mcs.common.vo.DictItem;
import com.shanghaiwater.mcs.common.vo.DictType;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.RprUsewaterRepair;

public class WaterRepairResponse extends ResponseModel {

	private List<RprUsewaterRepair> list;

	private List<McsIncident> mcsIncident;

	private List<DictItem> code;

	private List<DictType> type;

	public List<DictType> getType() {
		return type;
	}

	public void setType(List<DictType> type) {
		this.type = type;
	}

	public List<DictItem> getCode() {
		return code;
	}

	public void setCode(List<DictItem> code) {
		this.code = code;
	}

	private Integer total;

	private boolean success;

	public List<RprUsewaterRepair> getList() {
		return list;
	}

	public void setList(List<RprUsewaterRepair> list) {
		this.list = list;
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

	public List<McsIncident> getMcsIncident() {
		return mcsIncident;
	}

	public void setMcsIncident(List<McsIncident> mcsIncident) {
		this.mcsIncident = mcsIncident;
	}

}
