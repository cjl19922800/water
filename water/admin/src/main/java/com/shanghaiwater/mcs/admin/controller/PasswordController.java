package com.shanghaiwater.mcs.admin.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

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
import com.shanghaiwater.mcs.admin.model.PasswordRequest;
import com.shanghaiwater.mcs.admin.service.SysAdminLogService;
import com.shanghaiwater.mcs.admin.service.SysAdminUserService;
import com.shanghaiwater.mcs.common.errcode.McsErrCode;
import com.shanghaiwater.mcs.common.exception.MCSException;
import com.shanghaiwater.mcs.common.model.R;
import com.shanghaiwater.mcs.db.entity.SysAdminUser;
import com.shanghaiwater.mcs.db.mapper.SysAdminUserMapper;

/**
 * 页面跳转
 * 
 * @author Laipu
 *
 */
@RestController
@RequestMapping("/set/password")
@PermissionIntercept
public class PasswordController {
	@Autowired
	private SysAdminUserService sysAdminUserService;
	
	@Autowired
	private SysAdminUserMapper sysAdminUserMapper;

	
	@PostMapping(value="/update")
	public R update(HttpServletRequest request, @RequestBody PasswordRequest passwordRequest) throws NoSuchAlgorithmException {
		try {
			AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
			if (adminLoginInfo.getStatus().equals("0")) {
				
				QueryWrapper<SysAdminUser> queryWrapper = new QueryWrapper<SysAdminUser>();
				queryWrapper.eq("id", adminLoginInfo.getId());
				SysAdminUser sysAdminUser = sysAdminUserMapper.selectOne(queryWrapper);
				System.out.println(sysAdminUser);
				sysAdminUser.setStatus("1");
				sysAdminUserMapper.updateById(sysAdminUser);
			}
			passwordRequest.setId(adminLoginInfo.getId());
			System.out.println("修改密码");
			return sysAdminUserService.updatePassword(passwordRequest);
		} catch(MCSException e){
			return R.error(e.getErrorCode(), e.getMessage());
		}
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView listView(Model model) {
		return new ModelAndView("views/set/user/password", "ToolModel", model);
	}

}
