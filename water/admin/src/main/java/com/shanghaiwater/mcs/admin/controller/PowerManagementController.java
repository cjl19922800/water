package com.shanghaiwater.mcs.admin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanghaiwater.mcs.admin.annotation.PermissionIntercept;
import com.shanghaiwater.mcs.admin.common.AdminLoginInfo;
import com.shanghaiwater.mcs.admin.common.ResponseSuccessModel;
import com.shanghaiwater.mcs.admin.enums.ModuleEnums;
import com.shanghaiwater.mcs.admin.enums.OperationEnums;
import com.shanghaiwater.mcs.admin.model.CommonPageReponse;
import com.shanghaiwater.mcs.admin.model.request.SysNavigationRequest;
import com.shanghaiwater.mcs.admin.service.SysAdminLogService;
import com.shanghaiwater.mcs.db.entity.SysNavigation;
import com.shanghaiwater.mcs.db.entity.SysResources;
import com.shanghaiwater.mcs.db.mapper.SysNavigationMapper;
import com.shanghaiwater.mcs.db.mapper.SysResourcesMapper;


/**
 * 权限管理（导航、资源、用户设置权限、导航资源配置）
 * @author Laipu
 */
@RestController
@RequestMapping("/admin/power")
@PermissionIntercept
public class PowerManagementController {

	@Autowired
	private SysNavigationMapper sysNavigationMapper;
	
	@Autowired
	private SysResourcesMapper sysResourcesMapper;
	
	@Autowired
	private SysAdminLogService sysAdminLogService;

	//导航-列表页面
	@RequestMapping("/navigation/list")
	public ModelAndView navigationList(Model model) {
		return new ModelAndView("views/right/navigation/navigation", "model", model);
	}
	
