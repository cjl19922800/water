package com.shanghaiwater.mcs.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.service.UswChargeCollectionService;
import com.shanghaiwater.mcs.common.util.SecurityUtil;
import com.shanghaiwater.mcs.db.entity.UswChargeCollection;
import com.shanghaiwater.mcs.db.mapper.UswChargeCollectionMapper;
import com.shanghaiwater.mcs.db.vo.ChargeCollectionIncidentMgtVO;

@Service
public class UswChargeCollectionServiceImpl extends ServiceImpl<UswChargeCollectionMapper, UswChargeCollection> implements UswChargeCollectionService {

	@Autowired
	private UswChargeCollectionMapper uswChargeCollectionMapper;
	
	
	@Override
	public PageListBasicResponse queryChargeCollectionList(Map<String, Object> map) {
		PageListBasicResponse response = new PageListBasicResponse();
		Integer count = uswChargeCollectionMapper.queryChargeCollectionCount(map);
		List<ChargeCollectionIncidentMgtVO> list = uswChargeCollectionMapper.queryChargeCollectionList(map);
		
		for(ChargeCollectionIncidentMgtVO info:list) {
			info.setShwAddress(SecurityUtil.decryptAES(info.getShwAddress()));
			info.setCertNumber(SecurityUtil.decryptAES(info.getCertNumber()));
			info.setFczjhm(SecurityUtil.decryptAES(info.getFczjhm()));
			info.setContactNum(SecurityUtil.decryptAES(info.getContactNum()));
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
