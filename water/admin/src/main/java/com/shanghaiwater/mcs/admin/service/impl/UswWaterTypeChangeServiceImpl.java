package com.shanghaiwater.mcs.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.service.UswWaterTypeChangeService;
import com.shanghaiwater.mcs.common.util.SecurityUtil;
import com.shanghaiwater.mcs.db.entity.UswWaterTypeChange;
import com.shanghaiwater.mcs.db.mapper.UswWaterTypeChangeMapper;
import com.shanghaiwater.mcs.db.vo.WaterTypeChangeIncidentMgtVO;

@Service
public class UswWaterTypeChangeServiceImpl extends ServiceImpl<UswWaterTypeChangeMapper, UswWaterTypeChange> implements UswWaterTypeChangeService {

	@Autowired
	private UswWaterTypeChangeMapper uswWaterTypeChangeMapper;
	
	
	@Override
	public PageListBasicResponse queryWaterTypeChangeList(Map<String, Object> map) {
		PageListBasicResponse response = new PageListBasicResponse();
		Integer count = uswWaterTypeChangeMapper.queryWaterTypeChangeCount(map);
		List<WaterTypeChangeIncidentMgtVO> list = uswWaterTypeChangeMapper.queryWaterTypeChangeList(map);
		
		for(WaterTypeChangeIncidentMgtVO vo:list) {
			vo.setShwAddress(SecurityUtil.decryptAES(vo.getShwAddress()));
			vo.setCertNumber(SecurityUtil.decryptAES(vo.getCertNumber()));
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
