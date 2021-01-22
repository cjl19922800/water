package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.db.entity.UswMulitiPop;

public interface UswMultiPopService extends IService<UswMulitiPop>{

	public PageListBasicResponse queryList(Map<String, Object> map);

	
	
}
