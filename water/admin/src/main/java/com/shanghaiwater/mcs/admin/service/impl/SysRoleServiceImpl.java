package com.shanghaiwater.mcs.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.db.entity.SysRole;
import com.shanghaiwater.mcs.db.mapper.SysRoleMapper;
import com.shanghaiwater.mcs.admin.model.RoleListResponse;
import com.shanghaiwater.mcs.admin.service.SysRoleService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MCS
 * 
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	public RoleListResponse queryList(Map<String, Object> map) {
		RoleListResponse roleListResponse = new RoleListResponse();

		QueryWrapper<SysRole> queryWrapper = new QueryWrapper<SysRole>();

		String roleName = (String) map.get("roleName");

		if (!StringUtils.isEmpty(roleName)) {
			queryWrapper.like("role_name", roleName);
		}
		List<SysRole> list = sysRoleMapper.selectList(queryWrapper);

		roleListResponse.setCode(0);
		roleListResponse.setTotal(list.size());
		roleListResponse.setCount(String.valueOf(list.size()));
		roleListResponse.setSuccess(true);
		roleListResponse.setData(list);

		return roleListResponse;
	}
	
	
	
	
	

}
