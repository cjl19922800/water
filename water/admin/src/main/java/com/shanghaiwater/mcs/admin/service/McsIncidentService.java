package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.db.entity.McsIncident;
import com.shanghaiwater.mcs.admin.model.IncidentListResponse;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MCS
 * 
 */
public interface McsIncidentService extends IService<McsIncident> {
	public IncidentListResponse queryList(Map map);
}
