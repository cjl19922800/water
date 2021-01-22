package com.shanghaiwater.mcs.admin.model;

import com.shanghaiwater.mcs.admin.common.RequestModel;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.UswResidentApply;

public class ResidentApplyWaterRequest extends RequestModel{
	
	private UswResidentApply residentApplyWater;
	
	private McsIncident incident;

	public UswResidentApply getResidentApplyWater() {
		return residentApplyWater;
	}

	public void setResidentApplyWater(UswResidentApply residentApplyWater) {
		this.residentApplyWater = residentApplyWater;
	}

	public McsIncident getIncident() {
		return incident;
	}

	public void setIncident(McsIncident incident) {
		this.incident = incident;
	}

	
}
