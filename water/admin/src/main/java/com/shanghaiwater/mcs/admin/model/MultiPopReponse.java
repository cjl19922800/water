package com.shanghaiwater.mcs.admin.model;

import java.util.List;

import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.UswMulitiPop;
import com.shanghaiwater.mcs.db.entity.UswMulitiPopAppCert;

import lombok.Data;

public class MultiPopReponse {
	
	public McsIncident incident;
	
	public UswMulitiPop pop;
	
	public List<MultiPopBenReponse> benList;
	
	public List<UswMulitiPopAppCert> appCertList;

	public McsIncident getIncident() {
		return incident;
	}

	public void setIncident(McsIncident incident) {
		this.incident = incident;
	}

	public UswMulitiPop getPop() {
		return pop;
	}

	public void setPop(UswMulitiPop pop) {
		this.pop = pop;
	}

	public List<MultiPopBenReponse> getBenList() {
		return benList;
	}

	public void setBenList(List<MultiPopBenReponse> benList) {
		this.benList = benList;
	}

	public List<UswMulitiPopAppCert> getAppCertList() {
		return appCertList;
	}

	public void setAppCertList(List<UswMulitiPopAppCert> appCertList) {
		this.appCertList = appCertList;
	}
	
}
