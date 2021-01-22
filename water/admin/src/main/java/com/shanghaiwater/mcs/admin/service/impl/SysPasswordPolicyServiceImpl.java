package com.shanghaiwater.mcs.admin.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanghaiwater.mcs.admin.model.PasswordPolicyRequest;
import com.shanghaiwater.mcs.admin.service.PasswordPolicyService;
import com.shanghaiwater.mcs.common.model.R;
import com.shanghaiwater.mcs.db.entity.SysAdminUser;
import com.shanghaiwater.mcs.db.entity.SysPasswordPolicy;
import com.shanghaiwater.mcs.db.mapper.SysAdminUserMapper;
import com.shanghaiwater.mcs.db.mapper.SysPasswordPolicyMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Validated
public class SysPasswordPolicyServiceImpl extends ServiceImpl<SysPasswordPolicyMapper, SysPasswordPolicy>
		implements PasswordPolicyService {
	@Autowired
	private SysPasswordPolicyMapper sysPasswordPolicyMapper;

	@Autowired
	private SysAdminUserMapper sysAdminUserMapper;

	@Override
	public R savePasswordPolicy(PasswordPolicyRequest passwordPolicyRequest) {
		log.info("test");
		boolean flag = false;
		SysPasswordPolicy sysPasswordPolicy = new SysPasswordPolicy();
		QueryWrapper<SysPasswordPolicy> queryWrapper = new QueryWrapper<SysPasswordPolicy>();

		String hasNumber = passwordPolicyRequest.getHasNum();
		String hasLowerChar = passwordPolicyRequest.getHasLowerChar();
		String hasUpperChar = passwordPolicyRequest.getHasUpperChar();
		String hasSpecialChar = passwordPolicyRequest.getHasSpecialChar();
		Integer minLength = passwordPolicyRequest.getMinLength();
		Integer validPeriod = passwordPolicyRequest.getValidPeriod();
		Integer retryNumber = passwordPolicyRequest.getRetrynumber();

		// 参数校验放在了controller层
		sysPasswordPolicy.setHasNumber(hasNumber);
		sysPasswordPolicy.setHasLowerChar(hasLowerChar);
		sysPasswordPolicy.setHasUpperChar(hasUpperChar);
		sysPasswordPolicy.setHasSpecialChar(hasSpecialChar);
		sysPasswordPolicy.setMinLength(minLength);
		sysPasswordPolicy.setRetryNumber(retryNumber);
		sysPasswordPolicy.setValidPeriod(validPeriod);
		// 固定值
		sysPasswordPolicy.setMaxLength(32);
		sysPasswordPolicy.setLockTime(60);
		// 保存或更新
		// 根据登录的id在数据库里面查他的userid
		SysAdminUser sysAdminUser = new SysAdminUser();
		// 这个id是登录者的id
		sysAdminUser = sysAdminUserMapper.selectById(passwordPolicyRequest.getId());
		String userId = sysAdminUser.getUserId();
		queryWrapper.eq("creator", userId);
		int cout = sysPasswordPolicyMapper.selectCount(queryWrapper);
//		判断这个人是否有历史数据		
		if (cout > 0) {// 有历史数据
			sysPasswordPolicy.setUpdator(userId);
			sysPasswordPolicy.setUdate(new Date());
			flag = this.update(sysPasswordPolicy, queryWrapper);
		} else {// 无历史数据
			sysPasswordPolicy.setPasswdPolicyId(UUID.randomUUID().toString());
			sysPasswordPolicy.setRecordId(UUID.randomUUID().toString());
			sysPasswordPolicy.setCdate(new Date());
			sysPasswordPolicy.setCreator(userId);
			sysPasswordPolicy.setUpdator(userId);
			sysPasswordPolicy.setUdate(new Date());
			sysPasswordPolicy.setRecordVersion(UUID.randomUUID().toString());
			flag = this.save(sysPasswordPolicy);
		}
		// 返回数据以及检测
		if (flag) {
			return R.ok("修改成功");
		}else {
			return R.error("500", "修改失败");
		}
//		return null;
	}

	@Override
	public String selectPasswordPolicyByUserId(String userid) {
		QueryWrapper<SysPasswordPolicy> queryWrapper = new QueryWrapper<SysPasswordPolicy>();
		queryWrapper.eq("owner", "ROOT");
		return null;
	}
}