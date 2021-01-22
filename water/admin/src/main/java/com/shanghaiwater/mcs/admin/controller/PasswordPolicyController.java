package com.shanghaiwater.mcs.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanghaiwater.mcs.admin.annotation.PermissionIntercept;
import com.shanghaiwater.mcs.admin.common.AdminLoginInfo;
import com.shanghaiwater.mcs.admin.model.PasswordPolicyRequest;
import com.shanghaiwater.mcs.admin.service.PasswordPolicyService;
import com.shanghaiwater.mcs.common.model.R;
import com.shanghaiwater.mcs.db.entity.SysPasswordPolicy;
import com.shanghaiwater.mcs.db.mapper.SysPasswordPolicyMapper;

@RestController
@RequestMapping(value = "/password")
@PermissionIntercept
public class PasswordPolicyController {
	
	@Autowired
	PasswordPolicyService passwordPolicyService;
	
	@Autowired
	private SysPasswordPolicyMapper sysPasswordPolicyMapper;
	

	@RequestMapping(value = "/listview", method = RequestMethod.GET)
	public ModelAndView detail(Model model) {
		List<SysPasswordPolicy> policies = sysPasswordPolicyMapper.selectList(new QueryWrapper<SysPasswordPolicy>().orderByDesc("cdate"));
		if(policies.size()>0 && null != policies) {
			model.addAttribute("policy", policies.get(0));
		}else {
			model.addAttribute("policy", new SysPasswordPolicy());
		}
		return new ModelAndView("views/right/adminuser/passwordpolicy", "model", model);
	}

	// 不用在service层判断，只需要在controller层判断就好@validated
	@PostMapping("/save")
	public R save(@RequestBody @Valid PasswordPolicyRequest passwordPolicyRequest ,HttpServletRequest request) {
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		passwordPolicyRequest.setId(adminLoginInfo.getId());
		return passwordPolicyService.savePasswordPolicy(passwordPolicyRequest);
//		return R.ok("修改成功");
		
	}

}
