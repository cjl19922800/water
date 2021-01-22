package com.shanghaiwater.mcs.admin.model;

import java.util.List;

import com.shanghaiwater.mcs.db.entity.UswMulitiPopBen;
import com.shanghaiwater.mcs.db.entity.UswMulitiPopBenCert;

import lombok.Data;

public class MultiPopBenReponse {
	
	public UswMulitiPopBen ben;
	
	public List<UswMulitiPopBenCert> certList;

	public UswMulitiPopBen getBen() {
		return ben;
	}

	public void setBen(UswMulitiPopBen ben) {
		this.ben = ben;
	}

	public List<UswMulitiPopBenCert> getCertList() {
		return certList;
	}

	public void setCertList(List<UswMulitiPopBenCert> certList) {
		this.certList = certList;
	}
	
	
}
