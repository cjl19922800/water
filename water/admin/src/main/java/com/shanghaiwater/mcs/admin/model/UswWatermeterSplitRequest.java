package com.shanghaiwater.mcs.admin.model;

import com.shanghaiwater.mcs.admin.common.RequestModel;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.UswWatermeterSplit;

public class UswWatermeterSplitRequest extends RequestModel{
	
	private UswWatermeterSplit uswWatermeterSplit;
	
	private McsIncident mcsIncident;

	public UswWatermeterSplit getUswWatermeterSplit() {
		return uswWatermeterSplit;
	}

	public void setUswWatermeterSplit(UswWatermeterSplit uswWatermeterSplit) {
		this.uswWatermeterSplit = uswWatermeterSplit;
	}

	public McsIncident getMcsIncident() {
		return mcsIncident;
	}

	public void setMcsIncident(McsIncident mcsIncident) {
		this.mcsIncident = mcsIncident;
	}

	

}
