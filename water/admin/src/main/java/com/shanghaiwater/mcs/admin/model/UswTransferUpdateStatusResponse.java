package com.shanghaiwater.mcs.admin.model;

import com.shanghaiwater.mcs.admin.common.ResponseModel;

public class UswTransferUpdateStatusResponse extends ResponseModel {

	private String incidentId;

	private Boolean success;

	public String getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

}
