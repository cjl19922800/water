package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.db.entity.UswShareMeter;

public interface UswShareMeterService extends IService<UswShareMeter> {

	
	public PageListBasicResponse queryShareMeterList(Map<String, Object> map);
}
