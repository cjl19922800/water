package com.shanghaiwater.mcs.admin.model;

import com.shanghaiwater.mcs.admin.common.RequestModel;

public class RprUsewaterUpdateStatusRequest extends RequestModel {

	private String incidentId;

	private String status;

	public String getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
