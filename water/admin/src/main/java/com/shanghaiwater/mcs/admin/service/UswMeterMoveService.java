package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.db.entity.UswMeterMove;

public interface UswMeterMoveService extends IService<UswMeterMove> {

	public PageListBasicResponse queryMeterMoveList(Map<String, Object> map);
}
