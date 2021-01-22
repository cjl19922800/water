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
import com.shanghaiwater.mcs.admin.model.FlowsheetDetailPageResponse;
import com.shanghaiwater.mcs.admin.service.FlowsheetDetailService;
import com.shanghaiwater.mcs.db.entity.AccFlowsheetDetail;

@RestController
@RequestMapping("/right/flowsheet")
@PermissionIntercept
public class FlowsheetDetailController {
	
	@Autowired
	private FlowsheetDetailService flowsheetDetailSerive;
	
	@RequestMapping(value="/listview")
	public ModelAndView listView(Model model) {
		
		System.out.println("11111");
		return new ModelAndView("views/right/flowsheet/flowsheet.html","Model", model);
	}
	
	@RequestMapping(value="/getFailed")
	public FlowsheetDetailPageResponse findFailed(@RequestParam(value="startTime",required=false)String startTime,
			@RequestParam(value="endTime",required=false)String endTime,
			@RequestParam(value="payChannel",required=false)String payChannel,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="limit",defaultValue="10")Integer limit) {
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("payChannel", payChannel);
		map.put("page",page);
		map.put("limit",limit);
		
		return flowsheetDetailSerive.findFailed(map);
	}
	
	
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public ModelAndView detail(@RequestParam(value="id",required=true) String flowsheetDetailId,Model model) {
		
		Map<String,Object> map=new HashMap<String,Object>();
		model.addAttribute("flowsheet", map.put("flowsheetDetailId", flowsheetDetailId));
		return new ModelAndView("views/right/flowsheet/flowsheetfrom","model",model);
	}
	

}
