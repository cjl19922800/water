package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.db.entity.UswChargeCollection;

public interface UswChargeCollectionService extends IService<UswChargeCollection> {

	public PageListBasicResponse queryChargeCollectionList(Map<String, Object> map);
}
