package com.shanghaiwater.mcs.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.service.UswAccountCancelService;
import com.shanghaiwater.mcs.common.util.SecurityUtil;
import com.shanghaiwater.mcs.db.entity.UswAccountCancel;
import com.shanghaiwater.mcs.db.mapper.UswAccountCancelMapper;
import com.shanghaiwater.mcs.db.vo.AccountCancelIncidentMgtVO;

@Service
public class UswAccountCancelServiceImpl extends ServiceImpl<UswAccountCancelMapper, UswAccountCancel> implements UswAccountCancelService {

	@Autowired
	private UswAccountCancelMapper uswAccountCancelMapper;
	
	@Override
	public PageListBasicResponse queryAccountCancelList(Map<String, Object> map) {
		PageListBasicResponse response = new PageListBasicResponse();
		Integer count = uswAccountCancelMapper.queryAccountCancelCount(map);
		List<AccountCancelIncidentMgtVO> list = uswAccountCancelMapper.queryAccountCancelList(map);
		
		for(AccountCancelIncidentMgtVO vo:list) {
			vo.setShwAddress(SecurityUtil.decryptAES(vo.getShwAddress()));
			vo.setCertNumber(SecurityUtil.decryptAES(vo.getCertNumber()));
			vo.setFczjhm(SecurityUtil.decryptAES(vo.getFczjhm()));
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
