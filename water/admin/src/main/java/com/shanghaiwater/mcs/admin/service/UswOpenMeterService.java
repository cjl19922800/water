package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.db.entity.UswOpenMeter;

public interface UswOpenMeterService extends IService<UswOpenMeter> {

	public PageListBasicResponse queryOpenMeterList(Map<String, Object> map);
}
