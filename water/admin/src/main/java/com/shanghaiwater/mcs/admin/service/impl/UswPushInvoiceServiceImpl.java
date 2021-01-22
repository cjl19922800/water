package com.shanghaiwater.mcs.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.service.UswPushInvoiceService;
import com.shanghaiwater.mcs.common.util.SecurityUtil;
import com.shanghaiwater.mcs.db.entity.UswPushInvoice;
import com.shanghaiwater.mcs.db.mapper.UswPushInvoiceMapper;
import com.shanghaiwater.mcs.db.vo.PushInvoiceIncidentMgtVO;

@Service
public class UswPushInvoiceServiceImpl extends ServiceImpl<UswPushInvoiceMapper, UswPushInvoice> implements UswPushInvoiceService {

	
	@Autowired
	private UswPushInvoiceMapper uswPushInvoiceMapper;
	
	
	@Override
	public PageListBasicResponse queryPushInvoiceList(Map<String, Object> map) {
		PageListBasicResponse response = new PageListBasicResponse();
		Integer count = uswPushInvoiceMapper.queryPushInvocieCount(map);
		List<PushInvoiceIncidentMgtVO> list = uswPushInvoiceMapper.queryPushInvoiceList(map);
		
		for(PushInvoiceIncidentMgtVO vo:list) {
			vo.setShwAddress(SecurityUtil.decryptAES(vo.getShwAddress()));
			vo.setCertNumber(SecurityUtil.decryptAES(vo.getCertNumber()));
			vo.setContactValue(SecurityUtil.decryptAES(vo.getContactValue()));
			vo.setRegisteAddress(SecurityUtil.decryptAES(vo.getRegisteAddress()));
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
