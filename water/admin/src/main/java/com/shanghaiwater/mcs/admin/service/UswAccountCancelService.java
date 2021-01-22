package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.db.entity.UswAccountCancel;

public interface UswAccountCancelService extends IService<UswAccountCancel> {

	
	public PageListBasicResponse queryAccountCancelList(Map<String, Object> map);
}
