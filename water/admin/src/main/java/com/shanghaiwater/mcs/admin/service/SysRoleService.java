package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.db.entity.SysRole;
import com.shanghaiwater.mcs.admin.model.RoleListResponse;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MCS
 * 
 */
public interface SysRoleService extends IService<SysRole> {
	public RoleListResponse queryList(Map<String, Object> map);
	
	
	
	
}
