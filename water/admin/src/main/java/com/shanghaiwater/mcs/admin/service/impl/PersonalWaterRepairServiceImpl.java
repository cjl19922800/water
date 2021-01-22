package com.shanghaiwater.mcs.admin.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.RprUsewaterRepair;
import com.shanghaiwater.mcs.db.mapper.McsIncidentHisMapper;
import com.shanghaiwater.mcs.db.mapper.McsIncidentMapper;
import com.shanghaiwater.mcs.db.mapper.RprUsewaterRepairMapper;
import com.shanghaiwater.mcs.admin.model.WaterRepairRequest;
import com.shanghaiwater.mcs.admin.model.WaterRepairResponse;
import com.shanghaiwater.mcs.admin.service.PersonalWaterRepairService;

@Service
public class PersonalWaterRepairServiceImpl extends ServiceImpl<RprUsewaterRepairMapper, RprUsewaterRepair>
		implements PersonalWaterRepairService {

	@Autowired
	private RprUsewaterRepairMapper mcsMapper;

	@Autowired
	private McsIncidentMapper mcsIncidentMapper;

	@Autowired
	private McsIncidentHisMapper mcsIncidentHisMapper;

	@Override
	public Boolean personalRepair(WaterRepairRequest waterRepairRequest, HttpServletRequest request, String itemCode) {
		boolean result = false;

		return result;
	}

	@Override
	public WaterRepairResponse personalRepairQuery(String applyNo) {

		WaterRepairResponse repairResponse = new WaterRepairResponse();

		QueryWrapper<McsIncident> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("apply_no", applyNo);
		List<McsIncident> list = mcsIncidentMapper.selectList(queryWrapper);
		String incidentId = list.get(0).getIncidentId();

		QueryWrapper<RprUsewaterRepair> queryWrapper2 = new QueryWrapper<>();
		queryWrapper2.eq("incident_id", incidentId);
		List<RprUsewaterRepair> list2 = mcsMapper.selectList(queryWrapper2);

		repairResponse.setMcsIncident(list);
		repairResponse.setList(list2);
		return repairResponse;
	}

}
