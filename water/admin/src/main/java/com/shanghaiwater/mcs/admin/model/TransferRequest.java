package com.shanghaiwater.mcs.admin.model;

import com.shanghaiwater.mcs.admin.common.RequestModel;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.UswTransfer;

public class TransferRequest extends RequestModel {

	private UswTransfer transfer;
	
	private McsIncident mcsIncident;

	public UswTransfer getTransfer() {
		return transfer;
	}

	public void setTransfer(UswTransfer transfer) {
		this.transfer = transfer;
	}

	public McsIncident getMcsIncident() {
		return mcsIncident;
	}

	public void setMcsIncident(McsIncident mcsIncident) {
		this.mcsIncident = mcsIncident;
	}
	
	


	
}
