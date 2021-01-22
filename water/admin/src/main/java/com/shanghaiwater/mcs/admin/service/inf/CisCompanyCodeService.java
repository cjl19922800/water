package com.shanghaiwater.mcs.admin.service.inf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shanghaiwater.mcs.admin.feign.CodeService;
import com.shanghaiwater.mcs.common.model.masterdata.WaterCompanyResponse;
import com.shanghaiwater.mcs.common.vo.WaterCompany;

@Service
public class CisCompanyCodeService {

	@Autowired
	private CodeService codeService;

	public List<WaterCompany> selectAllCisCompany() {
		WaterCompanyResponse response = codeService.selectAllCisCompany();
		List<WaterCompany> data = response.getData();
		return data;
	}

	List<WaterCompany> selectCisCompanyByCompanyCode(String str) {
		return null;
	}

}
