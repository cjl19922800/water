package com.shanghaiwater.mcs.admin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.db.entity.SysAdminUserRoleRel;
import com.shanghaiwater.mcs.db.mapper.SysAdminUserRoleRelMapper;
import com.shanghaiwater.mcs.admin.service.SysAdminUserRoleRelService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MCS
 * 
 */
@Service
public class SysAdminUserRoleRelServiceImpl extends ServiceImpl<SysAdminUserRoleRelMapper, SysAdminUserRoleRel>
		implements SysAdminUserRoleRelService {

	@Autowired
	private SysAdminUserRoleRelMapper sysAdminUserRoleRelMapper;

	@Override
	public SysAdminUserRoleRel queryAdminRole(String adminId) {
		QueryWrapper<SysAdminUserRoleRel> queryWrapper = new QueryWrapper<SysAdminUserRoleRel>();

		queryWrapper.eq("admin_user_id", adminId);

		queryWrapper.orderByDesc("udate");

		List<SysAdminUserRoleRel> list = sysAdminUserRoleRelMapper.selectList(queryWrapper);

		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	@Override
	public void updateAdminRoleRel(String adminId, String roleId) {
		SysAdminUserRoleRel sysAdminUserRoleRel = new SysAdminUserRoleRel();

		sysAdminUserRoleRel.setAdminUserRoleRelId(UUID.randomUUID().toString());
		sysAdminUserRoleRel.setAdminUserId(adminId);
		sysAdminUserRoleRel.setRoleId(roleId);
		sysAdminUserRoleRel.setRecordId(UUID.randomUUID().toString());
		sysAdminUserRoleRel.setCreator("system");
		sysAdminUserRoleRel.setCdate(new Date());
		sysAdminUserRoleRel.setUpdator("system");
		sysAdminUserRoleRel.setUdate(new Date());
		sysAdminUserRoleRel.setRecordVersion(UUID.randomUUID().toString());

		sysAdminUserRoleRelMapper.insert(sysAdminUserRoleRel);
	}

}
