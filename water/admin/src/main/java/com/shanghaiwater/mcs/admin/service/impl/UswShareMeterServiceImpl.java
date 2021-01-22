package com.shanghaiwater.mcs.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.service.UswShareMeterService;
import com.shanghaiwater.mcs.common.util.SecurityUtil;
import com.shanghaiwater.mcs.db.entity.UswShareMeter;
import com.shanghaiwater.mcs.db.mapper.UswShareMeterMapper;
import com.shanghaiwater.mcs.db.vo.ShareMeterIncidentMgtVO;

@Service
public class UswShareMeterServiceImpl extends ServiceImpl<UswShareMeterMapper, UswShareMeter> implements UswShareMeterService {

	
	@Autowired
	private UswShareMeterMapper uswShareMeterMapper;
	
	@Override
	public PageListBasicResponse queryShareMeterList(Map<String, Object> map) {
		PageListBasicResponse response = new PageListBasicResponse();
		Integer count = uswShareMeterMapper.queryShareMeterCount(map);
		List<ShareMeterIncidentMgtVO> list = uswShareMeterMapper.queryShareMeterList(map);
		
		for(ShareMeterIncidentMgtVO vo:list) {
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
