package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.admin.common.ResponseModel;
import com.shanghaiwater.mcs.admin.model.FlowsheetDetailPageResponse;
import com.shanghaiwater.mcs.db.entity.AccFlowsheetDetail;

public interface FlowsheetDetailService extends IService<AccFlowsheetDetail>{

	//查询失败的流水单
	public FlowsheetDetailPageResponse findFailed(Map<String,Object> map);
	
	//获取流水单详情
	public AccFlowsheetDetail detail(Map<String,Object> map);
}
