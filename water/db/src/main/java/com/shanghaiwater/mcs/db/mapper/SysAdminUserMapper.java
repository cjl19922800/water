package com.shanghaiwater.mcs.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.SysAdminUser;

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
