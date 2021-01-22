package com.shanghaiwater.mcs.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.SysRoleCompany;

public interface SysRoleCompanyMapper extends BaseMapper<SysRoleCompany> {

	@Select(value="SELECT COMPANY_CODE FROM SYS_ROLE_COMPANY_POWER WHERE ROLE_ID = #{roleId, jdbcType=VARCHAR}")
	public List<String> getCompanyCodeByRoleId(@Param("roleId") String roleId);
	
}
