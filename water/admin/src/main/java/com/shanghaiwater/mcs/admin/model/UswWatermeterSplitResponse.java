package com.shanghaiwater.mcs.admin.model;

import java.util.List;

import com.shanghaiwater.mcs.admin.common.ResponseModel;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.UswWatermeterSplit;

public class UswWatermeterSplitResponse extends ResponseModel{

	private List<McsIncident> mcsIncident;
	
	private List<UswWatermeterSplit> list;
	
	private List<List<String>> src;
	
	private Integer total;
	
	private boolean success;


	public List<UswWatermeterSplit> getList() {
		return list;
	}

	public void setList(List<UswWatermeterSplit> list) {
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

	public List<List<String>> getSrc() {
		return src;
	}

	public void setSrc(List<List<String>> src) {
		this.src = src;
	}

	
}
