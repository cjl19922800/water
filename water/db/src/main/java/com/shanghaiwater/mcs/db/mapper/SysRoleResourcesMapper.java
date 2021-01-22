package com.shanghaiwater.mcs.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.SysRoleResources;
import com.shanghaiwater.mcs.db.vo.RoleResourcesPowerVO;

public interface SysRoleResourcesMapper extends BaseMapper<SysRoleResources> {

	@Select(value="SELECT RESOURCES_ID FROM SYS_ROLE_RESOURCES_POWER WHERE ROLE_ID = #{roleId, jdbcType=VARCHAR}")
	public List<String> getResourcesIdByRoleId(@Param("roleId") String roleId);
	
	
	@Select(value="SELECT t.PARNAV_NAME,t.URL,t.NAV_NAME FROM ( SELECT " + 
			"	(SELECT NAME FROM SYS_NAVIGATION WHERE ID = nav.PARENT_ID) AS PARNAV_NAME, " + 
			"	( SELECT SORT_NO FROM SYS_NAVIGATION WHERE ID = nav.PARENT_ID ) AS SORT_NO, " + 
			"	res.URL," + 
			"	nav.NAME AS NAV_NAME " + 
			"FROM" + 
			"	SYS_ROLE_RESOURCES_POWER po, " + 
			"	SYS_RESOURCES res, " + 
			"	SYS_NAVIGATION nav " + 
			"WHERE " + 
			"	po.ROLE_ID = #{roleId, jdbcType=VARCHAR} " + 
			"AND " + 
			"	po.RESOURCES_ID = res.ID " + 
			"AND " + 
			"	nav.ID = res.NAVIGATION_ID " + 
			"AND " + 
			"	res.NAVIGATION_ID IS NOT NULL " + 
			"AND " + 
			"	res.EMPLOY = 1 " + 
			"AND " + 
			"	nav.EMPLOY = 1 ) t ORDER BY t.SORT_NO ")
	public List<RoleResourcesPowerVO> selectHomeResourcesByRoleId(@Param("roleId") String roleId);
	
	
}
