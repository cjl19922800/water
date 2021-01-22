package com.shanghaiwater.mcs.admin.model;

import java.util.List;

import com.shanghaiwater.mcs.admin.common.ResponseModel;
import com.shanghaiwater.mcs.admin.common.http.model.DictCode;
import com.shanghaiwater.mcs.admin.common.http.model.DictType;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.UswTransfer;

public class TransferResponse extends ResponseModel {

	private List<UswTransfer> list;

	private List<McsIncident> mcsIncident;

	private List<List<String>> src;

	private List<DictCode> dictCode;

	private List<DictType> type;

	private Integer total;

	private boolean success;

	public List<DictType> getType() {
		return type;
	}

	public void setType(List<DictType> type) {
		this.type = type;
	}

	public List<UswTransfer> getList() {
		return list;
	}

	public void setList(List<UswTransfer> list) {
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

	public List<List<String>> getSrc() {
		return src;
	}

	public void setSrc(List<List<String>> src) {
		this.src = src;
	}

	public List<McsIncident> getMcsIncident() {
		return mcsIncident;
	}

	public void setMcsIncident(List<McsIncident> mcsIncident) {
		this.mcsIncident = mcsIncident;
	}

	public List<DictCode> getDictCode() {
		return dictCode;
	}

	public void setDictCode(List<DictCode> dictCode) {
		this.dictCode = dictCode;
	}

}
