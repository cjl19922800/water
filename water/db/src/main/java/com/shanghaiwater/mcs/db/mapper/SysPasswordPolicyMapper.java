package com.shanghaiwater.mcs.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shanghaiwater.mcs.db.entity.SysPasswordPolicy;



public interface SysPasswordPolicyMapper extends BaseMapper<SysPasswordPolicy>{
	String savePasswordPolicy(String passwordPolicyId,String len,String max,String rep);
}
