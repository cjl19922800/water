package com.shanghaiwater.mcs.admin.service;

import com.shanghaiwater.mcs.common.model.WSAddressResult;
import com.shanghaiwater.mcs.common.model.WSRepairResult;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.RprUsewaterRepair;
import com.shanghaiwater.mcs.db.entity.UswResidentApply;
import com.shanghaiwater.mcs.db.entity.UswTransfer;
import com.shanghaiwater.mcs.db.entity.UswWatermeterSplit;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MCS
 * 
 */
public interface WebServiceIFService {
	public WSAddressResult getAddress(String companyCode, String cardId);

	public WSRepairResult repair(McsIncident incident, RprUsewaterRepair rprUsewaterRepair);

	public WSRepairResult waterApply(McsIncident i, UswResidentApply r);

	public WSRepairResult waterSplitApply(McsIncident incident, UswWatermeterSplit split);

	public WSRepairResult waterTransferApply(McsIncident incident, UswTransfer transfer);



}
