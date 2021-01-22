package com.shanghaiwater.mcs.admin.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.db.entity.UswCompanyApply;
import com.shanghaiwater.mcs.admin.model.CompanyApplyListResponse;
import com.shanghaiwater.mcs.admin.model.CompanyApplyUpdateStatusResponse;

public interface UswCompanyApplyService extends IService<UswCompanyApply> {

	// add
	public CompanyApplyListResponse queryList(Map<String, Object> map);

	public CompanyApplyUpdateStatusResponse updateStatus(String incidentId, String status);

	public List<List<String>> queryImages(String incidentId);
}
