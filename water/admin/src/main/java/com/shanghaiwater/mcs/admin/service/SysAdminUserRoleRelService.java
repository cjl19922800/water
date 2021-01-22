package com.shanghaiwater.mcs.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.db.entity.SysAdminUserRoleRel;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MCS
 * 
 */
public interface SysAdminUserRoleRelService extends IService<SysAdminUserRoleRel> {
	public SysAdminUserRoleRel queryAdminRole(String adminId);

	public void updateAdminRoleRel(String adminId, String roleId);

}
