package com.shanghaiwater.mcs.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.service.UswCustomerChangeService;
import com.shanghaiwater.mcs.common.util.SecurityUtil;
import com.shanghaiwater.mcs.db.entity.UswUpdateInf;
import com.shanghaiwater.mcs.db.mapper.UswUpdateInfMapper;
import com.shanghaiwater.mcs.db.vo.CusChangeIncidentMgtVO;

@Service
public class UswCustomerChangeServiceImpl extends ServiceImpl<UswUpdateInfMapper, UswUpdateInf> implements UswCustomerChangeService{

	@Autowired
	private UswUpdateInfMapper uswUpdateInfMapper;
	
	@Override
	public PageListBasicResponse queryCusChangeList(Map<String, Object> map) {
		
		PageListBasicResponse response = new PageListBasicResponse();
		Integer count = uswUpdateInfMapper.queryCusChangeCount(map);
		List<CusChangeIncidentMgtVO> list = uswUpdateInfMapper.queryCusChangeList(map);
		
		for(CusChangeIncidentMgtVO vo:list) {
			vo.setShwAddress(SecurityUtil.decryptAES(vo.getShwAddress()));
			vo.setCertNumber(SecurityUtil.decryptAES(vo.getCertNumber()));
			vo.setMailAddress(SecurityUtil.decryptAES(vo.getMailAddress()));
			
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
