package com.shanghaiwater.mcs.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.service.UswPayRemailService;
import com.shanghaiwater.mcs.common.util.SecurityUtil;
import com.shanghaiwater.mcs.db.entity.UswPayRemailApply;
import com.shanghaiwater.mcs.db.mapper.UswPayRemailApplyMapper;
import com.shanghaiwater.mcs.db.vo.PayRemailIncidentMgtVO;

@Service
public class PayRemailServiceImpl extends ServiceImpl<UswPayRemailApplyMapper, UswPayRemailApply> implements UswPayRemailService{

	@Autowired
	private UswPayRemailApplyMapper uswPayRemailApplyMapper;
	
	@Override
	public PageListBasicResponse queryPayRemailList(Map<String, Object> map) {
		PageListBasicResponse response = new PageListBasicResponse();
		Integer count = uswPayRemailApplyMapper.queryPayRemailCount(map);
		List<PayRemailIncidentMgtVO> list = uswPayRemailApplyMapper.queryPayRemailList(map);
		
		
		for(PayRemailIncidentMgtVO vo:list) {
			vo.setShwAddress(SecurityUtil.decryptAES(vo.getShwAddress()));
			vo.setCertNumber(SecurityUtil.decryptAES(vo.getCertNumber()));
			vo.setContactValue(SecurityUtil.decryptAES(vo.getContactValue()));
			vo.setMailAddress(SecurityUtil.decryptAES(vo.getMailAddress()));
			vo.setAddPhone(SecurityUtil.decryptAES(vo.getAddPhone()));
			vo.setAddressee(SecurityUtil.decryptAES(vo.getAddressee()));
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
