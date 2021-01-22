package com.shanghaiwater.mcs.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.admin.common.ResponseSuccessModel;
import com.shanghaiwater.mcs.admin.model.RepairListResponse;
import com.shanghaiwater.mcs.admin.model.RprUsewaterRepairListResponse;
import com.shanghaiwater.mcs.admin.model.UseWaterRepairResponse;
import com.shanghaiwater.mcs.admin.model.WaterRepairResponse;
import com.shanghaiwater.mcs.admin.service.BusinessWaterRepairService;
import com.shanghaiwater.mcs.common.util.SecurityUtil;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.db.entity.RprUsewaterRepair;
import com.shanghaiwater.mcs.db.mapper.McsIncidentMapper;
import com.shanghaiwater.mcs.db.mapper.RprUsewaterRepairMapper;
import com.shanghaiwater.mcs.db.vo.RapirIncidentMgtVO;
import com.shanghaiwater.mcs.db.vo.RprUsewaterRepairMgtVO;

@Service
public class BusinessWaterRepairServiceImpl extends ServiceImpl<RprUsewaterRepairMapper, RprUsewaterRepair>
		implements BusinessWaterRepairService {

	@Autowired
	private RprUsewaterRepairMapper rprUsewaterRepairMapper;

	@Autowired
	private McsIncidentMapper mcsIncidentMapper;

	@Override
	public WaterRepairResponse businessRepairQuery(String applyNo) {
		WaterRepairResponse repairResponse = new WaterRepairResponse();

		QueryWrapper<McsIncident> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("apply_no", applyNo);
		List<McsIncident> list = mcsIncidentMapper.selectList(queryWrapper);
		String incidentId = list.get(0).getIncidentId();

		QueryWrapper<RprUsewaterRepair> queryWrapper2 = new QueryWrapper<>();
		queryWrapper2.eq("incident_id", incidentId);
		List<RprUsewaterRepair> list2 = rprUsewaterRepairMapper.selectList(queryWrapper2);

		repairResponse.setMcsIncident(list);
		repairResponse.setList(list2);
		return repairResponse;
	}

	@Override
	public UseWaterRepairResponse queryRepairList(String type) {
		UseWaterRepairResponse repairResponse = new UseWaterRepairResponse();

		QueryWrapper<McsIncident> queryWrapper = new QueryWrapper<>();
		List<McsIncident> list = mcsIncidentMapper.selectList(queryWrapper);

		QueryWrapper<RprUsewaterRepair> queryWrapper2 = new QueryWrapper<>();
		List<RprUsewaterRepair> list2 = rprUsewaterRepairMapper.selectList(queryWrapper2);

		repairResponse.setMcsIncident(list);
		repairResponse.setData(list2);

		repairResponse.setCode(0);
		repairResponse.setCount("100");
		return repairResponse;
	}

	@Override
	public RprUsewaterRepairListResponse queryList(Map<String, Object> map) {
		RprUsewaterRepairListResponse result = new RprUsewaterRepairListResponse();

		
		List<RapirIncidentMgtVO> dataList = rprUsewaterRepairMapper.queryRepairList(map);
		
		System.out.println(dataList);
		List<RprUsewaterRepairMgtVO> data = rprUsewaterRepairMapper.selectRprUsewaterList(map);
		result.setData(data);

		result.setCode(0);
		result.setCount(data.size() + "");
		return result;
	}
	
	@Override
	public RepairListResponse queryRepairList(Map<String,Object> map) {
		RepairListResponse response = new RepairListResponse();
		Integer count = rprUsewaterRepairMapper.selectRepairCount(map);
		List<RapirIncidentMgtVO> dataList = rprUsewaterRepairMapper.queryRepairList(map);
		
		for(RapirIncidentMgtVO vo:dataList) {
			vo.setShwAddress(SecurityUtil.decryptAES(vo.getShwAddress()));
			vo.setCertNumber(SecurityUtil.decryptAES(vo.getCertNumber()));
		}
		
		response.setData(dataList);
		response.setCode(0);
		response.setCount(String.valueOf(count));
		response.setTotal(count);
		return response;
		
	}
	

	@Override
	public ResponseSuccessModel updateStatus(String incidentId, String status) {
		ResponseSuccessModel responseSuccessModel = new ResponseSuccessModel();
		McsIncident mcsIncident = mcsIncidentMapper.selectById(incidentId);
		mcsIncident.setStatus(status);
		mcsIncidentMapper.updateById(mcsIncident);
		responseSuccessModel.setSuccess(true);
		return responseSuccessModel;
	}

//	@Override
//	public RprUsewaterUpdateStatusResponse updateStatus(String incidentId, String status) {
//		RprUsewaterUpdateStatusResponse residentApplyUpdateStatusResponse = new RprUsewaterUpdateStatusResponse();
//
//		McsIncident mcsIncident = mcsIncidentMapper.selectById(incidentId);
//		mcsIncident.setStatus(status);
//
//		mcsIncidentMapper.updateById(mcsIncident);
//
//		return residentApplyUpdateStatusResponse;
//	}

}
