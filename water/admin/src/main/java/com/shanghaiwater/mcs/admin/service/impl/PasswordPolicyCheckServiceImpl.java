package com.shanghaiwater.mcs.admin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.shanghaiwater.mcs.admin.service.PasswordPolicyCheckService;
import com.shanghaiwater.mcs.admin.util.CommonUtil;
import com.shanghaiwater.mcs.common.model.R;
import com.shanghaiwater.mcs.db.entity.SysAdminLoginDetails;
import com.shanghaiwater.mcs.db.entity.SysAdminUser;
import com.shanghaiwater.mcs.db.entity.SysPasswordPolicy;
import com.shanghaiwater.mcs.db.mapper.SysAdminLoginDetailsMapper;
import com.shanghaiwater.mcs.db.mapper.SysAdminUserMapper;
import com.shanghaiwater.mcs.db.mapper.SysPasswordPolicyMapper;

@Service
public class PasswordPolicyCheckServiceImpl implements PasswordPolicyCheckService{

	@Autowired
	private SysPasswordPolicyMapper sysPasswordPolicyMapper;
	
	@Autowired
	private SysAdminUserMapper sysAdminUserMapper;
	
	@Autowired
	private SysAdminLoginDetailsMapper sysAdminLoginDetailsMapper;
	
	
	
	public static boolean checkExpire() {
		return false;
	}
	
	
	@Override
	public R loginCheck(boolean isLogin,String userId) {
		QueryWrapper<SysAdminUser> qw = new QueryWrapper<SysAdminUser>();
		qw.eq("USER_ID", userId);
		List<SysAdminUser> userList = sysAdminUserMapper.selectList(qw);
		if(userList.size()>0 && null != userList) {
			SysAdminUser user = userList.get(0);
			Date initDate = user.getCdate();
			SysAdminLoginDetails loginDatil = new SysAdminLoginDetails();
			boolean addFlag = true;
			List<SysAdminLoginDetails> details = sysAdminLoginDetailsMapper.selectList(new QueryWrapper<SysAdminLoginDetails>().eq("USER_ID", userId));
			if(details.size()>0 && null != details) {
				loginDatil = details.get(0);
				addFlag = false;
			}
			if(isLogin) {
				if(addFlag) {
					loginDatil.setId(UUID.randomUUID().toString());
					loginDatil.setUserId(userId);
					loginDatil.setLoginTimes(1);
					loginDatil.setLatelyTime(new Date());
					
				}else {
					
				}
				
				
			}else {
				
			}
			
			
		}else {
			return R.error("DATA.IS.NOTEXITS","用户不存在");
		}
		return R.ok();
	}
	
	
	
	@Override
	public Map<String,Object> checkPolicy(String password) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<SysPasswordPolicy> policies = sysPasswordPolicyMapper.selectList(new QueryWrapper<SysPasswordPolicy>().orderByDesc("cdate"));
		if(policies.size()>0 && null != policies) {
			SysPasswordPolicy policy = policies.get(0);
			//数字校验
			if(StringUtils.isNotEmpty(policy.getHasNumber())) {
				if(policy.getHasNumber().equals("1")) {
					if(!CommonUtil.HasDigit(password)) {
						map.put("success", false);
						map.put("msg", "密码不包含数字");
						return map;
					}
				}
			}
			//小写字母校验
			if(StringUtils.isNotEmpty(policy.getHasLowerChar())) {
				if(policy.getHasLowerChar().equals("1")) {
					if(!CommonUtil.hasRegRule(password, CommonUtil.CONTAIN_LETTER_REGEX)) {
						map.put("success", false);
						map.put("msg", "密码不包含小写字母");
						return map;
					}
					
				}
			}
			//大写字母校验
			if(StringUtils.isNotEmpty(policy.getHasUpperChar())) {
				if(policy.getHasUpperChar().equals("1")) {
					if(!CommonUtil.hasRegRule(password, CommonUtil.CONTAIN_LETTER_UUP_REGEX)) {
						map.put("success", false);
						map.put("msg", "密码不包含大写字母");
						return map;
					}
					
				}
			}
			//特殊字符校验
			if(StringUtils.isNotEmpty(policy.getHasSpecialChar())) {
				if(policy.getHasSpecialChar().equals("1")) {
					if(!CommonUtil.stringHasSpecial(password)) {
						map.put("success", false);
						map.put("msg", "密码不包含特殊字符");
						return map;
					}
				}
			}
			//密码位数校验
			if(password.length()>policy.getMaxLength() || password.length()<policy.getMinLength()) {
				map.put("success", false);
				map.put("msg", "密码位数在"+policy.getMinLength()+"~"+policy.getMaxLength()+"之间");
				return map;
			}
			map.put("success", true);
		}else {
			map.put("success", true);
			map.put("msg", "无密码策略");
			return map;
		}
		return map;
	}
	
	
}
