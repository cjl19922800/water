package com.shanghaiwater.mcs.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.service.UswMultiPopService;
import com.shanghaiwater.mcs.common.util.SecurityUtil;
import com.shanghaiwater.mcs.db.entity.UswMulitiPop;
import com.shanghaiwater.mcs.db.mapper.UswMulitiPopMapper;
import com.shanghaiwater.mcs.db.vo.MultiPopMgVO;

@Service
public class UswMultiPopServiceImpl extends ServiceImpl<UswMulitiPopMapper, UswMulitiPop> implements UswMultiPopService{

	
	@Autowired
	private UswMulitiPopMapper uswMulitiPopMapper;
	
	@Override
	public PageListBasicResponse queryList(Map<String, Object> map) {
		PageListBasicResponse response = new PageListBasicResponse();
		Integer count = uswMulitiPopMapper.queryPopCount(map);
		List<MultiPopMgVO> list = uswMulitiPopMapper.queryPopList(map);
		for(MultiPopMgVO vo:list) {
			vo.setShwAddress(SecurityUtil.decryptAES(vo.getShwAddress()));
			vo.setCertNumber(SecurityUtil.decryptAES(vo.getCertNumber()));
			vo.setApplyAddress(SecurityUtil.decryptAES(vo.getApplyAddress()));
			vo.setPhone(SecurityUtil.decryptAES(vo.getPhone()));
		}
		
		response.setData(list);
		response.setCode(0);
		response.setCount(String.valueOf(count));
		response.setTotal(count);
		return response;
	}
	
	
	
}
