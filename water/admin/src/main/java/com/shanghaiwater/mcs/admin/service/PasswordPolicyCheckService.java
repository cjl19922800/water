package com.shanghaiwater.mcs.admin.service;

import java.util.Map;

import com.shanghaiwater.mcs.common.model.R;

public interface PasswordPolicyCheckService {

	/**
	 * 密码策略检测
	 * @param password
	 * @return
	 */
	public Map<String, Object> checkPolicy(String password);

	/**
	 * 登录策略
	 * @param isLogin
	 * @param userId
	 * @return
	 */
	public R loginCheck(boolean isLogin, String userId);

	
	
	
}