	//导航-列表查询
	@RequestMapping("/navigation/query")
	public CommonPageReponse queryNavigationList(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "parent", required = false) String parent,
			@RequestParam(defaultValue = "1")String page,
			@RequestParam(defaultValue = "10")String limit
			) {
		CommonPageReponse reponse = new CommonPageReponse();
		int current = Integer.parseInt(page); 
		int limitNum = Integer.parseInt(limit); 
		Page<SysNavigation> pageData = new Page<SysNavigation>(current,limitNum); 
		QueryWrapper<SysNavigation> qw = new QueryWrapper<SysNavigation>();
		if(StringUtils.isNotEmpty(name)) {
			qw.like("NAME", name);
		}
		
		if(StringUtils.isNotEmpty(parent)) {
			if(parent.equals("true")) {
				qw.isNull("PARENT_ID");
			}else {
				qw.isNotNull("PARENT_ID");
			}
		}
		
//		qw.eq("EMPLOY", true);
		qw.orderByAsc("SORT_NO");
		IPage<SysNavigation> list= sysNavigationMapper.selectPage(pageData,qw);
		reponse.setCode(0); 
		reponse.setTotal(list.getSize());
		reponse.setCount(String.valueOf(list.getSize()));
		reponse.setSuccess(true); 
		reponse.setData(list);
		return reponse;
	}
	
	//导航-新增页面
	@RequestMapping("/navigation/add")
	public ModelAndView navigationAdd(Model model) {
		QueryWrapper<SysNavigation> qw = new QueryWrapper<SysNavigation>();
		qw.eq("EMPLOY", true);
		qw.isNull("PARENT_ID");
		model.addAttribute("navigations", sysNavigationMapper.selectList(qw));
		return new ModelAndView("views/right/navigation/navigationform", "model", model);
	}
	
	//导航-新增方法
	@RequestMapping("/navigation/insert")
	public ResponseSuccessModel create(HttpServletRequest request, @RequestBody SysNavigationRequest dataRequest) {
		SysNavigation navigation = new SysNavigation();
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		navigation.setExplain(dataRequest.getExplain());
		navigation.setName(dataRequest.getName());
		navigation.setSortNo(dataRequest.getSortNo());
		navigation.setId(UUID.randomUUID().toString());
		navigation.setCreaterId(adminLoginInfo.getUserId());
		navigation.setUpdateId(adminLoginInfo.getUserId());
		navigation.setCreateTime(new Date());
		navigation.setUpdateTime(new Date());
		navigation.setParentId(dataRequest.getParentId());
		sysNavigationMapper.insert(navigation);
		
		StringBuffer sb = new StringBuffer();
		sb.append("用户");
		sb.append(adminLoginInfo.getUserName());
		sb.append("于");
		sb.append(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
		sb.append("新增导航");
		sb.append(navigation.getName());
		sysAdminLogService.saveAdminLog(OperationEnums.INSERT, ModuleEnums.NAVIGATION, sb.toString(), navigation.getId());
		
		ResponseSuccessModel responseSuccessModel = new ResponseSuccessModel();
		responseSuccessModel.setRequestId(UUID.randomUUID().toString());
		responseSuccessModel.setCode(0);
		responseSuccessModel.setSuccess(true);
		return responseSuccessModel;
	}
	
	//导航-删除方法
	@RequestMapping("/navigation/delete")
	public ResponseSuccessModel delete(HttpServletRequest request, @RequestBody String dataRequest) {
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		List<SysNavigation> navigations =  JSON.parseArray(dataRequest,SysNavigation.class);
		List<String> ids = new ArrayList<String>();
		if(navigations.size()>0) {
			for(SysNavigation naviation:navigations) {
				ids.add(naviation.getId());
				StringBuffer sb = new StringBuffer();
				sb.append("用户");
				sb.append(adminLoginInfo.getUserName());
				sb.append("于");
				sb.append(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
				sb.append("删除导航");
				sb.append(naviation.getName());
				sysAdminLogService.saveAdminLog(OperationEnums.DELETE, ModuleEnums.NAVIGATION, sb.toString(), naviation.getId());
			}	
			sysNavigationMapper.deleteBatchIds(ids);
		}
		ResponseSuccessModel responseSuccessModel = new ResponseSuccessModel();
		responseSuccessModel.setRequestId(UUID.randomUUID().toString());
		responseSuccessModel.setCode(0);
		responseSuccessModel.setSuccess(true);
		return responseSuccessModel;
	}
	
	//导航-更改页面
	@RequestMapping("/navigation/edit")
	public ModelAndView navigationEdit(Model model,@RequestParam(value = "id", required = false) String id) {
		SysNavigation navigation = sysNavigationMapper.selectById(id);
		model.addAttribute("navigation", navigation);
		
		QueryWrapper<SysNavigation> qw = new QueryWrapper<SysNavigation>();
		qw.eq("EMPLOY", true);
		qw.ne("ID", id);
		qw.isNull("PARENT_ID");
		model.addAttribute("navigations", sysNavigationMapper.selectList(qw));
		
		return new ModelAndView("views/right/navigation/navigation_edit", "model", model);
	}
	
	//导航-更改方法
	@RequestMapping("/navigation/update")
	public ResponseSuccessModel navigationUpdate(HttpServletRequest request, @RequestBody SysNavigationRequest dataRequest) {
		ResponseSuccessModel responseSuccessModel = new ResponseSuccessModel();
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		responseSuccessModel.setSuccess(true);
		if(StringUtils.isEmpty(dataRequest.getId())) {
			responseSuccessModel.setSuccess(false);
		}
		SysNavigation navigation = sysNavigationMapper.selectById(dataRequest.getId());
		if(null == navigation) {
			responseSuccessModel.setSuccess(false);
		}else {
			navigation.setExplain(dataRequest.getExplain());
			navigation.setName(dataRequest.getName());
			navigation.setSortNo(dataRequest.getSortNo());
			navigation.setUpdateId(adminLoginInfo.getUserId());
			navigation.setUpdateTime(new Date());
			navigation.setParentId(dataRequest.getParentId());
			sysNavigationMapper.updateById(navigation);
			
			StringBuffer sb = new StringBuffer();
			sb.append("用户");
			sb.append(adminLoginInfo.getUserName());
			sb.append("于");
			sb.append(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
			sb.append("修改导航");
			sb.append(navigation.getName());
			sysAdminLogService.saveAdminLog(OperationEnums.UPDATE, ModuleEnums.NAVIGATION, sb.toString(), navigation.getId());
			
			
		}
		responseSuccessModel.setRequestId(UUID.randomUUID().toString());
		responseSuccessModel.setCode(0);
		return responseSuccessModel;
	}
	
	//资源列表
	@RequestMapping("/resources/list")
	public ModelAndView resourcesList(Model model) {
		List<String> modulars = sysResourcesMapper.getModulars();
		model.addAttribute("modulars", modulars);
		return new ModelAndView("views/right/resources/resources", "model", model);
	}
	
	//资源数据查询
	@RequestMapping("/resources/query")
	public CommonPageReponse queryResourcesList(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "modular", required = false) String modular,
			@RequestParam(value = "url", required = false) String url,
			@RequestParam(defaultValue = "1")String page,
			@RequestParam(defaultValue = "10")String limit
			) {
		CommonPageReponse reponse = new CommonPageReponse();
		int current = Integer.parseInt(page); 
		int limitNum = Integer.parseInt(limit); 
		Page<SysResources> pageData = new Page<SysResources>(current,limitNum); 
		QueryWrapper<SysResources> qw = new QueryWrapper<SysResources>();
		if(StringUtils.isNotEmpty(name)) {
			qw.like("NAME", name);
		}
		if(StringUtils.isNotEmpty(modular)) {
			qw.eq("MODULAR", modular);
		}
		if(StringUtils.isNotEmpty(url)) {
			qw.like("URL", url);
		}
//		qw.eq("EMPLOY", true);
		qw.orderByAsc("MODULAR","SORT_NO");
		IPage<SysResources> list= sysResourcesMapper.selectPage(pageData,qw);
		reponse.setCode(0); 
		reponse.setTotal(list.getSize());
		reponse.setCount(String.valueOf(list.getSize()));
		reponse.setSuccess(true); 
		reponse.setData(list);
		return reponse;
	}
	
	//导航-新增页面
	@RequestMapping("/resources/add")
	public ModelAndView resourcesAdd(Model model) {
		QueryWrapper<SysNavigation> qw = new QueryWrapper<SysNavigation>();
		qw.eq("EMPLOY", true);
		qw.isNotNull("PARENT_ID");
		qw.orderByAsc("SORT_NO");
		List<SysNavigation> navigations = sysNavigationMapper.selectList(qw);
		model.addAttribute("navigations", navigations);
		List<String> modulars = sysResourcesMapper.getModulars();
		model.addAttribute("modulars", modulars);
		return new ModelAndView("views/right/resources/resources_add", "model", model);
	}
	
	@RequestMapping("/resources/insert")
	public ResponseSuccessModel resourcesInsert(HttpServletRequest request, @RequestBody SysResources dataRequest) {
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		dataRequest.setId(UUID.randomUUID().toString());
		dataRequest.setCreateId(adminLoginInfo.getUserId());
		dataRequest.setUpdateId(adminLoginInfo.getUserId());
		dataRequest.setCreateTime(new Date());
		dataRequest.setUpdateTime(new Date());
		sysResourcesMapper.insert(dataRequest);
		
		StringBuffer sb = new StringBuffer();
		sb.append("用户");
		sb.append(adminLoginInfo.getUserName());
		sb.append("于");
		sb.append(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
		sb.append("新增资源");
		sb.append(dataRequest.getName());
		sysAdminLogService.saveAdminLog(OperationEnums.INSERT, ModuleEnums.RESOURCES, sb.toString(), dataRequest.getId());
		
		
		ResponseSuccessModel responseSuccessModel = new ResponseSuccessModel();
		responseSuccessModel.setRequestId(UUID.randomUUID().toString());
		responseSuccessModel.setCode(0);
		responseSuccessModel.setSuccess(true);
		return responseSuccessModel;
	}
	
	@RequestMapping("/resources/edit")
	public ModelAndView resourcesEdit(Model model,@RequestParam(value = "id", required = false) String id) {
		SysResources resource = sysResourcesMapper.selectById(id);
		QueryWrapper<SysNavigation> qw = new QueryWrapper<SysNavigation>();
		qw.eq("EMPLOY", true);
		qw.isNotNull("PARENT_ID");
		qw.orderByAsc("SORT_NO");
		List<SysNavigation> navigations = sysNavigationMapper.selectList(qw);
		List<String> modulars = sysResourcesMapper.getModulars();
		model.addAttribute("modulars", modulars);
		model.addAttribute("navigations", navigations);
		model.addAttribute("resource", resource);
		return new ModelAndView("views/right/resources/resources_edit", "model", model);
	}
	
	@RequestMapping("/resources/update")
	public ResponseSuccessModel resourcesUpdate(HttpServletRequest request, @RequestBody SysResources dataRequest) {
		AdminLoginInfo adminLoginInfo = (AdminLoginInfo) request.getSession().getAttribute("adminLoginInfo");
		SysResources resource = sysResourcesMapper.selectById(dataRequest.getId());
		BeanUtils.copyProperties(dataRequest, resource);
		resource.setUpdateId(adminLoginInfo.getUserId());
		resource.setUpdateTime(new Date());
		sysResourcesMapper.updateById(dataRequest);
		
		StringBuffer sb = new StringBuffer();
		sb.append("用户");
		sb.append(adminLoginInfo.getUserName());
		sb.append("于");
		sb.append(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
		sb.append("修改资源");
		sb.append(dataRequest.getName());
		sysAdminLogService.saveAdminLog(OperationEnums.UPDATE, ModuleEnums.RESOURCES, sb.toString(), dataRequest.getId());
		
		
		ResponseSuccessModel responseSuccessModel = new ResponseSuccessModel();
		responseSuccessModel.setRequestId(UUID.randomUUID().toString());
		responseSuccessModel.setCode(0);
		responseSuccessModel.setSuccess(true);
		return responseSuccessModel;
	}
}
