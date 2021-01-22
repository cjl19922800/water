package com.shanghaiwater.mcs.admin.model;

import java.util.List;

import com.shanghaiwater.mcs.admin.common.ResponseModel;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.UswCompanyApply;

public class PasswordPolicyResponse extends ResponseModel{

	private List<UswCompanyApply> list;
	
	private List<McsIncident> mcsIncident;
	
	private List<List<String>> src;
	
	private Integer total;
	
	private boolean success;
	
	public List<List<String>> getSrc() {
		return src;
	}

	public void setSrc(List<List<String>> src) {
		this.src = src;
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

	public List<UswCompanyApply> getList() {
		return list;
	}

	public void setList(List<UswCompanyApply> list) {
		this.list = list;
	}

	public List<McsIncident> getMcsIncident() {
		return mcsIncident;
	}

	public void setMcsIncident(List<McsIncident> mcsIncident) {
		this.mcsIncident = mcsIncident;
	}

	
}
