package com.shanghaiwater.mcs.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shanghaiwater.mcs.admin.annotation.PermissionIntercept;
import com.shanghaiwater.mcs.admin.enums.ModuleEnums;
import com.shanghaiwater.mcs.admin.enums.OperationEnums;
import com.shanghaiwater.mcs.admin.model.CommonPageReponse;
import com.shanghaiwater.mcs.admin.service.SysAdminLogService;

@RestController
@RequestMapping("/right/adminlog")
@PermissionIntercept
public class SysAdminLogController {

	@Autowired
	private SysAdminLogService sysAdminLogService;
	
	
	@RequestMapping(value = "/listview", method = RequestMethod.GET)
	public ModelAndView listView(Model model) {
		model.addAttribute("modules", ModuleEnums.values());
		model.addAttribute("operations", OperationEnums.values());
		return new ModelAndView("views/right/adminlog/adminlog", "model", model);
	}
	
	@RequestMapping(value="/queryList",method=RequestMethod.GET)
	public CommonPageReponse queryListByPage(
			@RequestParam(value="startTime",required=false) String startTime,
			@RequestParam(value="endTime",required=false)String endTime,
			@RequestParam(value="operationType",required=false)String operationType,
			@RequestParam(value="moduleType",required=false)String moduleType,
			@RequestParam(value="page",required=false,defaultValue="1")String page,
			@RequestParam(value="limit",required=false,defaultValue="10")String limit
			) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("operationType", operationType);
		map.put("moduleType", moduleType);
		map.put("page", page);
		map.put("limit", limit);
		return sysAdminLogService.queryPagingLogList(map);
	}
	
}
