package com.shanghaiwater.mcs.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.admin.model.PasswordPolicyRequest;
import com.shanghaiwater.mcs.common.model.R;
import com.shanghaiwater.mcs.db.entity.SysPasswordPolicy;

public interface PasswordPolicyService extends IService<SysPasswordPolicy> {
	public R savePasswordPolicy(PasswordPolicyRequest passwordPolicyRequest);
	
	public String selectPasswordPolicyByUserId(String userid);
}
