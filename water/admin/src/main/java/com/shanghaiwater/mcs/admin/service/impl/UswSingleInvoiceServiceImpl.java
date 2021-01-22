package com.shanghaiwater.mcs.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.admin.service.UswSingleInvoiceService;
import com.shanghaiwater.mcs.common.util.SecurityUtil;
import com.shanghaiwater.mcs.db.entity.UswSingleInvoice;
import com.shanghaiwater.mcs.db.mapper.UswSingleInvoiceMapper;
import com.shanghaiwater.mcs.db.vo.SingleInvoiceIncidentMgtVO;

@Service
public class UswSingleInvoiceServiceImpl extends ServiceImpl<UswSingleInvoiceMapper, UswSingleInvoice> implements UswSingleInvoiceService {

	@Autowired
	private UswSingleInvoiceMapper uswSingleInvoiceMapper;
	
	@Override
	public PageListBasicResponse querySingleInvoiceList(Map<String, Object> map) {
		PageListBasicResponse response = new PageListBasicResponse();
		Integer count = uswSingleInvoiceMapper.querySingleInvoiceCount(map);
		List<SingleInvoiceIncidentMgtVO> list = uswSingleInvoiceMapper.querySingleInvocieList(map);
		
		for(SingleInvoiceIncidentMgtVO vo:list) {
			vo.setShwAddress(SecurityUtil.decryptAES(vo.getShwAddress()));
			vo.setCertNumber(SecurityUtil.decryptAES(vo.getCertNumber()));
			vo.setContactValue(SecurityUtil.decryptAES(vo.getContactValue()));
			vo.setRegisterAddress(SecurityUtil.decryptAES(vo.getRegisterAddress()));
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
