package com.shanghaiwater.mcs.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.service.UswRealNameService;
import com.shanghaiwater.mcs.common.util.SecurityUtil;
import com.shanghaiwater.mcs.db.entity.UswRealNameRegiste;
import com.shanghaiwater.mcs.db.mapper.UswRealNameRegisteMapper;
import com.shanghaiwater.mcs.db.vo.RealNameIncidentMgtVO;

@Service
public class RealNameServiceImpl extends ServiceImpl<UswRealNameRegisteMapper, UswRealNameRegiste> implements UswRealNameService{

	@Autowired
	private UswRealNameRegisteMapper uswRealNameRegisteMapper;
	
	@Override
	public PageListBasicResponse queryRealNameList(Map<String, Object> map) {
		PageListBasicResponse response = new PageListBasicResponse();
		Integer count = uswRealNameRegisteMapper.queryRealNameCount(map);
		List<RealNameIncidentMgtVO> list = uswRealNameRegisteMapper.queryRealNameList(map);
		
		for(RealNameIncidentMgtVO vo:list) {
			vo.setShwAddress(SecurityUtil.decryptAES(vo.getShwAddress()));
			vo.setCertNumber(SecurityUtil.decryptAES(vo.getCertNumber()));
			vo.setProOwnerPhone(SecurityUtil.decryptAES(vo.getProOwnerPhone()));
			vo.setUserCardId(SecurityUtil.decryptAES(vo.getUserCardId()));
			vo.setHouseCertNo(SecurityUtil.decryptAES(vo.getHouseCertNo()));
			vo.setApplyPhone(SecurityUtil.decryptAES(vo.getApplyPhone()));
		}
		
		
		System.out.println(count);
		System.out.println(list);
		
		response.setData(list);
		response.setCode(0);
		response.setCount(String.valueOf(count));
		response.setTotal(count);
		
		
		// TODO Auto-generated method stub
		return response;
	}

}
