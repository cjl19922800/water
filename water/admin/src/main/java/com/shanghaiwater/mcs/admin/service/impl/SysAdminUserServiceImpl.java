package com.shanghaiwater.mcs.admin.service.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.admin.enums.ModuleEnums;
import com.shanghaiwater.mcs.admin.enums.OperationEnums;
import com.shanghaiwater.mcs.admin.model.AdminUserListResponse;
import com.shanghaiwater.mcs.admin.model.LoginRequest;
import com.shanghaiwater.mcs.admin.model.PasswordRequest;
import com.shanghaiwater.mcs.admin.service.PasswordPolicyCheckService;
import com.shanghaiwater.mcs.admin.service.SysAdminLogService;
import com.shanghaiwater.mcs.admin.service.SysAdminUserService;
import com.shanghaiwater.mcs.admin.util.Hash256;
import com.shanghaiwater.mcs.common.errcode.McsErrCode;
import com.shanghaiwater.mcs.common.exception.MCSException;
import com.shanghaiwater.mcs.common.model.R;
import com.shanghaiwater.mcs.common.util.MD5Util;
import com.shanghaiwater.mcs.db.entity.SysAdminLoginDetails;
import com.shanghaiwater.mcs.db.entity.SysAdminUser;
import com.shanghaiwater.mcs.db.entity.SysPasswordPolicy;
import com.shanghaiwater.mcs.db.mapper.SysAdminLoginDetailsMapper;
import com.shanghaiwater.mcs.db.mapper.SysAdminUserMapper;
import com.shanghaiwater.mcs.db.mapper.SysPasswordPolicyMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MCS
 * 
 */
