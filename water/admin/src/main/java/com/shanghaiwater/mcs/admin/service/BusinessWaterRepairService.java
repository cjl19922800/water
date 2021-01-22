package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.admin.common.ResponseSuccessModel;
import com.shanghaiwater.mcs.db.entity.RprUsewaterRepair;
import com.shanghaiwater.mcs.admin.model.RepairListResponse;
import com.shanghaiwater.mcs.admin.model.RprUsewaterRepairListResponse;
import com.shanghaiwater.mcs.admin.model.RprUsewaterUpdateStatusResponse;
import com.shanghaiwater.mcs.admin.model.UseWaterRepairResponse;
import com.shanghaiwater.mcs.admin.model.WaterRepairResponse;

public interface BusinessWaterRepairService extends IService<RprUsewaterRepair> {

	public WaterRepairResponse businessRepairQuery(String applyNo);

	public UseWaterRepairResponse queryRepairList(String type);

	public RprUsewaterRepairListResponse queryList(Map<String, Object> map);

	public ResponseSuccessModel updateStatus(String incidentId, String status);

	
	/**
	 * 报修单列表查询
	 * @param map
	 * @return
	 */
	public RepairListResponse queryRepairList(Map<String, Object> map);

//	public RprUsewaterUpdateStatusResponse updateStatus(String incidentId, String status);
}
