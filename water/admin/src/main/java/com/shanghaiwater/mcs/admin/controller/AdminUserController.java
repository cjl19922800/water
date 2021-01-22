package com.shanghaiwater.mcs.admin.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shanghaiwater.mcs.admin.annotation.PermissionIntercept;
import com.shanghaiwater.mcs.admin.common.AdminLoginInfo;
import com.shanghaiwater.mcs.admin.common.ResponseSuccessModel;
import com.shanghaiwater.mcs.admin.enums.ModuleEnums;
import com.shanghaiwater.mcs.admin.enums.OperationEnums;
import com.shanghaiwater.mcs.admin.model.AdminUserListResponse;
import com.shanghaiwater.mcs.admin.service.PasswordPolicyCheckService;
import com.shanghaiwater.mcs.admin.service.SysAdminLogService;
import com.shanghaiwater.mcs.admin.service.SysAdminUserService;
import com.shanghaiwater.mcs.admin.service.SysRoleService;
import com.shanghaiwater.mcs.admin.util.Hash256;
import com.shanghaiwater.mcs.common.model.R;
import com.shanghaiwater.mcs.common.util.GsonUtil;
import com.shanghaiwater.mcs.db.entity.SysAdminUser;
import com.shanghaiwater.mcs.db.entity.SysRole;
import com.shanghaiwater.mcs.db.mapper.SysAdminUserMapper;

/**
 * 页面跳转
 * 
 * @author Laipu
 *
 */
@RestController
@RequestMapping("/right/adminuser")
@PermissionIntercept
public class AdminUserController {

	private Gson gson = GsonUtil.getInstance();

	@Autowired
	private SysAdminUserService sysAdminUserService;

	@Autowired
	private SysAdminUserMapper sysAdminUserMapper;

	@Autowired
	private SysAdminLogService sysAdminLogService;

	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private PasswordPolicyCheckService passwordPolicyCheckService;
	
	
	@RequestMapping("/delete")
	public ResponseSuccessModel delete(@RequestParam(value = "id", required = false) String id) {
		sysAdminUserMapper.deleteById(id);
		ResponseSuccessModel responseSuccessModel = new ResponseSuccessModel();
		responseSuccessModel.setRequestId(UUID.randomUUID().toString());
		responseSuccessModel.setCode(0);
		responseSuccessModel.setSuccess(true);
		return responseSuccessModel;
	}
	

	@RequestMapping(value = "/listview", method = RequestMethod.GET)
	public ModelAndView listView(Model model) {
		return new ModelAndView("views/right/adminuser/adminuser", "model", model);
	}

	@RequestMapping("/detail")
	public ModelAndView detailPage(@RequestParam(value = "id", required = false) String id, Model model) {
		SysAdminUser sysAdminUser = sysAdminUserService.getById(id);
		model.addAttribute("adminuser", sysAdminUser);
		List<SysRole> sysRoleList = sysRoleService.list();
		model.addAttribute("roleList", sysRoleList);
		return new ModelAndView("views/right/adminuser/adminuserform", "model", model);
	}

	@RequestMapping("/new")
	public ModelAndView detailPage(Model model) {
		List<SysRole> sysRoleList = sysRoleService.list();
		model.addAttribute("roleList", sysRoleList);
		return new ModelAndView("views/right/adminuser/adminusernewform", "model", model);
	}

