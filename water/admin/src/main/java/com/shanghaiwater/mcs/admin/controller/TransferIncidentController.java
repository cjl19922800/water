package com.shanghaiwater.mcs.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shanghaiwater.mcs.admin.annotation.PermissionIntercept;
import com.shanghaiwater.mcs.admin.model.CommonPageReponse;
import com.shanghaiwater.mcs.admin.service.IMgtWaterCompanyService;
import com.shanghaiwater.mcs.admin.service.TransferIncidentService;
import com.shanghaiwater.mcs.db.entity.MgtWaterCompany;

@RestController
@RequestMapping("/home/transfer/")
@PermissionIntercept
public class TransferIncidentController {
	
	@Autowired
	private TransferIncidentService transferIncidentService;
	
	@Autowired
	private IMgtWaterCompanyService mgtWaterCompanyService;
	

	@RequestMapping(value = "/listview", method = RequestMethod.GET)
	public ModelAndView listView(Model model) {
		
		List<MgtWaterCompany> companyList = mgtWaterCompanyService.findAllCompany();
		model.addAttribute("companyList", companyList);
		return new ModelAndView("views/home/transfer/transferIndex", "model", model);
	}
	
	
	@RequestMapping(value="/queryList",method=RequestMethod.GET)
	public CommonPageReponse queryListByPage(
			@RequestParam(value="startTime",required=false) String startTime,
			@RequestParam(value="endTime",required=false)String endTime,
			@RequestParam(value="companyCode",required=false)String companyCode,
			@RequestParam(value="page",required=false,defaultValue="1")String page,
			@RequestParam(value="limit",required=false,defaultValue="10")String limit
			) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("companyCode", companyCode);
		map.put("page", page);
		map.put("limit", limit);
		return transferIncidentService.queryPagingLogList(map);
	}
	
	
	
}
