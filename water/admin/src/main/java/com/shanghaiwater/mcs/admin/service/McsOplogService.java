package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.admin.model.LogListResponse;
import com.shanghaiwater.mcs.admin.model.LogPageResponse;
import com.shanghaiwater.mcs.db.entity.McsOplog;


public interface McsOplogService extends IService<McsOplog>{
	
	//获取日志详情
	public McsOplog detail(Map<String, Object> map);
	
	//根据事件id获取事件类型
	public LogListResponse getEventTypeByEventId(Map<String,Object> map);
	
	//分页查询
	public LogPageResponse findByPage(Map<String,Object> map);
	
	
	 
}
