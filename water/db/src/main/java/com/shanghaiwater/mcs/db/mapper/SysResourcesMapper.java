package com.shanghaiwater.mcs.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.SysResources;

public interface SysResourcesMapper extends BaseMapper<SysResources> {

	@Select(value = { "SELECT DISTINCT(MODULAR) FROM SYS_RESOURCES" })
	public List<String> getModulars();
	
	
	@Select(value="SELECT URL FROM SYS_RESOURCES WHERE ID IN (SELECT RESOURCES_ID FROM SYS_ROLE_RESOURCES_POWER WHERE ROLE_ID = #{roleId, jdbcType=VARCHAR})")
	public List<String> getUrlByRoleId(@Param("roleId") String roleId);
	
}
