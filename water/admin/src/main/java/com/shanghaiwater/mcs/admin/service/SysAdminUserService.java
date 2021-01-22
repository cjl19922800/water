package com.shanghaiwater.mcs.admin.service;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanghaiwater.mcs.db.entity.SysAdminUser;
import com.shanghaiwater.mcs.admin.common.ResponseSuccessModel;
import com.shanghaiwater.mcs.admin.model.AdminUserListResponse;
import com.shanghaiwater.mcs.admin.model.LoginRequest;
import com.shanghaiwater.mcs.admin.model.PasswordRequest;
import com.shanghaiwater.mcs.common.model.R;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MCS
 * 
 */
public interface SysAdminUserService extends IService<SysAdminUser> {
	public AdminUserListResponse queryList(Map<String, Object> map);

	public SysAdminUser checkLogin(LoginRequest loginRequest) throws Exception;
	
	public R updatePassword(PasswordRequest passwordRequest) throws NoSuchAlgorithmException;

}
