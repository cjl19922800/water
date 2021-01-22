package com.shanghaiwater.mcs.admin.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.shanghaiwater.mcs.admin.annotation.PermissionIntercept;
import com.shanghaiwater.mcs.admin.common.AdminLoginInfo;
import com.shanghaiwater.mcs.admin.common.ResponseSuccessModel;
import com.shanghaiwater.mcs.admin.model.LoginRequest;
import com.shanghaiwater.mcs.admin.service.SysAdminUserRoleRelService;
import com.shanghaiwater.mcs.admin.service.SysAdminUserService;
import com.shanghaiwater.mcs.admin.service.SysRoleService;
import com.shanghaiwater.mcs.common.util.GsonUtil;
import com.shanghaiwater.mcs.db.entity.SysAdminUser;
import com.shanghaiwater.mcs.db.mapper.SysResourcesMapper;
import com.shanghaiwater.mcs.db.mapper.SysRoleCompanyMapper;
import com.shanghaiwater.mcs.db.mapper.SysRoleMapper;

@RestController
public class LoginController {

	private Gson gson = GsonUtil.getInstance();

	@Autowired
	private SysAdminUserService sysAdminUserService;

	@Autowired
	private SysAdminUserRoleRelService sysAdminUserRoleRelService;

	@Autowired
	private SysRoleService sysRoleService;
	
	private SysRoleMapper ysRoleMapper;

	@Autowired
	private SysRoleCompanyMapper sysRoleCompanyMapper;

	@Autowired
	private SysResourcesMapper sysResourcesMapper;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(Model model) {
		return new ModelAndView("views/user/login", "model", model);
	}

	@RequestMapping(value = "/dologin")
	public ResponseSuccessModel listView(HttpServletRequest request, @RequestBody String payload) throws Exception {
		LoginRequest loginRequest = gson.fromJson(payload, LoginRequest.class);
		SysAdminUser sysAdminUser = sysAdminUserService.checkLogin(loginRequest);
		boolean ret = false;
		if (sysAdminUser != null) {
			ret = true;
		}
		ResponseSuccessModel responseSuccessModel = new ResponseSuccessModel();
		// session
		if (ret) {
			AdminLoginInfo adminLoginInfo = new AdminLoginInfo();
				sysAdminUser.setLastPasswordLogin(new Date());
				sysAdminUserService.updateById(sysAdminUser);
				
				adminLoginInfo.setId(sysAdminUser.getId());
				adminLoginInfo.setUserId(sysAdminUser.getUserId());
				adminLoginInfo.setUserName(sysAdminUser.getUserName());
				adminLoginInfo.setDisplayName(sysAdminUser.getDisplayName());
				adminLoginInfo.setEmail(sysAdminUser.getEmail());
				adminLoginInfo.setCellPhone(sysAdminUser.getCellPhone());
				adminLoginInfo.setLastPasswordLogin(sysAdminUser.getLastPasswordLogin());
				adminLoginInfo.setDescription(sysAdminUser.getDescription());
				adminLoginInfo.setRoleId(sysAdminUser.getRoleId());
				adminLoginInfo.setStatus(sysAdminUser.getStatus());
				adminLoginInfo.setAuthorities(sysResourcesMapper.getUrlByRoleId(sysAdminUser.getRoleId()));
				adminLoginInfo.setCompanyAuthorities(sysRoleCompanyMapper.getCompanyCodeByRoleId(sysAdminUser.getRoleId()));
				request.getSession().setAttribute("adminLoginInfo", adminLoginInfo);
				responseSuccessModel.setStatus(sysAdminUser.getStatus());

		}

		
		responseSuccessModel.setRequestId(UUID.randomUUID().toString());
		responseSuccessModel.setCode(0);
		responseSuccessModel.setSuccess(ret);
		
		return responseSuccessModel;
	}
	
	@RequestMapping(value = "/dologOut", method = RequestMethod.GET)
	public ModelAndView dologOutModel(HttpServletRequest request, Model model, HttpServletResponse response) {
		
		
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
		
		
		//清除cookie
		request.getSession().invalidate();
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				cookie.setValue(null);
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
		return new ModelAndView("views/user/login", "model", model);
	}
	
	
}
