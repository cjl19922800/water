package com.shanghaiwater.mcs.admin.model;

import com.shanghaiwater.mcs.admin.common.RequestModel;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.UswCompanyApply;

public class CompanyApplyWaterRequest extends RequestModel{
	
	private UswCompanyApply residentApplyWater;
	
	private McsIncident mcsIncident;

	public UswCompanyApply getResidentApplyWater() {
		return residentApplyWater;
	}

	public void setResidentApplyWater(UswCompanyApply residentApplyWater) {
		this.residentApplyWater = residentApplyWater;
	}

	public McsIncident getMcsIncident() {
		return mcsIncident;
	}

	public void setMcsIncident(McsIncident mcsIncident) {
		this.mcsIncident = mcsIncident;
	}

	
	

}
