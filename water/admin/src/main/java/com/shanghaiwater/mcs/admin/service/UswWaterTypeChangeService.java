package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.db.entity.UswWaterTypeChange;

public interface UswWaterTypeChangeService extends IService<UswWaterTypeChange> {

	
	public PageListBasicResponse queryWaterTypeChangeList(Map<String, Object> map);
}
