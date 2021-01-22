package com.shanghaiwater.mcs.admin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanghaiwater.mcs.admin.annotation.PermissionIntercept;
import com.shanghaiwater.mcs.admin.common.AdminLoginInfo;
import com.shanghaiwater.mcs.admin.common.ResponseSuccessModel;
import com.shanghaiwater.mcs.admin.enums.ModuleEnums;
import com.shanghaiwater.mcs.admin.enums.OperationEnums;
import com.shanghaiwater.mcs.admin.model.RoleListResponse;
import com.shanghaiwater.mcs.admin.model.StandardListResponse;
import com.shanghaiwater.mcs.admin.service.IMgtWaterCompanyService;
import com.shanghaiwater.mcs.admin.service.SysAdminLogService;
import com.shanghaiwater.mcs.admin.service.SysRoleService;
import com.shanghaiwater.mcs.common.util.DateUtils;
import com.shanghaiwater.mcs.db.entity.MgtWaterCompany;
import com.shanghaiwater.mcs.db.entity.SysResources;
import com.shanghaiwater.mcs.db.entity.SysRole;
import com.shanghaiwater.mcs.db.entity.SysRoleCompany;
import com.shanghaiwater.mcs.db.entity.SysRoleResources;
import com.shanghaiwater.mcs.db.mapper.SysResourcesMapper;
import com.shanghaiwater.mcs.db.mapper.SysRoleCompanyMapper;
import com.shanghaiwater.mcs.db.mapper.SysRoleMapper;
import com.shanghaiwater.mcs.db.mapper.SysRoleResourcesMapper;

/**
 * 页面跳转
 * 
 * @author Laipu
 *
 */
@RestController
@RequestMapping("/right/role")
@PermissionIntercept
public class RoleController {

	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private IMgtWaterCompanyService mgtWaterCompanyService;
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Autowired
	private SysResourcesMapper sysResourcesMapper;

	@Autowired
	private SysRoleCompanyMapper sysRoleCompanyMapper;

	@Autowired
	private SysRoleResourcesMapper sysRoleResourcesMapper;
	
	@Autowired
	private SysAdminLogService sysAdminLogService;

	@RequestMapping(value = "/listview", method = RequestMethod.GET)
	public ModelAndView listView(Model model) {
		return new ModelAndView("views/right/role/role", "toolModel", model);
	}

	@RequestMapping("/detail")
	public ModelAndView detailPage(@RequestParam(value = "id", required = false) String id, Model model) {
		System.out.println(id);
		SysRole sysRole = sysRoleService.getById(id);
		model.addAttribute("role", sysRole);
		QueryWrapper<SysRoleCompany> qw = new QueryWrapper<SysRoleCompany>();
		qw.eq("ROLE_ID", id);
		List<SysRoleCompany> companyPowers = sysRoleCompanyMapper.selectList(qw);
		List<String> companyCodes = new ArrayList<String>();
		for(SysRoleCompany power: companyPowers) {
			companyCodes.add(power.getCompanyCode());
		}
		model.addAttribute("companyCodes", companyCodes);
		List<MgtWaterCompany> companys = mgtWaterCompanyService.findAllCompany();
		model.addAttribute("companys", companys);
		return new ModelAndView("views/right/role/roleform", "model", model);
	}

	@RequestMapping("/list")
	public RoleListResponse list(@RequestParam(value = "role_name", required = false) String roleName, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleName", roleName);
		return sysRoleService.queryList(map);
	}