	@RequestMapping("/create")
	public ResponseSuccessModel create(HttpServletRequest request, @RequestBody String payload) throws NoSuchAlgorithmException {
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		SysAdminUser sysAdminUser = gson.fromJson(payload, SysAdminUser.class);
		ResponseSuccessModel responseSuccessModel = new ResponseSuccessModel();
		if(sysAdminUserMapper.selectList(new QueryWrapper<SysAdminUser>().eq("USER_ID", sysAdminUser.getUserId())).size()>0) {
			responseSuccessModel.setSuccess(false);
			responseSuccessModel.setErrCode("DATA.IS.EXITS");
			responseSuccessModel.setErrMessage("此用户账号已存在");
			return responseSuccessModel;
		}
		
		//检测密码策略
		Map<String,Object> map = passwordPolicyCheckService.checkPolicy(sysAdminUser.getUserPassword());
		if(!(boolean) map.get("success")) {
			responseSuccessModel.setRequestId(UUID.randomUUID().toString());
			responseSuccessModel.setCode(0);
			responseSuccessModel.setSuccess(false);
			responseSuccessModel.setErrMessage(String.valueOf(map.get("msg")));
			return responseSuccessModel;
		}
		
		
		
//		MessageDigest md = MessageDigest.getInstance("MD5");
//		md.update(sysAdminUser.getUserPassword().getBytes());
//		String newPassword = new BigInteger(1, md.digest()).toString(16);
		String newPassword = Hash256.getSHA256Str(sysAdminUser.getUserPassword());
		sysAdminUser.setUserPassword(newPassword);
		sysAdminUser.setId(UUID.randomUUID().toString());
		sysAdminUser.setRecordId(UUID.randomUUID().toString());
		sysAdminUser.setCdate(new Date());
		sysAdminUser.setCreator(adminLoginInfo.getUserId());
		sysAdminUser.setUdate(new Date());
		sysAdminUser.setUpdator(adminLoginInfo.getUserId());
		sysAdminUser.setRecordVersion(UUID.randomUUID().toString());
		JsonObject jsonObject = (JsonObject) new JsonParser().parse(payload).getAsJsonObject();
		String adminuserRoleId = jsonObject.get("adminuserRoleId").getAsString();
		sysAdminUser.setRoleId(adminuserRoleId);
		StringBuffer sb = new StringBuffer();
		sb.append("用户");
		sb.append(adminLoginInfo.getUserName());
		sb.append("于");
		sb.append(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
		sb.append("新增用户");
		sysAdminLogService.saveAdminLog(OperationEnums.INSERT, ModuleEnums.USER, sb.toString(), sysAdminUser.getId());
		sysAdminUserService.saveOrUpdate(sysAdminUser);
		responseSuccessModel.setRequestId(UUID.randomUUID().toString());
		responseSuccessModel.setCode(0);
		responseSuccessModel.setSuccess(true);
		return responseSuccessModel;
	}

	@RequestMapping("/list")
	public AdminUserListResponse list(@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "userName", required = false) String userName, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("userName", userName);

		return sysAdminUserService.queryList(map);
	}

	@RequestMapping("/update")
	public ResponseSuccessModel updateStatus(HttpServletRequest request, @RequestBody String payload) throws NoSuchAlgorithmException {
		SysAdminUser sysAdminUser = gson.fromJson(payload, SysAdminUser.class);
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		JsonObject jsonObject = (JsonObject) new JsonParser().parse(payload).getAsJsonObject();
		String adminuserRoleId = jsonObject.get("adminuserRoleId").getAsString();
		
		
		sysAdminUser.setRoleId(adminuserRoleId);
		sysAdminUser.setUdate(new Date());
		sysAdminUser.setUpdator(adminLoginInfo.getUserId());
		sysAdminUserService.updateById(sysAdminUser);
		StringBuffer sb = new StringBuffer();
		sb.append("用户");
		sb.append(adminLoginInfo.getUserName());
		sb.append("于");
		sb.append(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
		sb.append("修改用户");
		sysAdminLogService.saveAdminLog(OperationEnums.UPDATE, ModuleEnums.USER, sb.toString(), sysAdminUser.getId());
		ResponseSuccessModel responseSuccessModel = new ResponseSuccessModel();
		responseSuccessModel.setRequestId(UUID.randomUUID().toString());
		responseSuccessModel.setCode(0);
		responseSuccessModel.setSuccess(true);
		return responseSuccessModel;
	}

}
