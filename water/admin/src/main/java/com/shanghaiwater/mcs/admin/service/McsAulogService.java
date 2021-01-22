package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.admin.model.AulogPageResponse;
import com.shanghaiwater.mcs.admin.model.AulogResponse;
import com.shanghaiwater.mcs.db.entity.McsAulog;

public interface McsAulogService extends IService<McsAulog>{
	
	public AulogPageResponse getByPage(Map<String,Object> map);
	
	public McsAulog findById(Map<String,Object> map);

}