	@RequestMapping("/update")
	public ResponseSuccessModel updateStatus(HttpServletRequest request, @RequestBody SysRole sysRole) {
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
//		sysRole.setUpdator(adminLoginInfo.getId());
		sysRoleService.updateById(sysRole);
		QueryWrapper<SysRoleCompany> qw = new QueryWrapper<SysRoleCompany>();
		qw.eq("ROLE_ID", sysRole.getId());
		sysRoleCompanyMapper.delete(qw);
		String[] companys = sysRole.getCompanyPowers().split(",");
		if(companys.length>0) {
			for(String company:companys) {
				SysRoleCompany companyPower = new SysRoleCompany();
				companyPower.setRoleId(sysRole.getId());
				companyPower.setCompanyCode(company);
				companyPower.setCreateTime(new Date());
				companyPower.setCreateId(adminLoginInfo.getUserId());
				sysRoleCompanyMapper.insert(companyPower);
			}
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("用户");
		sb.append(adminLoginInfo.getUserName());
		sb.append("于");
		sb.append(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
		sb.append("修改角色");
		sb.append(sysRole.getRoleName());
		sysAdminLogService.saveAdminLog(OperationEnums.UPDATE, ModuleEnums.ROLE, sb.toString(), sysRole.getId());
		
		
		ResponseSuccessModel responseSuccessModel = new ResponseSuccessModel();
		responseSuccessModel.setRequestId(UUID.randomUUID().toString());
		responseSuccessModel.setCode(0);
		responseSuccessModel.setSuccess(true);
		return responseSuccessModel;
	}
	@RequestMapping("/add")
	public ModelAndView addView(Model model) {
		List<MgtWaterCompany> companys = mgtWaterCompanyService.findAllCompany();
		model.addAttribute("companys", companys);
		return new ModelAndView("views/right/role/role_add", "toolModel", model);
	}
	
	@RequestMapping("/insert")
	public ResponseSuccessModel insert(HttpServletRequest request, @RequestBody SysRole sysRole) {
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");	
		String uuid = UUID.randomUUID().toString();
		
		sysRole.setRoleId(uuid);
		
		sysRole.setCdate(DateUtils.dateToLocalDateTime(new Date()));
		sysRole.setUdate(DateUtils.dateToLocalDateTime(new Date()));
		sysRole.setCreator(adminLoginInfo.getUserId());
		sysRole.setUpdator(adminLoginInfo.getUserId());
		sysRole.setId(uuid);
		sysRole.setRecordId(UUID.randomUUID().toString());
		sysRole.setRecordVersion(UUID.randomUUID().toString());
		sysRoleMapper.insert(sysRole);
		String[] companys = sysRole.getCompanyPowers().split(",");
		if(companys.length>0) {
			for(String company:companys) {
				SysRoleCompany companyPower = new SysRoleCompany();
				companyPower.setRoleId(uuid);
				companyPower.setCompanyCode(company);
				companyPower.setCreateTime(new Date());
				companyPower.setCreateId(adminLoginInfo.getUserId());
				sysRoleCompanyMapper.insert(companyPower);
			}
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("用户");
		sb.append(adminLoginInfo.getUserName());
		sb.append("于");
		sb.append(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
		sb.append("新增角色");
		sb.append(sysRole.getRoleName());
		sysAdminLogService.saveAdminLog(OperationEnums.INSERT, ModuleEnums.ROLE, sb.toString(), sysRole.getId());
		
		ResponseSuccessModel responseSuccessModel = new ResponseSuccessModel();
		responseSuccessModel.setRequestId(UUID.randomUUID().toString());
		responseSuccessModel.setCode(0);
		responseSuccessModel.setSuccess(true);
		return responseSuccessModel;
	}
	
	@RequestMapping("/delete")
	public ResponseSuccessModel delete(HttpServletRequest request, @RequestBody String dataRequest) {
		List<SysRole> roles =  JSON.parseArray(dataRequest,SysRole.class);
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		List<String> ids = new ArrayList<String>();
		if(roles.size()>0) {
			for(SysRole role:roles) {
				ids.add(role.getId());
				StringBuffer sb = new StringBuffer();
				sb.append("用户");
				sb.append(adminLoginInfo.getUserName());
				sb.append("于");
				sb.append(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
				sb.append("删除角色");
				sb.append(role.getRoleName());
				sysAdminLogService.saveAdminLog(OperationEnums.DELETE, ModuleEnums.ROLE, sb.toString(), role.getId());
			}
			QueryWrapper<SysRoleCompany> qw = new QueryWrapper<SysRoleCompany>();
			qw.in("ROLE_ID", ids);
			sysRoleCompanyMapper.delete(qw);
			sysRoleMapper.deleteBatchIds(ids);
		}
		ResponseSuccessModel responseSuccessModel = new ResponseSuccessModel();
		responseSuccessModel.setRequestId(UUID.randomUUID().toString());
		responseSuccessModel.setCode(0);
		responseSuccessModel.setSuccess(true);
		return responseSuccessModel;
	}
	
	@RequestMapping("/powerSet")
	public ModelAndView powerSetView(Model model,@RequestParam(value = "id", required = false) String id) {
		model.addAttribute("roleId", id);
		return new ModelAndView("views/right/role/power", "model", model);
	}
	
	@RequestMapping("/queryPower")
	public StandardListResponse queryPower(@RequestParam(value = "roleId", required = false) String id) {
		StandardListResponse response = new StandardListResponse();
		List<String> resourcesIds = sysRoleResourcesMapper.getResourcesIdByRoleId(id);
		QueryWrapper<SysResources> qw = new QueryWrapper<SysResources>();
		qw.eq("EMPLOY", true);
		qw.orderByAsc("MODULAR","SORT_NO");
		List<SysResources> list = sysResourcesMapper.selectList(qw);
		if(list.size()>0) {
			for(SysResources resources:list) {
				if(resourcesIds.contains(resources.getId())) {
					resources.setChecked(true);
				}else {
					resources.setChecked(false);
				}
			}
		}
		response.setCode(0);
		response.setTotal(list.size());
		response.setCount(String.valueOf(list.size()));
		response.setSuccess(true);
		response.setData(list);
		
		System.out.println(response.getData());
		
		return response;
	}
	
	@RequestMapping("/power/insert")
	public ResponseSuccessModel powerInsert(HttpServletRequest request,
			@RequestBody String payload,
			@RequestParam(value = "id", required = false) String id) {
		List<SysResources> resourcesList =  JSON.parseArray(payload,SysResources.class);
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		QueryWrapper<SysRoleResources> qw = new QueryWrapper<SysRoleResources>();
		qw.eq("ROLE_ID", id);
		sysRoleResourcesMapper.delete(qw);
		if(resourcesList.size()>0) {
			for(SysResources resources:resourcesList) {
				SysRoleResources power = new SysRoleResources();
				power.setCreateId(adminLoginInfo.getUserId());
				power.setCreateTime(new Date());
				power.setResourcesId(resources.getId());
				power.setRoleId(id);
				sysRoleResourcesMapper.insert(power);
			}
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("用户");
		sb.append(adminLoginInfo.getUserName());
		sb.append("于");
		sb.append(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
		sb.append("修改角色权限");
		sysAdminLogService.saveAdminLog(OperationEnums.UPDATE, ModuleEnums.ROLE, sb.toString(), id);
		
		ResponseSuccessModel responseSuccessModel = new ResponseSuccessModel();
		responseSuccessModel.setRequestId(UUID.randomUUID().toString());
		responseSuccessModel.setCode(0);
		responseSuccessModel.setSuccess(true);
		return responseSuccessModel;
	}
	
}
