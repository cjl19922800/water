package com.shanghaiwater.mcs.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.service.UswAdviceComplaintService;
import com.shanghaiwater.mcs.common.util.SecurityUtil;
import com.shanghaiwater.mcs.db.entity.UswAdviceComplaint;
import com.shanghaiwater.mcs.db.mapper.UswAdviceComplaintMapper;
import com.shanghaiwater.mcs.db.vo.AdviceComplainIncidentMgtVO;

@Service
public class UswAdviceComplaintServiceImpl extends ServiceImpl<UswAdviceComplaintMapper, UswAdviceComplaint> implements UswAdviceComplaintService {

	@Autowired
	private UswAdviceComplaintMapper uswAdviceComplaintMapper;
	
	@Override
	public PageListBasicResponse queryAdviceComplaintList(Map<String, Object> map) {
		PageListBasicResponse response = new PageListBasicResponse();
		Integer count = uswAdviceComplaintMapper.queryAdviceComplaintCount(map);
		List<AdviceComplainIncidentMgtVO> list = uswAdviceComplaintMapper.queryAdviceComplaintList(map);
		
		for(AdviceComplainIncidentMgtVO vo:list) {
			vo.setShwAddress(SecurityUtil.decryptAES(vo.getShwAddress()));
			vo.setCertNumber(SecurityUtil.decryptAES(vo.getCertNumber()));
			vo.setContactValue(SecurityUtil.decryptAES(vo.getContactValue()));
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
