package com.shanghaiwater.mcs.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.admin.entity.SysAdminUser;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author MCS
 * 
 */
public interface SysAdminUserMapper extends BaseMapper<SysAdminUser> {
	String selectAdminUserRoleId(String adminId);
}
