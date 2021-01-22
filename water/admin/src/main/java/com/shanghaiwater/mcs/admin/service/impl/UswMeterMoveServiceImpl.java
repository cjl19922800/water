package com.shanghaiwater.mcs.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.service.UswMeterMoveService;
import com.shanghaiwater.mcs.common.util.SecurityUtil;
import com.shanghaiwater.mcs.db.entity.UswMeterMove;
import com.shanghaiwater.mcs.db.mapper.UswMeterMoveMapper;
import com.shanghaiwater.mcs.db.vo.MeterMoveIncidentMgtVO;

@Service
public class UswMeterMoveServiceImpl extends ServiceImpl<UswMeterMoveMapper, UswMeterMove> implements UswMeterMoveService{

	@Autowired
	private UswMeterMoveMapper uswMeterMoveMapper;
	
	@Override
	public PageListBasicResponse queryMeterMoveList(Map<String, Object> map) {
		PageListBasicResponse response = new PageListBasicResponse();
		Integer count = uswMeterMoveMapper.queryMeterMoveCount(map);
		List<MeterMoveIncidentMgtVO> list = uswMeterMoveMapper.queryMeterMoveList(map);
		
		for(MeterMoveIncidentMgtVO vo:list) {
			vo.setShwAddress(SecurityUtil.decryptAES(vo.getShwAddress()));
			vo.setCertNumber(SecurityUtil.decryptAES(vo.getCertNumber()));
			vo.setHouseCertNo(SecurityUtil.decryptAES(vo.getHouseCertNo()));
			vo.setContactNumber(SecurityUtil.decryptAES(vo.getContactNumber()));
		}
		
		System.out.println(count);
		System.out.println(list);
		
		response.setData(list);
		response.setCode(0);
		response.setCount(String.valueOf(count));
		response.setTotal(count);
		
		return response;
	}

}
