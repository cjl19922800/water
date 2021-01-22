package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.admin.model.PageListBasicResponse;
import com.shanghaiwater.mcs.db.entity.UswUpdateInf;

public interface UswCustomerChangeService extends IService<UswUpdateInf> {

	public PageListBasicResponse queryCusChangeList(Map<String, Object> map);
}
