package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.admin.model.McsTasklogPageResponse;
import com.shanghaiwater.mcs.db.entity.McsTasklog;

public interface McsTasklogService extends IService<McsTasklog>{
	
	//查询task日志
	public McsTasklogPageResponse getTask(Map<String,Object> map);
	
	//获取task日志详情
	public McsTasklog detail(Map<String,Object> map);

}
