package com.shanghaiwater.mcs.admin.model;

import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.WaterRepair;
import com.shanghaiwater.mcs.common.model.RequestModel;

public class WaterRepairRequest extends RequestModel {

	private WaterRepair waterRepair;
	
	private McsIncident incident;
	
	private String town;

	public WaterRepair getWaterRepair() {
		return waterRepair;
	}

	public void setWaterRepair(WaterRepair waterRepair) {
		this.waterRepair = waterRepair;
	}

	public McsIncident getIncident() {
		return incident;
	}

	public void setIncident(McsIncident incident) {
		this.incident = incident;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	
}
