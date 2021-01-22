package com.shanghaiwater.mcs.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.service.UswOpenMeterService;
import com.shanghaiwater.mcs.common.util.SecurityUtil;
import com.shanghaiwater.mcs.db.entity.UswOpenMeter;
import com.shanghaiwater.mcs.db.mapper.UswOpenMeterMapper;
import com.shanghaiwater.mcs.db.vo.OpenMeterIncidentMgtVO;

@Service
public class UswOpenMeterServiceImpl extends ServiceImpl<UswOpenMeterMapper, UswOpenMeter> implements UswOpenMeterService {

	@Autowired
	private UswOpenMeterMapper uswOpenMeterMapper;
	
	@Override
	public PageListBasicResponse queryOpenMeterList(Map<String, Object> map) {
		PageListBasicResponse response = new PageListBasicResponse();
		Integer count = uswOpenMeterMapper.queryOpenMeterCount(map);
		List<OpenMeterIncidentMgtVO> list = uswOpenMeterMapper.queryOpenMeterList(map);
		
		for(OpenMeterIncidentMgtVO vo:list) {
			vo.setShwAddress(SecurityUtil.decryptAES(vo.getShwAddress()));
			vo.setCertNumber(SecurityUtil.decryptAES(vo.getCertNumber()));
			vo.setHouseCertNo(SecurityUtil.decryptAES(vo.getHouseCertNo()));
			vo.setContactNum(SecurityUtil.decryptAES(vo.getContactNum()));
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