@Slf4j
@Service
public class SysAdminUserServiceImpl extends ServiceImpl<SysAdminUserMapper, SysAdminUser>
		implements SysAdminUserService {
	
	@Autowired
	private SysPasswordPolicyMapper sysPasswordPolicyMapper;
	
	@Autowired
	private PasswordPolicyCheckService passwordPolicyCheckService;
	

	// 这里为什么不用申明，如果不申明，那sysAdminUserMapper的对象是什么？
	@Autowired
	private SysAdminUserMapper sysAdminUserMapper;
	
	@Autowired
	private SysAdminLogService sysAdminLogService;

	@Override
	public AdminUserListResponse queryList(Map<String, Object> map) {
		AdminUserListResponse adminUserListResponse = new AdminUserListResponse();
		QueryWrapper<SysAdminUser> queryWrapper = new QueryWrapper<SysAdminUser>();
		String userId = (String) map.get("userId");
		String userName = (String) map.get("userName");
		if (!StringUtils.isEmpty(userId)) {
			queryWrapper.like("user_id", userId);
		}
		if (!StringUtils.isEmpty(userName)) {
			queryWrapper.like("user_name", userName);
		}
		List<SysAdminUser> list = sysAdminUserMapper.selectList(queryWrapper);
		adminUserListResponse.setCode(0);
		adminUserListResponse.setTotal(list.size());
		adminUserListResponse.setCount(String.valueOf(list.size()));
		adminUserListResponse.setSuccess(true);
		adminUserListResponse.setData(list);
		return adminUserListResponse;
	}
	
	
	
	
	

	@Override
	public SysAdminUser checkLogin(LoginRequest loginRequest) throws Exception {
//		SysAdminLoginDetails details = new SysAdminLoginDetails();
		QueryWrapper<SysAdminUser> queryWrapper = new QueryWrapper<SysAdminUser>();
		String userId = loginRequest.getUserId();
		String userPassword = loginRequest.getUserPassword();
//		MessageDigest md = MessageDigest.getInstance("MD5");
//		md.update(userPassword.getBytes());
//		String newPassword = new BigInteger(1, md.digest()).toString(16);
		String newPassword = Hash256.getSHA256Str(userPassword);
		queryWrapper.eq("user_id", userId);
		queryWrapper.eq("user_password", newPassword);
		List<SysAdminUser> list = sysAdminUserMapper.selectList(queryWrapper);
		if (list != null && list.size() > 0) {
			
			
			return list.get(0);
		} else {
			
			
			
			
			return null;
		}
	}

	@Override
	public R updatePassword(PasswordRequest passwordRequest) throws NoSuchAlgorithmException {
		QueryWrapper<SysAdminUser> sysAdminUserWrapper = new QueryWrapper<SysAdminUser>();
		// 参数校验,直接利用注解校验

		// 业务校验,验证输入密码和数据库中的密码是否一致,先md5加密,后对比
		String oldPassword = "";
//		MessageDigest md = MessageDigest.getInstance("MD5");
//		md.update(passwordRequest.getOldPassword().getBytes());
//		oldPassword = new BigInteger(1, md.digest()).toString(16);
		
		oldPassword = Hash256.getSHA256Str(passwordRequest.getOldPassword());
		
		
		SysAdminUser sysAdminUser = sysAdminUserMapper.selectById(passwordRequest.getId());
		String checkPassword = sysAdminUser.getUserPassword();
		if (!(oldPassword.equals(checkPassword))) {
			throw new MCSException(McsErrCode.PASSWORD_ERROR, "密码错误，重新输入！");
		}
		// 读取密码策略表
		String newPassword = passwordRequest.getNewPassword();
		Map<String,Object> map = passwordPolicyCheckService.checkPolicy(newPassword);
		if(!(boolean) map.get("success")) {
			return R.error("CHECK.POLICY.ERR", String.valueOf(map.get("msg")));
		}
		System.out.println(newPassword);
//		MessageDigest md2 = MessageDigest.getInstance("MD5");
//		md2.update(newPassword.getBytes());
//		newPassword = new BigInteger(1, md2.digest()).toString(16);
		newPassword = Hash256.getSHA256Str(newPassword);
		System.out.println("加密后"+newPassword);
		// 修改口令
		try {
			sysAdminUser.setUserPassword(newPassword);
		} catch (MCSException e) {
			throw new MCSException(McsErrCode.MD5_NOT_EXIST, "MD5加密失败");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		sysAdminUserWrapper.eq("ID", passwordRequest.getId());
		this.update(sysAdminUser, sysAdminUserWrapper);
		
		StringBuffer sb = new StringBuffer();
		sb.append("用户");
		sb.append(sysAdminUser.getUserName());
		sb.append("于");
		sb.append(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
		sb.append("修改密码");
		sysAdminLogService.saveAdminLog(OperationEnums.UPDATE, ModuleEnums.USER, sb.toString(), sysAdminUser.getId());
		return R.ok();
	}

	// 用于判断是否符合密码策略,引用的函数,成功返回valiperiod,retrynumber,失败返回失败信息
	public String checkPasswordPolicy(String newPassword) {
		QueryWrapper<SysPasswordPolicy> passwordPolicyWrapper = new QueryWrapper<SysPasswordPolicy>();
		passwordPolicyWrapper.eq("OWNER", "ROOT");
		passwordPolicyWrapper.orderByDesc("CDATE");
		List<SysPasswordPolicy> sysPasswordPolicyList = sysPasswordPolicyMapper.selectList(passwordPolicyWrapper);
		System.out.println(sysPasswordPolicyList.get(0));
		// 这样写对不对？
		log.info(null, sysPasswordPolicyList.get(0));

		String hasNumber = sysPasswordPolicyList.get(0).getHasNumber();
		String hasLowerChar = sysPasswordPolicyList.get(0).getHasLowerChar();
		String hasUpperChar = sysPasswordPolicyList.get(0).getHasUpperChar();
		String hasSpecialChar = sysPasswordPolicyList.get(0).getHasSpecialChar();
		Integer minLength = sysPasswordPolicyList.get(0).getMinLength();
		Integer maxLength = sysPasswordPolicyList.get(0).getMaxLength();
		// 这两个是需要返回的
		Integer validPeriod = sysPasswordPolicyList.get(0).getValidPeriod();
		Integer retryNumber = sysPasswordPolicyList.get(0).getRetryNumber();

//		if (hasNumber.equals("1")&&!PasswordPolicyUtil.HasDigit(newPassword)){
//			throw new MCSException(McsErrCode.PASSWORDPOLICY_NOT_MET,"密码中必须包含数字！");
//		}
//		if ((hasLowerChar.equals("1"))&&!PasswordPolicyUtil.hasLowerChar(newPassword)){
//			throw new MCSException(McsErrCode.PASSWORDPOLICY_NOT_MET,"密码中必须包含小写字母！");
//		}
//		if (hasUpperChar.equals("1")&&!PasswordPolicyUtil.hasUpperChar(newPassword)){
//			throw new MCSException(McsErrCode.PASSWORDPOLICY_NOT_MET,"密码中必须包含大写字母！");
//		}
//		if (hasSpecialChar.equals("1")&&!PasswordPolicyUtil.hasSpecialChar(newPassword)){
//			throw new MCSException(McsErrCode.PASSWORDPOLICY_NOT_MET,"密码中必须包含特殊字母！");
//		}
		if (!(newPassword.length() >= minLength && newPassword.length() <= maxLength)) {
			throw new MCSException(McsErrCode.PASSWORDPOLICY_NOT_MET, "检查密码长度");
		}
		return "success" + "," + validPeriod.toString() + "," + retryNumber.toString();
	}

}
