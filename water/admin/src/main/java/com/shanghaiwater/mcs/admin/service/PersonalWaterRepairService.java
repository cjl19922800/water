package com.shanghaiwater.mcs.admin.service;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.db.entity.RprUsewaterRepair;
import com.shanghaiwater.mcs.admin.model.WaterRepairRequest;
import com.shanghaiwater.mcs.admin.model.WaterRepairResponse;

public interface PersonalWaterRepairService extends IService<RprUsewaterRepair> {

	public Boolean personalRepair(WaterRepairRequest personalWaterRepairRequest, HttpServletRequest request,
			String itemCode);

	public WaterRepairResponse personalRepairQuery(String applyNo);

}
